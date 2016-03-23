package com.testservice.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testservice.domain.Book;
import com.testservice.service.BookService;

@Path("/books")
@Component
public class BookResource extends GeneralResource {

    @Autowired
    private BookService bookService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBooks() {
        List<Book> books = bookService.loadAll();
        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {};
        return ok(entity);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveBook(Book book) {
        book = bookService.save(book);
        if (logging) {
            bookService.saveLogs(book);
        }
        return ok(book);
    }

    @DELETE
    public Response deleteBooks() {
        bookService.deleteAll();
        return NO_CONTENT;
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBook(@PathParam("id") int id) {
        Book book = bookService.load(id);
        if (book == null) {
            return NOT_FOUND;
        }
        return ok(book);
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateBook(Book book, @PathParam("id") int id) {
        book.setId(id);
        bookService.update(book);
        if (logging) {
            bookService.saveLogs(book);
        }
        return NO_CONTENT;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        bookService.delete(id);
        return NO_CONTENT;
    }

}