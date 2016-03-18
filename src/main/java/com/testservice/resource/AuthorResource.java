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

import com.testservice.domain.Author;
import com.testservice.service.AuthorService;

@Path("/delay/{delay}/authors/")
@Component
public class AuthorResource extends GeneralResource {

    @Autowired
    private AuthorService authorService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthors() {
        List<Author> authors = authorService.getAuthors();
        GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(authors) {
        };
        return Response.ok(entity).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveAuthor(Author author) {
        int id = authorService.saveAuthor(author);
        return Response.created(URI.create(String.valueOf(id))).build();
    }

    @DELETE
    public Response deleteAuthors() {
        authorService.deleteAuthors();
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthor(@PathParam(value = "id") int id) {
        Author author = authorService.getAuthor(id);
        if (author == null) {
            return NOT_FOUND;
        }
        return Response.ok(author).build();
    }

    @POST
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateAuthor(Author author, @PathParam(value = "id") int id) {
        author.setId(id);
        authorService.updateAuthor(author);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam(value = "id") int id) {
        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }
}