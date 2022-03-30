package Meli.Back.Controllers;

import Meli.Back.controllers.Controller;
import Meli.Back.models.Author;
import Meli.Back.models.DetailItem;
import Meli.Back.models.DetailResult;
import Meli.Back.models.SearchResult;
import Meli.Back.services.FilterService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FilterService service;

    @Test
    public void listItems() throws Exception {

        Author author = new Author();
        SearchResult searchResultLocal = SearchResult.builder()
                .items(Collections.EMPTY_LIST)
                .author(author)
                .build();

        when(service.listItems("test")).thenReturn(searchResultLocal);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/items/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..author.name", hasItem("Nataly")))
                .andExpect(jsonPath("$..author.lastName", hasItem("Mora Mancera")));

    }

    @Test
    public void detailItem() throws Exception {

        Author author = new Author();
        DetailResult detailResult = DetailResult.builder()
                .item(DetailItem.builder().id("123").build())
                .author(author)
                .build();

        when(service.detailItem("123")).thenReturn(detailResult);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/item/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..author.name", hasItem("Nataly")))
                .andExpect(jsonPath("$..author.lastName", hasItem("Mora Mancera")))
                .andExpect(jsonPath("$..item.id", hasItem("123")));
    }
}