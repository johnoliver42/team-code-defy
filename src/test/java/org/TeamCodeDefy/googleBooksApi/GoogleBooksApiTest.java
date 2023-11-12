package org.TeamCodeDefy.googleBooksApi;

import org.TeamCodeDefy.persistance.GoogleBooksApiDao;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleBooksApiTest {

    @Test
    public void testGoogleBooksApi() {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://www.googleapis.com/books/v1/volumes?q=isbn:9781476735115");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        assertEquals("???", response);
    }

    @Test
    public void testBookTitleSuccess() throws Exception {

        String isbn = "9781476735115";

        GoogleBooksApiDao dao = new GoogleBooksApiDao();
        GoogleBookResponse gbResponse = dao.getGoogleBook(isbn);
        String expectedBookName = "Wool";
        List<ItemsItem> items = gbResponse.getItems();
        ItemsItem firstItem = items.get(0);
        VolumeInfo volumeInfo = firstItem.getVolumeInfo();
        assertEquals(expectedBookName, volumeInfo.getTitle());
    }
}