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
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testservice.domain.Author;
import com.testservice.domain.Book;
import com.testservice.service.AuthorService;
import com.testservice.service.BookService;

@Path("/authors/")
@Component
public class AuthorResource extends GeneralResource {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthors(SecurityContext securityContext) {
        List<Author> authors = authorService.loadAll();
        GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(authors) {};
        return ok(entity);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveAuthor(Author author) {
        author = authorService.save(author);
        if (logging) {
            authorService.saveLogs(author);
        }
        return ok(author);
    }

    @DELETE
    public Response deleteAuthors() {
        authorService.deleteAll();
        return NO_CONTENT;
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthor(@PathParam("id") int id) {
        Author author = authorService.load(id);
        if (author == null) {
            return NOT_FOUND;
        }
        return ok(author);
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAuthor(Author author, @PathParam("id") int id) {
        author.setId(id);
        authorService.update(author);
        if (logging) {
            authorService.saveLogs(author);
        }
        return NO_CONTENT;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        authorService.delete(id);
        return NO_CONTENT;
    }

    @GET
    @Path("/{id}/books")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getBooksByAuthor(@PathParam("id") int id) {
        List<Book> books = bookService.getBooksByAuthor(id);
        GenericEntity<List<Book>> entity = new GenericEntity<List<Book>>(books) {};
        return ok(entity);
    }
}