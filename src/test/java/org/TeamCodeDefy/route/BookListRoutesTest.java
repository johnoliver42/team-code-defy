package org.TeamCodeDefy.route;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.service.BookListApiService;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookListRoutesTest {
  private BookListRoutes bookListRoutes;

  @Before
  public void setUp() {
      bookListRoutes = new BookListRoutes();

  }

    @Test
    public void createReadingListSuccess() {
//      String listName = "My Reading List";
//      ReadingList readingList = BookListApiService.createReadingList(listName);
//
//      assertNotNull(readingList);
//      assertEquals(listName, readingList.getListName());
   }

    @Test
    public void deleteReadingListSuccess() {
//        int listNameToBeDeleted = 15928;
//        boolean response = BookListApiService.deleteReadingList(listNameToBeDeleted);
//
//        assertEquals(Response.Status.OK.getStatusCode(), response);
    }

    @Test
    public void getReadingListSuccess() {
//          int id = 15928;
//          ReadingList readingList = BookListApiService.getReadingListById(id);
//          assertNotNull(readingList);
    }

    @Test
    public void addBookToReadingListByIsbnSuccess() {
//        int listId = 15928;
//        String isbn = "1234567890";
//        boolean response = BookListApiService.addBookToReadingListByIsbn(listId, isbn);
//
//        assertEquals(Response.Status.OK.getStatusCode(), response);
    }

    @Test
    public void addBookToReadingListByNameSuccess() {
//        int listId = 15928;
//        String bookName = "Total tertiary support";
//        boolean response = BookListApiService.addBookToReadingListByName(listId, bookName);
//
//        assertEquals(Response.Status.OK.getStatusCode(), response);
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