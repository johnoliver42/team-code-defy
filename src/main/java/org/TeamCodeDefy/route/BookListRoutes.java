package org.TeamCodeDefy.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ErrorResponse;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.service.BookListApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * API routes for BookList. (Contains only the code needed to route
 * API requests to the correct controller/function.)
 */
@Path("/reading-list")
public class BookListRoutes {

    private final Logger logger = LogManager.getLogger(this.getClass());




    /**
     * Create a new BookList that a user can add books to.
     *
     * @param listName the list name
     * @return Reading List ID
     */
    @GET
    @Path("create-list/{listName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReadingList(@PathParam("listName") String listName) {
        System.out.println("listName: " + listName);
        // Call the function in BookListApiService.java to create a new reading list
        ReadingList newReadingList = BookListApiService.createReadingList(listName);

        // Convert data to Java types as needed.
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(newReadingList);
        } catch (JsonProcessingException e) {
            logger.error("Error creating JSON response:", e);
            ErrorResponse errorResponse = new ErrorResponse("JSON mapping error", "Unable to map JSON response");
            try {
                String jsonError = mapper.writeValueAsString(errorResponse);
                return Response.status(500, jsonError).build();
            } catch (JsonProcessingException ex) {
                logger.error("Error creating JSON error response:", ex);
            }
        }
        return Response.ok(json, MediaType.APPLICATION_JSON).build();

    }

    /**
     * Delete a reading list based on the reading lists id.
     *
     * @param id the id
     * @return Success or failure message.
     */
    @GET
    @Path("{id}/delete-reading-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReadingList(@PathParam("id") String id) {
        // Convert data to Java types as needed.
        boolean deleted = BookListApiService.deleteReadingList(Integer.parseInt(id));

        if (deleted) {
            return Response.status(Response.Status.OK).entity("Reading list with ID " + id + " deleted.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    /**
     * Get the reading list based on the reading lists id.
     *
     * @param id the id
     * @return The reading list with all of the books in it.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadingList(@PathParam("id") String id) {
        ReadingList readingList = BookListApiService.getReadingListById(Integer.parseInt(id));
        System.out.println("readingList: " + readingList.getListName());

        if (readingList != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(readingList);
                return Response.status(200).entity(json).build();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Response.status(500).entity("Error creating JSON response").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    /**
     * Add a book to the reading list using the books ISBN and reading list ID.
     *
     * @param id   the id
     * @param isbn the isbn
     * @return Success or failure message.
     */
    @POST
    @Path("{id}/add-book-by-isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookToReadingListByIsbn(@PathParam("id") String id, @PathParam("isbn") String isbn) {
        // Call the function in BookListApiService.java to add book to the reading list
        boolean added = BookListApiService.addBookToReadingListByIsbn(Integer.parseInt(id), isbn);

        if (added) {
            return Response.status(Response.Status.OK).entity("Book with ISBN " + isbn + " added to reading list " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    /**
     * Add a book to the reading list using user provided information and reading list ID.
     *
     * The user will provide the book information as JSON in the body of the request.
     *
     * @param id the id
     * @return Success or failure message.
     */
    @POST
    @Path("{id}/add-book-by-name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookToReadingListByName(@PathParam("id") String id, String body) {
        Book book = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            book = mapper.readValue(body, Book.class);

            // Call the function in BookListApiService to add a book to the reading list
            Book newBook = BookListApiService.addBookToReadingListByName(Integer.parseInt(id), book);

            if (newBook != null) {
                // Encode newBook to JSON and return to user
                String json = mapper.writeValueAsString(newBook);
                return Response.status(Response.Status.OK).entity(json).build();
            } else {
                logger.error("Error new book returned null");
                ErrorResponse errorResponse = new ErrorResponse("JSON mapping error", "Unable to map JSON response");
                try {
                    String jsonError = mapper.writeValueAsString(errorResponse);
                    return Response.status(500, jsonError).build();
                } catch (JsonProcessingException ex) {
                    logger.error("Error creating JSON error response:", ex);
                }
            }

        } catch (JsonProcessingException e) {
            logger.error("Error mapping JSON to Book object:", e);
            ErrorResponse errorResponse = new ErrorResponse("JSON mapping error", "Unable to map JSON response");
            try {
                String jsonError = mapper.writeValueAsString(errorResponse);
                return Response.status(500, jsonError).build();
            } catch (JsonProcessingException ex) {
                logger.error("Error creating JSON error response:", ex);
                return null;
            }
        }
        return null;
    }

    /**
     * Remove a book from the reading list using the books ID and reading list ID.
     *
     * @return Success or failure message.
     */
    @DELETE
    @Path("{id}/remove-book-by-id/{bookId}")
    public Response removeBookFromReadingList(@PathParam("id") String id, @PathParam("bookId") String bookId) {
        // Call the function in BookListApiService.java to remove the book from the reading list
        boolean removed = BookListApiService.removeBookFromReadingList(Integer.parseInt(id), Integer.parseInt(bookId));

        if (removed) {
            return Response.status(Response.Status.OK).entity("Book with ID " + bookId + " removed from reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found in reading list").build();
        }
    }
//
//    /**
//     * Update the order/position of a book in the reading list using the books ID,
//     * reading list ID, and new position.
//     *
//     * @return Success or failure message.
//     */
//    @PUT
//    @Path("{id}/update-book-reading-order/{bookId}/{newPosition}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBookReadingOrder(@PathParam("id") String id, @PathParam("bookId") String bookId, @PathParam("newPosition") int newPosition ){
//        boolean updated = bookListApiService.updateBookReadingOrderService(id, bookId, newPosition);
//
//        if (updated) {
//            return Response.status(Response.Status.OK).entity("Book order updated in reading list with ID " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
//        }
//    }
//
//    /**
//     * Mark a book as read using the books ID and reading list ID.
//     *
//     * @return book read status
//     */
//    @PUT
//    @Path("{id}/mark-book-as-read/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response setBookReadStatus(@PathParam("id") String id, @PathParam("bookId") String bookId) {
//        boolean markedAsRead = bookListApiService.setBookReadStatusService(id, bookId);
//
//        if (markedAsRead) {
//            return Response.status(Response.Status.OK).entity("Book marked as read in reading list with ID " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
//        }
//    }
//
//    /**
//     * Get a book using the books ID and reading list ID.
//     *
//     * @return book
//     */
//    @GET
//    @Path("{id}/get-book/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getBook(@PathParam("id") String id, @PathParam("bookId") String bookId) {
//        Book book = bookListApiService.getBookService(id, bookId);
//
//        if (book != null) {
//            ObjectMapper mapper = new ObjectMapper();
//            try {
//                String json = mapper.writeValueAsString(book);
//                return Response.status(Response.Status.OK).entity(json).build();
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                return Response.status(500).entity("Error creating JSON response").build();            }
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
//        }
//    }
//
//    /**
//     * Update last page read response.
//     *
//     * @return response
//     */
//    @PUT
//    @Path("{id}/update-last-page-read/{bookId}/{lastPageRead}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateLastPageRead(@PathParam("id") String id, @PathParam("bookId") String bookId, @PathParam("lastPageRead") int lastPageRead) {
//        boolean updated = bookListApiService.updateLastPageReadService(id, bookId, lastPageRead);
//
//        if (updated) {
//            return Response.status(Response.Status.OK).entity("Last page read updated for book in reading list with ID " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
//        }
//    }
//
//    /**
//     * Update a book using the books ID and reading list ID.
//     *
//     * @return response
//     */
//    @PUT
//    @Path("{id}/update-book/{bookId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateBook(@PathParam("id") String id, @PathParam("bookId") String bookId) {
//        boolean updated = bookListApiService.updateBook(id, bookId);
//        if (updated) {
//            return Response.status(Response.Status.OK).entity("Book with ID " + bookId + " updated in reading list with ID " + id).build();
//        } else {
//            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
//        }
//    }
}