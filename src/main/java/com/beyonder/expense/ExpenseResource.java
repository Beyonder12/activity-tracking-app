package com.beyonder.expense;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseResource {

    @GET
    @Path("/test")
    public String test(){
        return "hello";
    }

    @GET
    public List<Expense> getAll() {
        return Expense.listAll();
    }

    @POST
    @Transactional
    public Expense create(Expense book) {
        book.persist();
        return book;
    }

    @GET
    @Path("/{id}")
    public Expense getExpense(@PathParam("id") Long id) {
        return Expense.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateExpense(@PathParam("id") Long id, Expense expense) {
        Expense entity = Expense.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }

        entity.title = expense.title;
        entity.author = expense.author;
        entity.description = expense.description;
        entity.total = expense.total;

        return Response.ok(entity).status(200).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteExpense(@PathParam("id") Long id) {
        Expense entity = Expense.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Book with id of " + id + " does not exist.", 404);
        }

        entity.delete();
        return Response.noContent().build();
    }

    // Additional methods for PUT, DELETE can be added here as per your requirement.
}
