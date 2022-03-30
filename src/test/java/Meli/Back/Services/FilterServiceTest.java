package Meli.Back.Services;

import Meli.Back.models.Author;
import Meli.Back.models.DetailItem;
import Meli.Back.models.DetailResult;
import Meli.Back.models.SearchResult;

import Meli.Back.services.FilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {FilterService.class})
class FilterServiceTest {

    @Autowired
    private FilterService underTest;

    @BeforeEach
    void setUp(){
        underTest = new FilterService();
    }


    @Test
    void listItems() throws IOException {

        Author author = new Author();
        SearchResult searchResultLocal = SearchResult.builder()
                .items(Collections.EMPTY_LIST)
                .author(author)
                .build();

        var result = underTest.listItems("test");

        assertTrue(result.getClass() == searchResultLocal.getClass());
        assertEquals(result.getAuthor(), searchResultLocal.getAuthor());

    }

    @Test
    void detailItem() throws IOException {


        DetailResult detailResult = DetailResult.builder()
                .author( new Author())
                .item(DetailItem.builder().build())
                .build();

        var listOfItems = underTest.listItems("test");
        var result = underTest.detailItem(listOfItems.getItems().get(0).getId());


        assertTrue(result.getClass() == detailResult.getClass());
        assertEquals(result.getAuthor(), detailResult.getAuthor());
        assertThrows(FileNotFoundException.class, () -> underTest.detailItem("12344"));

    }

}