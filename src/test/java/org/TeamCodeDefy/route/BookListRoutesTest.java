package org.TeamCodeDefy.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.service.BookListApiService;
import org.TeamCodeDefy.util.UnitTestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;
import java.lang.invoke.MethodHandles;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookListRoutesTest {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().getClass());
    private BookListRoutes bookListRoutes;

    @Before
    public void setUp() {
        bookListRoutes = new BookListRoutes();
        UnitTestUtil.createTestDatabase();
        UnitTestUtil.createTestData();
    }

    @Test
    public void createReadingListSuccess() {
        try (Response response = bookListRoutes.createReadingList("Test List Name");) {
            String jsonResponse =  (String)response.getEntity();
            assertNotNull(jsonResponse);
            // Convert the response json to a ReadingList object
            ObjectMapper mapper = new ObjectMapper();
            ReadingList readingList = null;
            readingList = mapper.readValue(jsonResponse, ReadingList.class);
            assertNotNull(readingList);
            assertEquals("Test List Name", readingList.getListName());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void deleteReadingListSuccess() {
        String id = "15928";
        try (Response response = bookListRoutes.deleteReadingList(id);) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Try to get the deleted reading list from the database
            ReadingList readingList = BookListApiService.getReadingListById(Integer.parseInt(id));
            assertNull(readingList);

        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void getReadingListSuccess() {
          String id = "15928";
            try (Response response = bookListRoutes.getReadingList(id);) {
                String jsonResponse =  (String)response.getEntity();
                assertNotNull(jsonResponse);
                // Convert the response json to a ReadingList object
                ObjectMapper mapper = new ObjectMapper();
                ReadingList readingList = null;
                readingList = mapper.readValue(jsonResponse, ReadingList.class);
                assertNotNull(readingList);
                assertEquals(Integer.parseInt(id), (int)readingList.getId());
            } catch (Exception e) {
                logger.error(e);
            }
    }

    @Test
    public void addBookToReadingListByIsbnSuccess() {

        // Use bookListRoutes to add a book to a reading list using the isbn
        try (Response response = bookListRoutes.addBookToReadingListByIsbn("15928", "9780062316097");) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the response
            String jsonResponse =  (String)response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            Book book = mapper.readValue(jsonResponse, Book.class);
            assertNotNull(book);
            assertEquals("9780062316097", book.getIsbn());
            Book bookFromDb = BookListApiService.getBook(15928,book.getId());
            assertNotNull(bookFromDb);
            assertEquals(book.getReadingList().getId(), bookFromDb.getReadingList().getId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void addBookToReadingListByNameSuccess() {
        // Get a book from the database to use for testing
        Book book = BookListApiService.getBook(692064,1028);
        book.setId(null);
        book.setIsbn(null);

        // Convert the book to json
        ObjectMapper mapper = new ObjectMapper();
        String jsonBook = null;
        try {
            jsonBook = mapper.writeValueAsString(book);
        } catch (Exception e) {
            logger.error(e);
        }

        // Use bookListRoutes to add a book to a reading list using the book name
        try (Response response = bookListRoutes.addBookToReadingListByName("15928", jsonBook);) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the response
            String jsonResponse =  (String)response.getEntity();
            Book bookFromResponse = mapper.readValue(jsonResponse, Book.class);
            assertNotNull(bookFromResponse);
            assertEquals(book.getTitle(), bookFromResponse.getTitle());
            Book bookFromDb = BookListApiService.getBook(692064, bookFromResponse.getId());
            assertNotNull(bookFromDb);
            assertEquals(bookFromResponse.getReadingList().getId(), bookFromDb.getReadingList().getId());
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @Test
    public void removeBookFromReadingListSuccess() {
        // Use bookListRoutes to remove a book from a reading list
        try (Response response = bookListRoutes.removeBookFromReadingList("15928", "863592");) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Try to get the deleted book from the database
            Book book = BookListApiService.getBook(15928,863592);
            assertNull(book);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void updateBookReadingOrderSuccess() {
        // Get a reading list from the database to use for testing
        ReadingList readingList = BookListApiService.getReadingListById(15928);
        // Get a book from the reading list to use for testing
        Book book = readingList.getBooks().toArray(Book[]::new)[0];
        book.setReadingListSequenceNumber((book.getReadingListSequenceNumber() >= 2) ? 1 : 3);

        // Use bookListRoutes to update the reading order of a book in a reading list
        try (Response response = bookListRoutes.updateBookReadingOrder(15928, book.getId(), book.getReadingListSequenceNumber());) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the database
            Book bookFromResponse = BookListApiService.getBook(15928,book.getId());
            assertEquals(book.getReadingListSequenceNumber(), bookFromResponse.getReadingListSequenceNumber());

        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void setBookReadStatusSuccess() {
        // Get a reading list from the database to use for testing
        ReadingList readingList = BookListApiService.getReadingListById(15928);
        // Get a book from the reading list to use for testing
        Book book = readingList.getBooks().toArray(Book[]::new)[0];
        book.setIsRead(!book.getIsRead());

        // Use bookListRoutes to update the read status of a book in a reading list
        try (Response response = bookListRoutes.setBookReadStatus(15928, book.getId(), book.getIsRead());) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the database
            Book bookFromResponse = BookListApiService.getBook(15928,book.getId());
            assertEquals(book.getIsRead(), bookFromResponse.getIsRead());

        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void getBookSuccess() {
        // Get a book from the database to use for testing
        Book book = BookListApiService.getBook(15928,104620);

        // Use bookListRoutes to get a book from a reading list
        try (Response response = bookListRoutes.getBook(15928, book.getId());) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the response
            String jsonResponse =  (String)response.getEntity();
            ObjectMapper mapper = new ObjectMapper();
            Book bookFromResponse = mapper.readValue(jsonResponse, Book.class);
            assertNotNull(bookFromResponse);
            assertEquals(book.getId(), bookFromResponse.getId());
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @Test
    public void updateLastPageReadSuccess() {
        // Get a reading list from the database to use for testing
        ReadingList readingList = BookListApiService.getReadingListById(15928);
        // Get a book from the reading list to use for testing
        Book book = readingList.getBooks().toArray(Book[]::new)[0];
        book.setLastPageRead(book.getLastPageRead() + 1);

        // Use bookListRoutes to update the last page read of a book in a reading list
        try (Response response = bookListRoutes.updateLastPageRead(15928, book.getId(), book.getLastPageRead());) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            // Get the book from the database
            Book bookFromResponse = BookListApiService.getBook(15928,book.getId());
            assertEquals(book.getId(), bookFromResponse.getId());
            assertEquals(book.getLastPageRead(), bookFromResponse.getLastPageRead());

        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void updateBookSuccess() {
//        int newId = 123;
//        int bookId = 1028;
//
//        boolean response = BookListApiService.updateBook(newId, bookId);
//        assertEquals(Response.Status.OK.getStatusCode(), response);
    }
}