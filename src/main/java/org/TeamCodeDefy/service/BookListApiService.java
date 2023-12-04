package org.TeamCodeDefy.service;

import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;

import org.TeamCodeDefy.persistance.GenericDao;
import org.TeamCodeDefy.persistance.GoogleBooksApiDao;
import org.TeamCodeDefy.utilities.BookConversion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Controller class for the BookList API.
 *
 * All public functions in this class must be static
 */
public final class BookListApiService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().getClass());

    private BookListApiService() {}

    /**
     * Create a new BookList that a user can add books to.
     *
     * @param listName the list name
     * @return ReadingList
     */
    public static ReadingList createReadingList(String listName) throws IllegalArgumentException {

        // Trim the list name to a max of 100 characters.
        listName = listName.trim();
        if (listName.isEmpty()) {
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

    public static boolean deleteReadingList(int id) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        ReadingList readingList = readingListDao.getById(id);

        if (readingList != null) {
            readingListDao.delete(readingList);
            return true;
        } else {
            return false;
        }
    }

    public static ReadingList getReadingListById(int readingListId) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        return readingListDao.getById(readingListId);
    }


    public static boolean addBookToReadingListByIsbn(int readingListId, String isbn) {

        // Get book from Google Books API
        Book bookTemp = new Book();
        GoogleBooksApiDao googleBooksApiDao = new GoogleBooksApiDao();
        BookConversion bookConversion = new BookConversion();
        Book book = bookConversion.mapToBookEntity(googleBooksApiDao.getGoogleBook(isbn), bookTemp);

        // Get reading list so that we can set the proper sequence number
        ReadingList readingList = getReadingListById(readingListId);

        // Add the book to the reading list
        readingList = addBookToReadingList(readingList, book);

        // Update the reading list in the database
        updateReadingList(readingList);

        return true;
    }

    public static boolean removeBookFromReadingList(int readingListId, int bookId) {
        return false;
    }

    public static boolean updateReadingListOrder(int readingListId, int bookId, int newPosition) {

        // Get readingList from database
        ReadingList readingList = getReadingListById(readingListId);

        // Get the book from the readingList using the bookId
        Book book = readingList.getBooks().stream()
            .filter(b -> b.getId() == bookId)
            .findFirst()
            .orElse(null);

        // If the book is found, update the readingListSequenceNumber of each book in the readingList
        if (book != null) {
            // Set the new readingListSequenceNumber for the book
            book.setReadingListSequenceNumber(newPosition);

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

    /**
     * Set or update the readingListSequenceNumber for a book in a readingList for existing books or
     * add the book with the readingListSequenceNumber to the readingList for new books.
     *
     * If the book does not have a readingListSequenceNumber, set it to the next available sequence number.
     *
     * @param readingList the readingList
     * @param book the book with the new readingListSequenceNumber set
     * @return ReadingList
     */
    private static ReadingList assignOrUpdateReadingListSequenceNumber(ReadingList readingList, Book book) {

        // If the book is already in the readingList, remove the book from the readingList so that we can update
        // the readingListSequenceNumber for the book and then add the book back to the readingList.
        readingList.getBooks().remove(book);

        List<Book> books = reOrderReadingListBookSequence(readingList);

        // Iterate over the books in the readingList and update the sequence number for each book as needed based on
        // the readingLIstSequenceNumber of the book being added.
        for (Book b : books) {
            if (b.getReadingListSequenceNumber() >= book.getReadingListSequenceNumber()) {
                b.setReadingListSequenceNumber(b.getReadingListSequenceNumber() + 1);
            }
        }

        // Add the book back to the readingList with the new readingListSequenceNumber
        books.add(book);
        readingList.setBooks(new HashSet<>(books));

        return readingList;
    }

    /**
     * Order/re-order the books in the readingList by the books readingListSequenceNumber to ensure that the sequence
     * numbers are sequential from 1 to the number of books in the readingList.
     *
     * @param readingList
     * @return
     */
    private static List<Book> reOrderReadingListBookSequence(ReadingList readingList) {
        // Sort the books in the readingList by the books readingListSequenceNumber
        List<Book> books = new ArrayList<>(readingList.getBooks());
        books.sort(Comparator.comparingInt(Book::getReadingListSequenceNumber));
        books.forEach(b -> logger.debug(b.getReadingListSequenceNumber()));

        // Check the readingListSequenceNumber for each book and update it to ensure that there are no gaps in the sequence
        // numbers to ensure that the sequence numbers are sequential from 1 to the number of books in the readingList
        for (int i = 0; i < books.size(); i++) {
            books.get(i).setReadingListSequenceNumber(i + 1);
        }
        return books;
    }

    /**
     * Add a book to a readingList.
     *
     * If the book does not have a readingListSequenceNumber, set it to the next available sequence number.
     *
     * @param readingList the readingList
     * @param book the book to add to the readingList
     * @return ReadingList
     */
    private static ReadingList addBookToReadingList(ReadingList readingList, Book book) {
        // If the book does not have a readingListSequenceNumber or the readingList is empty, set the
        // readingListSequenceNumber to the next available sequence number and add the book to the readingList.
        if (book.getReadingListSequenceNumber() == null || readingList.getBooks().isEmpty()) {
            book.setReadingListSequenceNumber(readingList.getBooks().size() + 1);
            readingList.getBooks().add(book);
            return readingList;
        } else {
            // We already have a readingListSequenceNumber for the book, so we need to update the readingListSequenceNumber
            return assignOrUpdateReadingListSequenceNumber(readingList, book);
        }
    }


    /**
     * Update a readingList in the database.
     * @param readingList
     */
    private static void updateReadingList(ReadingList readingList) {
        GenericDao<ReadingList> readingListDao = new GenericDao<>(ReadingList.class);
        readingListDao.saveOrUpdate(readingList);
    }

    public static boolean setBookReadStatus(int readingListId, int bookId, boolean readStatus) {
        return false;
    }

    /**
     * Get a book by id.
     *
     * @param bookId the book id
     * @return Book
     */
    public static Book getBook(int bookId) {
        // Get book from database
        GenericDao<Book> bookDao = new GenericDao<>(Book.class);
        return bookDao.getById(bookId);
    }

    public static boolean updateLastPageRead(int readingListId, int bookId, int lastPageRead) {

        // Get readingList from database
        ReadingList readingList = getReadingListById(readingListId);

        // Iterate over the books in the readingList and update the books last page read
        for (Book book : readingList.getBooks()) {
            if (book.getId() == bookId) {
                book.setLastPageRead(lastPageRead);
                return true;
            }
        }

        return false;
    }

    public static boolean updateBook(int readingListId, Book book) {

        // Get readingList from database
        ReadingList readingList = getReadingListById(readingListId);
        addBookToReadingList(readingList, book);

        // Update the readingList in the database
        updateReadingList(readingList);
        return true;
    }

}








