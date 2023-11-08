package org.TeamCodeDefy.route;

import org.TeamCodeDefy.service.BookListApiService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static org.TeamCodeDefy.service.BookListApiService.createReadingListService;

/**
 * API routes for BookList. (Contains only the code needed to route
 * API requests to the correct controller/function.)
 */
@Path("booklist")
public class BookListRoutes {


    /**
     * Create a new BookList that a user can add books to.
     *
     * @return Reading List ID
     */
    @GET
    @Path("/create-list")
    @Produces
    public Response createReadingList(@QueryParam("listName") String listName) {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.
        BookListApiService.createReadingListService();

        // Convert Java types to JSON/Response as needed.

        return null;
    }

    /**
     * Delete a reading list based on the reading lists id.
     *
     * @return Success or failure message.
     */
    @GET
    @Path("reading-list/{id}/delete")
    @Produces("application/json")
    public Response deleteReadingList(@PathParam("id") String id) {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
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
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.


        // Convert Java types to JSON/Response as needed.
        return null;
    }


    /**
     * Add a book to the reading list using the books ISBN and reading list ID.
     *
     * @return Success or failure message.
     */
    @POST
    @Path("reading-list/{id}/add-book-by-isbn/{isbn}")
    @Produces("application/json")
    public Response addBookToReadingListByIsbn(@PathParam("id") String id, @PathParam("isbn") String isbn) {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Add a book to the reading list using user provided information and reading list ID.
     *
     * The user will provide the book information as JSON in the body of the request.
     *
     * @return Success or failure message.
     */
    @POST
    @Path("reading-list/{id}/add-book-by-name")
    @Produces("application/json")
    public Response addBookToReadingListByName() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Remove a book from the reading list using the books ID and reading list ID.
     *
     * @return Success or failure message.
     */
    public Response removeBookFromReadingList() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Update the order/position of a book in the reading list using the books ID,
     * reading list ID, and new position.
     *
     * @return Success or failure message.
     */
    public Response updateBookReadingOrder() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Mark a book as read using the books ID and reading list ID.
     *
     * @return
     */
    public Response setBookReadStatus() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Get a book using the books ID and reading list ID.
     *
     * @return
     */
    public Response getBook() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }


    /**
     *
     * @return
     */
    public Response updateLastPageRead() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

    /**
     * Update a book using the books ID and reading list ID.
     *
     * @return
     */
    public Response updateBook() {
        // Convert data to Java types as needed.

        // Call the function in BookListApiService.java.

        // Convert Java types to JSON/Response as needed.
        return null;
    }

}
