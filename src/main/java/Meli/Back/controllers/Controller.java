package Meli.Back.controllers;

import Meli.Back.models.DetailResult;
import Meli.Back.models.SearchResult;
import Meli.Back.services.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@CrossOrigin( origins = "http://localhost:3000")

public class Controller {
    @Autowired
    private FilterService service;

    @GetMapping(value = "api/items/{query}")
    public SearchResult queryItems(@PathVariable("query") String query) throws IOException {
        return service.listItems(query);
    }
    @GetMapping(value = "api/item/{id}")
    public DetailResult detailItem(@PathVariable("id") String id) throws IOException {
        return service.detailItem(id);
    }


}