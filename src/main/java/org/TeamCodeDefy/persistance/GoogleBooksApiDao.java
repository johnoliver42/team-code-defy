package org.TeamCodeDefy.persistance;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GoogleBooksApiDao {

    public GoogleBookResponse getGoogleBook() {
        Client client = ClientBuilder.newClient();
        //TODO read in URI from properties file
        WebTarget target =
                client.target("https://www.googleapis.com/books/v1/volumes?q=isbn:9781476735115");
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
