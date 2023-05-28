package com.beyonder;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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

    // Additional methods for PUT, DELETE can be added here as per your requirement.
}
