package giphy.pikachu.Models;

import jakarta.json.JsonObject;

public class QueryObject {
    String q = "pikachu";
    int limit = 10;
    int offset = 0;
    String rating = "g";
    String lang ="en";

    public String getQ() {
        return q;
    }
    public void setQ(String q) {
        this.q = q;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
    }
    
    public void create(JsonObject parameters){
        setQ(parameters.getString("q"));
        setLimit(parameters.getInt("limit"));
        setOffset(parameters.getInt("offset"));
        setRating(parameters.getString("rating"));
        setLang(parameters.getString("lang"));
    }
}
