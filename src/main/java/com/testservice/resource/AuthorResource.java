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

import com.testservice.domain.Author;
import com.testservice.domain.Book;
import com.testservice.service.AuthorService;
import com.testservice.service.BookService;

@Path("/delay/{delay}/authors/")
@Component
public class AuthorResource extends GeneralResource {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthors() {
        List<Author> authors = authorService.getAuthors();
        GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(authors) {};
        return ok(entity);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveAuthor(Author author) {
        author = authorService.saveAuthor(author);
        return ok(author);
    }

    @DELETE
    public Response deleteAuthors() {
        authorService.deleteAuthors();
        return NO_CONTENT;
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthor(@PathParam(value = "id") int id) {
        Author author = authorService.getAuthor(id);
        if (author == null) {
            return NOT_FOUND;
        }
        return ok(author);
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAuthor(Author author, @PathParam(value = "id") int id) {
        author.setId(id);
        authorService.updateAuthor(author);
        return NO_CONTENT;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam(value = "id") int id) {
        authorService.deleteAuthor(id);
        return NO_CONTENT;
    }

    @GET
    @Path("/{id}/books")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBooksByAuthor(@PathParam(value = "id") int id) {
        List<Book> books = bookService.getBooksByAuthor(id);
        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {};
        return ok(entity);
    }
}