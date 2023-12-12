package org.TeamCodeDefy.utilities;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.googleBooksApi.GoogleBookResponse;
import org.TeamCodeDefy.persistance.GoogleBooksApiDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookConversionTest {

    GoogleBooksApiDao dao;
    BookConversion converter;
    @BeforeEach
    void setUp() {
        dao = new GoogleBooksApiDao();
        converter = new BookConversion();
    }
    @Test
    void convertBookSuccess() {
        String isbn = "9781101970164";
        GoogleBookResponse gbResponse = dao.getGoogleBook(isbn);
        Book book = new Book();
        book = converter.mapToBookEntity(gbResponse, book);

        assertEquals("Elastic", book.getTitle());
        assertEquals("Leonard Mlodinow", book.getAuthor());
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    void convertBookMultipleAuthors() {
        String isbn = "9780486415871";
        GoogleBookResponse gbResponse = dao.getGoogleBook(isbn);
        Book book = new Book();
        book = converter.mapToBookEntity(gbResponse, book);

        assertEquals("Crime and Punishment", book.getTitle());
        assertEquals("Fyodor Dostoyevsky;Constance Garnett", book.getAuthor());
        assertEquals(isbn, book.getIsbn());
    }
}