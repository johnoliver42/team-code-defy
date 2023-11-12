package org.TeamCodeDefy.googleBooksApi;

import org.TeamCodeDefy.entities.Book;

/**
* Conversion methods for Google Book Response
* */
public class GoogleBookConversion {

    /**
     * Map Google Book Response from Google Books API to Book Entity
     **/
    public Book mapToBookEntity(GoogleBookResponse googleBookResponse, Book book) {

        book.setTitle(googleBookResponse.);

        return book;
    }
}
