package com.testservice.resource;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class GeneralResource {

    @QueryParam("delay")
    private int delay;

    @QueryParam("logging")
    protected boolean logging;

    protected final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();
    protected final Response NO_CONTENT = Response.status(Response.Status.NO_CONTENT).build();

    @PostConstruct
    private void init() {
        if (delay > 0) {
            delay();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    private void delay() {
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Response ok(Object entity) {
        return Response.ok().entity(entity).build();
    }
}