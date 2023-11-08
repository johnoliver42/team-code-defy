package org.TeamCodeDefy.route;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * API routes for BookList. (Contains only the code needed to route
 * API requests to the correct controller/function.)
 */
public class BookListRoutes {


    /**
     * Create a new BookList that a user can add books to.
     *
     * @return
     */
    public Response createReadingList() {
        return null;
    }

    /**
     * Delete a reading list based on the reading lists id.
     *
     * @return Success or failure message.
     */
    public Response deleteReadingList() {
        return null;
    }

    /**
     * Get the reading list based on the reading lists id.
     *
     * @return The reading list with all of the books in it.
     */
    @GET
    @Path("stub")
    @Produces("application/json")
    public Response getReadingList() {
        return null;
    }


    /**
     * Add a book to the reading list using the books ISBN and reading list ID.
     *
     * @return Success or failure message.
     */
    public Response addBookToReadingListByIsbn() {
        return null;
    }

    /**
     * Add a book to the reading list using user provided information and reading list ID.
     *
     * The user will provide the book information as JSON in the body of the request.
     *
     * @return Success or failure message.
     */
    public Response addBookToReadingListName() {
        return null;
    }

    /**
     * Remove a book from the reading list using the books ID and reading list ID.
     *
     * @return Success or failure message.
     */
    public Response removeBookFromReadingList() {
        return null;
    }

    /**
     * Update the order/position of a book in the reading list using the books ID,
     * reading list ID, and new position.
     *
     * @return Success or failure message.
     */
    public Response updateBookReadingOrder() {
        return null;
    }

    /**
     * Mark a book as read using the books ID and reading list ID.
     *
     * @return
     */
    public Response setBookReadStatus() {
        return null;
    }

    /**
     * Get a book using the books ID and reading list ID.
     *
     * @return
     */
    public Response getBook() {
        return null;
    }




}
