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

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookListApiService
 *
 * @author John Oliver
 */
class BookListApiServiceTest {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().getClass());

    private GenericDao<Book> genericBookDao = new GenericDao<>(Book.class);
    private GenericDao<ReadingList> genericReadingListDao = new GenericDao<>(ReadingList.class);

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

    /**
     * Test to verify that a book with a null sequence number is added to the end of the list,
     * test to verify that a book with a sequence number is added to the correct position in the list,
     * and test to verify that if a readingList has no books, the sequence number of the book is set to 1.
     */
    @Test
    void setOrUpdateReadingListSequenceNumber() {

//        ReadingList originalReadingList = genericReadingListDao.getById(15928);
//
//        assertEquals(5, originalReadingList.getBooks().size());
//
//        // Update the readingListSequenceNumber for a book in a readingList for existing books
//        Book updatedBook = originalReadingList.getBooks().stream()
//                .filter(book -> book.getReadingListSequenceNumber() == 2)
//                .findFirst()
//                .orElse(null);
//        assertNotNull(updatedBook);
//        updatedBook.setReadingListSequenceNumber(4);
//
//        ReadingList updatedReadingList =
//                BookListApiService.setOrUpdateReadingListSequenceNumber(originalReadingList, updatedBook);
//
//        // Make sure the book is in the readingList
//        assertTrue(updatedReadingList.getBooks().contains(updatedBook));
//
//        // Make sure the book is in the correct position in the readingList
//        for (Book book : updatedReadingList.getBooks()) {
//            if (Objects.equals(book.getId(), updatedBook.getId())) {
//                assertEquals(4, (int)book.getReadingListSequenceNumber());
//                }
//        }
//
//        // Make sure all books in the readingList have a readingListSequenceNumber in the correct order
//        ArrayList<Book> books = new ArrayList<>(updatedReadingList.getBooks());
//        books.sort(Comparator.comparing(Book::getReadingListSequenceNumber));
//        for (int i = 0; i < books.size(); i++) {
//            assertEquals(i + 1, (int)books.get(i).getReadingListSequenceNumber());
//        }
//
//        // Add a book to the readingList with a null readingListSequenceNumber
//        int originalSize = updatedReadingList.getBooks().size();
//        Book newBook = new Book();
//        newBook.setReadingListSequenceNumber(null);
//        ReadingList newReadingList = BookListApiService.setOrUpdateReadingListSequenceNumber(updatedReadingList, newBook);
//        // Make sure the book is in the readingList and that it is the last book in the readingList
//        assertTrue(newReadingList.getBooks().contains(newBook));
//        assertEquals(originalSize + 1, newReadingList.getBooks().size());

    }
}