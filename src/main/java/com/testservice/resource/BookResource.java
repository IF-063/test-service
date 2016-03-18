package com.testservice.resource;

import java.net.URI;
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

@Path("/delay/{delay}/books")
@Component
public class BookResource extends GeneralResource {

    @Autowired
    private BookService bookService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBooks() {
        List<Book> books = bookService.getBooks();
        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveBook(Book book) {
        int id = bookService.saveBook(book);
        return Response.created(URI.create(String.valueOf(id))).build();
    }

    @DELETE
    public Response deleteBooks() {
        bookService.deleteBooks();
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBook(@PathParam(value = "id") int id) {
        Book book = bookService.getBook(id);
        if (book == null) {
            return NOT_FOUND;
        }
        return Response.ok(book).build();
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateBook(Book book, @PathParam(value = "id") int id) {
        book.setId(id);
        bookService.updateBook(book);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam(value = "id") int id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}