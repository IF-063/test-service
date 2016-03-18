package com.testservice.resource;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class GeneralResource {

    @PathParam(value = "delay")
    private int delay;

    private static final Random random = new Random();

    protected final Response NOT_FOUND = Response.status(Response.Status.NOT_FOUND).build();

    private void delay() {
        int sleepTime = random.nextInt(delay) + 1;
        System.out.println("delay: " + sleepTime);
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    private void init() {
        if (delay > 0) {
            delay();
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}