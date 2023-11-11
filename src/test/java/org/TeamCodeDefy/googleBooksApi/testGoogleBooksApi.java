package org.TeamCodeDefy.googleBooksApi;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testGoogleBooksApi {

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
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://www.googleapis.com/books/v1/volumes?q=isbn:9781476735115");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        GoogleBookResponse gbResponse = mapper.readValue(response, GoogleBookResponse.class);
        String expectedBookName = "Wool";
        List<ItemsItem> items = gbResponse.getItems();
        ItemsItem firstItem = items.get(0);
        VolumeInfo volumeInfo = firstItem.getVolumeInfo();
        assertEquals(expectedBookName, volumeInfo.getTitle());
    }
}