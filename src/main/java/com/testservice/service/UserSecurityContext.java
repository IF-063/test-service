package com.testservice.service;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.testservice.domain.User;

public class UserSecurityContext implements SecurityContext {

    private User user;

    public UserSecurityContext(User user) {
        this.user = user;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public boolean isUserInRole(String arg0) {
        return user.getRole().equals(arg0);
    }
}