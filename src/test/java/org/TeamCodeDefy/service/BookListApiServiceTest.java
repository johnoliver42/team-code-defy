package org.TeamCodeDefy.service;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.persistance.GenericDao;
import org.TeamCodeDefy.util.UnitTestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static  org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for BookListApiService
 *
 * @author John Oliver
 */
class BookListApiServiceTest {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().getClass());



    @BeforeEach
    void setUp() {
        UnitTestUtil.createTestDatabase();
        UnitTestUtil.createTestData();
    }

    /** Test to verify that a new readingList is created with a listName and that the listName is trimmed to 100 characters.
     *
    */
    @Test
    void createReadingListSuccess() {
        logger.info("Testing createReadingListSuccess");
        ReadingList readingList = BookListApiService.createReadingList("Test List Name");
        assertTrue(readingList.getId() > 0);
        assertEquals("Test List Name", BookListApiService.getReadingListById(readingList.getId()).getListName());
        logger.info("ID of new readingList: " + readingList.getId());
    }


    @Test
    void deleteReadingList() {
        logger.info("Testing deleteReadingList");
        // Get the reading list to delete
        ReadingList readingList = BookListApiService.getReadingListById(15928);
        // Delete the reading list
        BookListApiService.deleteReadingList(readingList.getId());
        // Make sure the reading list is deleted
        assertNull(BookListApiService.getReadingListById(readingList.getId()));
        assertThat(BookListApiService.getReadingListById(15928), nullValue());
    }

    @Test
    void getReadingListById() {
        logger.info("Testing getReadingListById");
        ReadingList readingList = BookListApiService.getReadingListById(15928);
        assertEquals("Coralie Daniel DVM", readingList.getListName());
    }

    @Test
    void addBookToReadingListByIsbn() {
        // Get a reading list to add the book to
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        // Get the book by ISBN from google books
        BookListApiService.addBookToReadingListByIsbn(readingListBefore.getId(), "9780486415871");
        // Get the reading list after the book is added
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        // Make sure the book is in the reading list
        assertEquals(readingListBefore.getBooks().size() + 1, readingListAfter.getBooks().size());

    }

    @Test
    void removeBookFromReadingList() {
        // Get the reading list to remove the book from
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        BookListApiService.removeBookFromReadingList(readingListBefore.getId(), 104620);
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        assertEquals(readingListBefore.getBooks().size() - 1, readingListAfter.getBooks().size());
    }

    @Test
    void updateReadingListOrder() {
        // Get the reading list to update
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        // Get the book to update
        Book bookToUpdate = readingListBefore.getBooks().stream()
                .filter(book -> book.getReadingListSequenceNumber() == 2)
                .findFirst()
                .orElse(null);

        // Update the reading list
        BookListApiService.updateReadingListOrder(readingListBefore.getId(), bookToUpdate.getId(),4);
        // Get the reading list after the update
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        // Iterate over the book list and make sure the book is in the correct position
        for (Book book : readingListAfter.getBooks()) {
            if (Objects.equals(book.getId(), bookToUpdate.getId())) {
                assertEquals(4, (int)book.getReadingListSequenceNumber());
            }
        }
    }

    @Test
    void setBookReadStatus() {
        // Get the reading list to update
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        // Get the book to update
        Book bookToUpdate = readingListBefore.getBooks().stream()
                .filter(book -> book.getReadingListSequenceNumber() == 2)
                .findFirst()
                .orElse(null);
        // Update the reading list
        BookListApiService.setBookReadStatus(readingListBefore.getId(), bookToUpdate.getId(), true);
        // Get the reading list after the update
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        // Iterate over the book list and make sure the book is in the correct position
        for (Book book : readingListAfter.getBooks()) {
            if (Objects.equals(book.getId(), bookToUpdate.getId())) {
                assertTrue(book.getIsRead());
            }
        }
    }

    @Test
    void getBook() {
        // Get a book from the database
        Book book = BookListApiService.getBook(15928,104620);
        Book book2 = BookListApiService.getBook(15928,104620);
        // Make sure the book is not null
        assertNotNull(book);
        assertEquals((int)book.getId(), (int)104620);
        assertEquals((int)book.getId(), (int)book2.getId());
    }

    @Test
    void updateLastPageRead() {
        // Get the reading list to update
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        // Get the book to update
        Book bookToUpdate = readingListBefore.getBooks().stream()
                .filter(book -> book.getId() == 104620)
                .findFirst()
                .orElse(null);
        // Update the last page read
        BookListApiService.updateLastPageRead(readingListBefore.getId(), bookToUpdate.getId(), 100);
        // Get the reading list after the update
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        // Iterate over the book list and make sure the book is in the correct position
        for (Book book : readingListAfter.getBooks()) {
            if (Objects.equals(book.getId(), bookToUpdate.getId())) {
                assertEquals(100, (int)book.getLastPageRead());
            }
        }
    }

    @Test
    void updateBookSuccess() {
        // Get a reading list and a book to update
        ReadingList readingListBefore = BookListApiService.getReadingListById(15928);
        Book bookToUpdate = readingListBefore.getBooks().stream()
                .filter(book -> book.getId() == 104620)
                .findFirst()
                .orElse(null);
        // Update the book
        bookToUpdate.setAuthor("Test Author");
        bookToUpdate.setTitle("Test Title");
        bookToUpdate.setPageCount(100);
        bookToUpdate.setAverageRating("5");
        bookToUpdate.setDescription("Test Description");
        bookToUpdate.setThumbnailLink("Test Thumbnail Link");
        BookListApiService.updateBook(readingListBefore.getId(),bookToUpdate);
        // Get the reading list after the update
        ReadingList readingListAfter = BookListApiService.getReadingListById(15928);
        // Iterate over the book list and make sure the book is in the correct position
        for (Book book : readingListAfter.getBooks()) {
            if (Objects.equals(book.getId(), bookToUpdate.getId())) {
                assertEquals("Test Author", book.getAuthor());
                assertEquals("Test Title", book.getTitle());
                assertEquals(100, (int)book.getPageCount());
                assertEquals("5", book.getAverageRating());
                assertEquals("Test Description", book.getDescription());
                assertEquals("Test Thumbnail Link", book.getThumbnailLink());
            }
        }
    }
}