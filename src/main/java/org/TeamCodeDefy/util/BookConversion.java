package org.TeamCodeDefy.util;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;
import org.TeamCodeDefy.googleBooksApi.IndustryIdentifiersItem;
import org.TeamCodeDefy.googleBooksApi.VolumeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
* Conversion methods for Google Book.java Response
* */
public class BookConversion {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * Map Google Book.java Response from Google Books API to Book.java Entity
     **/
    public Book mapToBookEntity(GoogleBookResponse googleBookResponse, Book book) {

        //Book book = new Book();
        VolumeInfo bookInfo = googleBookResponse.getItems().get(0).getVolumeInfo();
        logger.debug("VOLUME INFO: " + bookInfo);

        book.setTitle(bookInfo.getTitle());
        book.setAuthor(joinAuthors(bookInfo.getAuthors()));
        book.setIsbn(getIsbn(bookInfo.getIndustryIdentifiers()));
        book.setDescription(bookInfo.getDescription());
        book.setPublisher(bookInfo.getPublisher());
        book.setLanguage(bookInfo.getLanguage());
        book.setPageCount(bookInfo.getPageCount());
        book.setThumbnailLink(bookInfo.getImageLinks().getThumbnail());
        book.setAverageRating(bookInfo.getAverageRating());
        return book;
    }

    private String getIsbn(List<IndustryIdentifiersItem> industryIdentifiers) {
//        "industryIdentifiers": [
//        {"type": "ISBN_13", "identifier": "9781476735115" },
//        {"type": "ISBN_10", "identifier": "1476735115"}]
            String isbn13 = null;
            String isbn10 = null;

        for (IndustryIdentifiersItem identifier : industryIdentifiers) {
            if ("ISBN_13".equals(identifier.getType())) {
                isbn13 = identifier.getIdentifier();
            } else if ("ISBN_10".equals(identifier.getType())) {
                isbn10 = identifier.getIdentifier();
            }
        }
        logger.debug("ISBN13: " + isbn13 + ", ISBN10: " + isbn10);
        // Prefer ISBN-13 if available, otherwise use ISBN-10
        return (isbn13 != null) ? isbn13 : isbn10;
    }

    private String joinAuthors(List<String> authors) {
        return authors != null ? String.join(";", authors) : "";
    }
}
