//package org.TeamCodeDefy.route;
//
//import org.TeamCodeDefy.service.BookListApiService;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import static org.TeamCodeDefy.service.BookListApiService.createReadingListService;
//
///**
// * API routes for BookList. (Contains only the code needed to route
// * API requests to the correct controller/function.)
// */
//@Path("booklist")
//public class BookListRoutes {
//    /**
//     * Create a new BookList that a user can add books to.
//     *
//     * @param listName the list name
//     * @return Reading List ID
//     */
//    @POST
//    @Path("/create-list")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createReadingList(@QueryParam("listName") String listName) {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//        BookListApiService.createReadingListService();
//
//        // Convert Java types to JSON/Response as needed.
//
//        return null;
//    }
//
//    /**
//     * Delete a reading list based on the reading lists id.
//     *
//     * @param id the id
//     * @return Success or failure message.
//     */
//    @DELETE
//    @Path("reading-list/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteReadingList(@PathParam("id") String id) {
//        // Convert data to Java types as needed.
//        boolean deleted = BookListApiService.deleteReadingListService(id);
//
//        if (deleted) {
//            return Response.status(Response.Status.OK).entity("Reading list with ID " + id + " deleted.").build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
//        }
//    }
//
//    /**
//     * Get the reading list based on the reading lists id.
//     *
//     * @param id the id
//     * @return The reading list with all of the books in it.
//     */
//    @GET
//    @Path("reading-list/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getReadingList(@PathParam("id") String id) {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//
//    /**
//     * Add a book to the reading list using the books ISBN and reading list ID.
//     *
//     * @param id   the id
//     * @param isbn the isbn
//     * @return Success or failure message.
//     */
//    @POST
//    @Path("reading-list/{id}/add-book-by-isbn/{isbn}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addBookToReadingListByIsbn(@PathParam("id") String id, @PathParam("isbn") String isbn) {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//        boolean added = BookListApiService.addBookToReadingListByIsbnService(id, isbn);
//
//        if (added) {
//            return Response.status(Response.Status.OK).entity("Book with ISBN " + isbn + " added to reading list " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
//        }
//    }
//
//    /**
//     * Add a book to the reading list using user provided information and reading list ID.
//     * <p>
//     * The user will provide the book information as JSON in the body of the request.
//     *
//     * @param id the id
//     * @return Success or failure message.
//     */
//    @POST
//    @Path("reading-list/{id}/add-book-by-name")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addBookToReadingListByName(@PathParam("id") String id) {
//        // Call the function in BookListApiService to add a book to the reading list
//        boolean added = BookListApiService.addBookToReadingListByNameService(id, book);
//
//        if (added) {
//            return Response.status(Response.Status.OK).entity("Book with ISBN " + isbn + " added to reading list " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
//        }
//    }
//
//    /**
//     * Remove a book from the reading list using the books ID and reading list ID.
//     *
//     * @return Success or failure message.
//     */
//    @DELETE
//    @Path("reading-list/{id}/remove-book-by-id")
//    public Response removeBookFromReadingList() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//    /**
//     * Update the order/position of a book in the reading list using the books ID,
//     * reading list ID, and new position.
//     *
//     * @return Success or failure message.
//     */
//    @PUT
//    @Path("reading-list/{id}/update-book-reading-order/{bookId}/{newPosition}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBookReadingOrder() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//    /**
//     * Mark a book as read using the books ID and reading list ID.
//     *
//     * @return book read status
//     */
//    @PUT
//    @Path("reading-list/{id}/mark-book-as-read/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response setBookReadStatus() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//    /**
//     * Get a book using the books ID and reading list ID.
//     *
//     * @return book
//     */
//    @GET
//    @Path("reading-list/{id}/get-book/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getBook() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//
//    /**
//     * Update last page read response.
//     *
//     * @return response
//     */
//    @PUT
//    @Path("reading-list/{id}/update-last-page-read/{bookId}/{lastPageRead}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateLastPageRead() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//    /**
//     * Update a book using the books ID and reading list ID.
//     *
//     * @return response
//     */
//    @PUT
//    @Path("reading-list/{id}/update-book/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBook() {
//        // Convert data to Java types as needed.
//
//        // Call the function in BookListApiService.java.
//
//        // Convert Java types to JSON/Response as needed.
//        return null;
//    }
//
//}
