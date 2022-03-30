package Meli.Back.services;

import Meli.Back.models.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class FilterService {

    public SearchResult listItems(String query) throws IOException {

        var response = getRequest("https://api.mercadolibre.com/sites/MLA/search?q=:" + query);
        var results = response.get("results");
        ArrayList<Item> presentedList = new ArrayList<>();

        if (results.isArray()) {
            for (final JsonNode objNode : results) {
                presentedList.add(Item.builder()
                        .id(getJsonItem("id", objNode))
                        .title(getJsonItem("title", objNode))
                        .price(getPrice(objNode))
                        .picture(getJsonItem("thumbnail", objNode))
                        .condition(getJsonItem("condition", objNode))
                        .free_shipping(Boolean.parseBoolean(objNode.get("shipping").get("free_shipping").toString()))
                        .build());
            }
        }

        return SearchResult.builder()
                .author(new Author())
                .items(presentedList)
                .build();
    }

    public DetailResult detailItem(String id) throws IOException {

        System.out.println("-----------");
        var response = getRequest("https://api.mercadolibre.com/items/" + id);
        var description = getRequest("https://api.mercadolibre.com/items/" + id + "/description");

        System.out.println(response);
        System.out.println(description);

        DetailItem item = DetailItem.builder()
                .id(getJsonItem("id", response))
                .title(getJsonItem("title", response))
                .price(getPrice(response))
                .picture(getPicture(response))
                .condition(getJsonItem("condition", response))
                .free_shipping(Boolean.parseBoolean(response.get("shipping").get("free_shipping").toString()))
                .sold_quantity(response.get("sold_quantity").asInt())
                .description(getJsonItem("plain_text", description))
                .build();


        return DetailResult.builder()
                .author(new Author())
                .item(item)
                .build();
    }



    private String getJsonItem(String attribute, JsonNode results) {
        return results.get(attribute).asText();
    }

    private Price getPrice(JsonNode priceAttributes) {
        return Price.builder()
                .amount(priceAttributes.get("price").asInt())
                .currency(priceAttributes.get("currency_id").asText())
                .decimals(2.00F)
                .build();
    }

    private String getPicture(JsonNode detailItem) {
        ArrayList<String> pictureList = new ArrayList<>();

        for (final JsonNode objNode : detailItem.get("pictures")) {
            pictureList.add(objNode.get("url").asText());
        }
        return pictureList.get(0);
    }


    private JsonNode getRequest(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");

        InputStream inputStream = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(inputStream);
        return jsonNode;
    }
}

