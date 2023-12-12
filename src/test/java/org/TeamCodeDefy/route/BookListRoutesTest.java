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
            Book bookFromDb = BookListApiService.getBook(book.getId());
            assertNotNull(bookFromDb);
            assertEquals(book.getReadingList().getId(), bookFromDb.getReadingList().getId());
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Test
    public void addBookToReadingListByNameSuccess() {
        // Get a book from the database to use for testing
        Book book = BookListApiService.getBook(1028);
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
            Book bookFromDb = BookListApiService.getBook(bookFromResponse.getId());
            assertNotNull(bookFromDb);
            assertEquals(bookFromResponse.getReadingList().getId(), bookFromDb.getReadingList().getId());
        } catch (Exception e) {
            logger.error(e);
        }

    }

    @Test
    public void removeBookFromReadingListSuccess() {
//      int listId = 15928;
//      int bookIdToRemove = 2673;
//
//      boolean response = BookListApiService.removeBookFromReadingList(listId, bookIdToRemove);
//      assertEquals(Response.Status.OK.getStatusCode(), response);
    }

    @Test
    public void updateBookReadingOrderSuccess() {
//      int listId = 15928;
//      int bookId = 1028;
//      int newOrder = 1;
//
//      boolean response = BookListApiService.updateReadingListOrder(listId, bookId, newOrder);
//      assertEquals(Response.Status.OK.getStatusCode(), response);
    }

    @Test
    public void setBookReadStatusSuccess() {
//      int bookId = 1028;
//      boolean isRead = true;
//
//      boolean response = BookListApiService.setBookReadStatus(bookId, isRead);
//      assertEquals(Response.Status.OK.getStatusCode(), response);
    }

    @Test
    public void getBookSuccess() {
//        int bookId = 1028;
//        Book book = BookListApiService.getBook(bookId);
//
//        assertNotNull(book);
//        assertEquals(bookId, book.getId());
    }

    @Test
    public void updateLastPageReadSuccess() {
//        int id = 123;
//        int bookId = 1028;
//        int lastPageRead = 438;
//
//        boolean response = BookListApiService.updateLastPageRead(id,bookId, lastPageRead);
//        assertEquals(Response.Status.OK.getStatusCode(), response);
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