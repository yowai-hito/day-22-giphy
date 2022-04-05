package giphy.pikachu.Services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import giphy.pikachu.Models.QueryObject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class SearchService {

    public JsonArray giphyRequest(QueryObject params){

        Properties prop = new Properties();

        try(InputStream input = new FileInputStream("application.properties")){
            prop.load(input);
        }
        catch(Exception e) {
            prop.setProperty("GIPHY_API_KEY", ${GIPHY_KEY});
        }

        final String GIPHY_API_KEY = prop.getProperty("GIPHY_API_KEY");

        RestTemplate template = new RestTemplate();

        String endpoint = UriComponentsBuilder
            .fromUriString("https://api.giphy.com/v1/gifs/search")
            .queryParam("api_key",GIPHY_API_KEY)
            .queryParam("q", params.getQ())
            .queryParam("limit",params.getLimit())
            .queryParam("rating",params.getRating())
            .queryParam("offset",params.getOffset())
            .queryParam("lang", params.getLang())
            .build().toString();



        RequestEntity<Void> req = RequestEntity
            .get(endpoint)
            .accept(MediaType.APPLICATION_JSON)
            .build();
            
        ResponseEntity<String> resp = template.exchange(req, String.class);
        JsonReader jsonReader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject giphyResponse = jsonReader.readObject();
        JsonArray gifs = giphyResponse.getJsonArray("data");
        return gifs;

    }
}
