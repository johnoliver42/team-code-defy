package org.TeamCodeDefy.persistance;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GoogleBooksApiDao {

    public GoogleBookResponse getGoogleBook(String isbn) {

        //TODO read in URI from properties file
        String uri = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        GoogleBookResponse gbResponse = null;
        try {
            gbResponse = mapper.readValue(response, GoogleBookResponse.class);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            // TODO set up logging and write this to log
            e.printStackTrace();
        }
        return gbResponse;
    }
}
