package org.TeamCodeDefy.service;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.persistance.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Controller class for the BookList API.
 *
 * All public functions in this class must be static
 */
public final class BookListApiService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private void BookListApiService() {}

    /**
     * Create a new BookList that a user can add books to.
     *
     * @param listName the list name
     * @return ReadingList
     */
    public static ReadingList createReadingListService(String listName) throws IllegalArgumentException {

        // Trim the list name to a max of 100 characters.
        listName = listName.trim();
        if (listName.length() == 0) {
            throw new IllegalArgumentException("List name cannot be empty.");
        }
        listName = listName.length() > 100 ? listName.substring(0,100) : listName;

        ReadingList readingList = new ReadingList();
        readingList.setListName(listName);

        // Insert the new reading list into the database.
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        int readingListId = readingListDao.insert(readingList);

        return readingListDao.getById(readingListId);
    }

    public static boolean deleteReadingListService(int id) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        ReadingList readingList = readingListDao.getById(id);

        if (readingList != null) {
            readingListDao.delete(readingList);
            return true;
        } else {
            return false;
        }
    }

    public static ReadingList getReadingListByIdService(int id) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        return readingListDao.getById(id);
    }


    public static boolean addBookToReadingListByIsbnService(String id, String isbn) {
        return false;
    }

    public static boolean removeBookFromReadingList(int readingListId, int bookId) {
        return false;
    }

    public static boolean updateReadingListOrder(int readingListId, int bookId, int newPosition) {

        // Get readingList from database
        ReadingList readingList = getReadingListByIdService(readingListId);

        // Get the book from the readingList using the bookId
        Book book = readingList.getBooks().stream()
            .filter(b -> b.getId() == bookId)
            .findFirst()
            .orElse(null);

        // If the book is found, update the readingListSequenceNumber of each book in the readingList
        if (book != null) {
            // Remove the book from the readingList
            readingList.getBooks().remove(book);
            // Iterate over the books in the readingList and update the book sequence number
            for (Book b : readingList.getBooks()) {
                if (b.getReadingListSequenceNumber() >= newPosition) {
                    b.setReadingListSequenceNumber(book.getReadingListSequenceNumber() + 1);
                }
            }
            book.setReadingListSequenceNumber(newPosition);
            readingList.getBooks().add(book);

            // Update the readingList in the database
            updateReadingList(readingList);
            return true;
        }
        return false;
    }

    public static void updateReadingList(ReadingList readingList) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        readingListDao.saveOrUpdate(readingList);
    }

    public static boolean setBookReadStatus(int readingListId, int bookId, boolean readStatus) {
        return false;
    }

    public static Book getBook(int bookId) {
        return null;
    }

    public static boolean updateLastPageRead(int readingListId, int bookId, int lastPageRead) {

        // Get readingList from database
        ReadingList readingList = getReadingListByIdService(readingListId);

        // Find the book in the reading list with the given bookId
        Book book = readingList.getBooks().stream()
            .filter(b -> b.getId() == bookId)
            .findFirst()
            .orElse(null);

        // If the book is found, update the last page read
        if (book != null) {
            book.setLastPageRead(lastPageRead);
            return true;
        }

        return false;
    }

    public static boolean updateBook(int readingListId, Book book) {

        return false;
    }

}








