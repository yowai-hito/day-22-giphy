package giphy.pikachu.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import giphy.pikachu.Models.QueryObject;
import giphy.pikachu.Services.SearchService;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Controller
public class SearchController {
    @Autowired
    SearchService searchsvc; 

    @GetMapping(value="/")
    public String Init(Model model){
        QueryObject params = new QueryObject();
        model.addAttribute("params", params);
        return "form";
    }
    
    @PostMapping(value = "/search")
    public String Search(Model model, @ModelAttribute("params") QueryObject params){
        
        JsonArray response = searchsvc.giphyRequest(params);
        List<String> gifUrls = new ArrayList<String>();

        for (int i=0;i<response.size();i++){
            JsonObject gifData = response.getJsonObject(i);
            String url = gifData.getJsonObject("images").getJsonObject("original").getString("url");
            gifUrls.add(url);
        }
        model.addAttribute("urls", gifUrls);
        return "result";
    }
}
