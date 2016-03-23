package com.testservice.config;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.testservice.domain.User;
import com.testservice.service.TokenService;

@Provider
@PreMatching
@Component
public class AuthorizationRequestFilter implements ContainerRequestFilter {

    private static final String TOKEN_NAME = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";

    @Autowired
    private TokenService tokenService;

    @PostConstruct
    private void init() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(TOKEN_NAME);
        if (token == null) {
            Map<String, List<String>> map = requestContext.getUriInfo().getQueryParameters();
            if (!CollectionUtils.isEmpty(map)) {
                token = map.get(TOKEN_NAME).get(0);
            }
        }
        if (token == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                             .entity("User cannot access the resource.")
                                             .build());
        } else {
            token = token.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            if (!tokenService.authenticate(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                                 .entity("User cannot access the resource.")
                                                 .build());
            }
            User user = tokenService.get(token);
            requestContext.setSecurityContext(new SecurityContext() {

                @Override
                public Principal getUserPrincipal() {
                    return user;
                }

                @Override
                public boolean isUserInRole(String role) {
                    return user.getRole().equals(role);
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return SecurityContext.BASIC_AUTH;
                }
            });
        }
    }
}