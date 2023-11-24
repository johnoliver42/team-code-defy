package org.TeamCodeDefy.persistance;

import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;
import org.TeamCodeDefy.googleBooksApi.ItemsItem;
import org.TeamCodeDefy.googleBooksApi.VolumeInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GoogleBooksApiDaoTest {

    GoogleBooksApiDao dao;
    @BeforeEach
    void setUp() {
        dao = new GoogleBooksApiDao();
    }

    @Test
    void getBookWithIsbn13Success() {
        String isbn = "9781101970164";
        GoogleBookResponse gbResponse = dao.getGoogleBook(isbn);
        String expectedBookName = "Elastic";
        List<String> expectedAuthor = new ArrayList<String>();
        expectedAuthor.add("Leonard Mlodinow");
        List<ItemsItem> items = gbResponse.getItems();
        VolumeInfo volumeInfo = items.get(0).getVolumeInfo();
        assertEquals(1, items.size());
        assertEquals(expectedBookName, volumeInfo.getTitle());
        assertEquals(expectedAuthor, volumeInfo.getAuthors());
        assertEquals("ISBN_13", volumeInfo.getIndustryIdentifiers().get(0).getType());
        assertEquals(isbn, volumeInfo.getIndustryIdentifiers().get(0).getIdentifier());
    }
    @Test
    void getBookWithMultipleAuthorsSuccess() {
        String isbn = "9780486415871";
        GoogleBookResponse gbResponse = dao.getGoogleBook(isbn);
        String expectedBookName = "Crime and Punishment";
        List<String> expectedAuthor = new ArrayList<String>();
        expectedAuthor.add("Fyodor Dostoyevsky");
        expectedAuthor.add("Constance Garnett");
        List<ItemsItem> items = gbResponse.getItems();
        VolumeInfo volumeInfo = items.get(0).getVolumeInfo();
        assertEquals(1, items.size());
        assertEquals(expectedBookName, volumeInfo.getTitle());
        assertEquals(expectedAuthor, volumeInfo.getAuthors());
        assertEquals("ISBN_13", volumeInfo.getIndustryIdentifiers().get(0).getType());
        assertEquals(isbn, volumeInfo.getIndustryIdentifiers().get(0).getIdentifier());
    }


    @Test
    void isbnNotFound() {

    }

    @Test
    void isbnNotProvided() {

    }
}