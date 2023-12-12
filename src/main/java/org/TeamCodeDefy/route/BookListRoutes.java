package org.TeamCodeDefy.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.TeamCodeDefy.entities.Book;
import org.TeamCodeDefy.entities.ReadingList;
import org.TeamCodeDefy.service.BookListApiService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("reading-list")
public class BookListRoutes {
    @POST
    @Path("/create-list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReadingList(@QueryParam("listName") String listName) {
        try {
            ReadingList newList = BookListApiService.createReadingListService(listName);

            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(Map.of("listName", listName, "newList", newList));

            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();
        } catch (IllegalArgumentException e) {
            // Handle the specific exception for an invalid list name
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing JSON response", e);
        }
    }

    @DELETE
    @Path("reading-list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteReadingList(@PathParam("id") int id) {
        boolean deleted = BookListApiService.deleteReadingListService(id);

        if (deleted) {
            return Response.status(Response.Status.OK).entity("Reading list with ID " + id + " deleted.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    @GET
    @Path("reading-list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadingList(@PathParam("id") int id) {
        ReadingList readingList = BookListApiService.getReadingListByIdService(id);

        if (readingList != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonResponse = mapper.writeValueAsString(readingList);
                return Response.status(Response.Status.OK).entity(jsonResponse).build();
            } catch (JsonProcessingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating JSON response").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    @POST
    @Path("/{id}/add-book-by-isbn/{isbn}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookToReadingListByIsbn(@PathParam("id") int id, @PathParam("isbn") int isbn) {
        boolean added = BookListApiService.addBookToReadingListByIsbnService(id, isbn);

        if (added) {
            return Response.status(Response.Status.OK).entity("Book with ISBN " + isbn + " added to reading list " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    @POST
    @Path("reading-list/{id}/add-book-by-name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBookToReadingListByName(@PathParam("id") int id, String bookName) {
        boolean added = BookListApiService.addBookToReadingListByName(id, bookName);

        if (added) {
            return Response.status(Response.Status.OK).entity("Book with name " + bookName + " added to reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Reading list with ID " + id + " not found.").build();
        }
    }

    @DELETE
    @Path("reading-list/{id}/remove-book-by-id/{bookId}")
    public Response removeBookFromReadingList(@PathParam("id") int id, @PathParam("bookId") int bookId) {
        boolean removed = BookListApiService.removeBookFromReadingListById(id, bookId);

        if (removed) {
            return Response.status(Response.Status.OK).entity("Book with ID " + bookId + " removed from reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book not found in reading list").build();
        }
    }

    @PUT
    @Path("reading-list/{id}/update-book-reading-order/{bookId}/{newPosition}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBookReadingOrder(@PathParam("id") int id, @PathParam("bookId") int bookId, @PathParam("newPosition") int newPosition) {
        boolean updated = bookListApiService.updateBookReadingOrder(id, bookId, newPosition);

        if (updated) {
            return Response.status(Response.Status.OK).entity("Book order updated in reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
        }
    }

    @PUT
    @Path("reading-list/{id}/mark-book-as-read/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setBookReadStatus(@PathParam("id") int id, @PathParam("bookId") int bookId) {
        boolean markedAsRead = bookListApiService.setBookReadStatus(id, bookId);

        if (markedAsRead) {
            return Response.status(Response.Status.OK).entity("Book marked as read in reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
        }
    }

    @GET
    @Path("{id}/get-book/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") int id, @PathParam("bookId") int bookId) {
        Book book = BookListApiService.getBook(id, bookId);

        if (book != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String jsonResponse = mapper.writeValueAsString(book);
                return Response.status(Response.Status.OK).entity(jsonResponse).build();
            } catch (JsonProcessingException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating JSON response").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
        }
    }

    @PUT
    @Path("/{id}/update-last-page-read/{bookId}/{lastPageRead}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLastPageRead(@PathParam("id") int id, @PathParam("bookId") int bookId, @PathParam("lastPageRead") int lastPageRead) {
        boolean updated = BookListApiService.updateLastPageRead(id, bookId, lastPageRead);

        if (updated) {
            return Response.status(Response.Status.OK).entity("Last page read updated for book in reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
        }
    }

    @PUT
    @Path("/{id}/update-book/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("id") int id, @PathParam("bookId") int bookId) {
        boolean updated = BookListApiService.updateBook(id, bookId);

        if (updated) {
            return Response.status(Response.Status.OK).entity("Book with ID " + bookId + " updated in reading list with ID " + id).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Book or reading list not found.").build();
        }
    }
}
