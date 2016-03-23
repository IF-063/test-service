package com.testservice.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.util.CollectionUtils;

@Provider
@PreMatching
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final String TOKEN_NAME = "Authorization";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(TOKEN_NAME);
        if (token == null) {
            Map<String, List<String>> map = requestContext.getUriInfo().getQueryParameters();
            if (!CollectionUtils.isEmpty(map)) {
                token = map.get(TOKEN_NAME).get(0);
            }
        }
        if (TokenStore.STORE.gerUserName(token) == null) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).entity("User cannot access the resource.").build());
        }
    }
}