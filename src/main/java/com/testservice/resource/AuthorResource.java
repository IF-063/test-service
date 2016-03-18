package com.testservice.resource;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.testservice.domain.Author;
import com.testservice.service.AuthorService;

@Path("/authors")
@Component
public class AuthorResource {

    @Autowired
    private AuthorService authorService;

    private final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();

    @PostConstruct
    public void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAuthors() {
        List<Author> authors = authorService.getAuthors();
        GenericEntity<List<Author>> entity = new GenericEntity<List<Author>>(authors) {};
        return Response.ok(entity).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response saveAuthor(Author author) {
        System.out.println(author);
        int id = authorService.saveAuthor(author);
        System.out.println(id);
        return Response.ok().header("Location", id).build();
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
    public Response updateAuthor(Author author) {
        authorService.updateAuthor(author);
        return Response.ok().build();
    }
}