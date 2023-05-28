package com.beyonder;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @GET
    @Path("/test")
    public String test(){
        return "hello";
    }

    @GET
    public List<Book> getAll() {
        return Book.listAll();
    }

    @POST
    @Transactional
    public Book create(Book book) {
        book.persist();
        return book;
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") Long id) {
        return Book.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBook(@PathParam("id") Long id, Book book) {
        Book entity = Book.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }

        entity.title = book.title;
        entity.author = book.author;

        return Response.ok(entity).status(200).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBook(@PathParam("id") Long id) {
        Book entity = Book.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }

        entity.delete();
        return Response.noContent().build();
    }

    // Additional methods for PUT, DELETE can be added here as per your requirement.
}
