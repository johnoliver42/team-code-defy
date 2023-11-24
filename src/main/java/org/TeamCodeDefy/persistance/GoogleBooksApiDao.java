package org.TeamCodeDefy.persistance;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GoogleBooksApiDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    public GoogleBookResponse getGoogleBook(String isbn) {

        GoogleBookResponse gbResponse = null;
        if (isbn.isBlank()) {
            logger.debug("no isbn was entered.");
        }
        else {
            String googleBooksURI = "https://www.googleapis.com/books/v1/volumes?q=isbn:";
            String bookUri = googleBooksURI + isbn;

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(bookUri);
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            ObjectMapper mapper = new ObjectMapper();
            try {
                gbResponse = mapper.readValue(response, GoogleBookResponse.class);
            } catch (Exception e) {
                logger.error("error mapping google book response to object.", e);
            }
        }
        return gbResponse;
    }
}
