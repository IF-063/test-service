package com.testservice.service;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testservice.domain.User;

@Component
public class TokenService {

    private static final int timeToLive = 20;

    private final Map<String, User> map = new PassiveExpiringMap<>(timeToLive, TimeUnit.SECONDS);

    @Autowired
    private UserService userService;

    public boolean contains(String token) {
        return map.containsKey(token);
    }

    public boolean tryAuthenticate(String token) {
        String decodedToken = null;
        try {
            decodedToken = new String(Base64.getDecoder().decode(token));
            System.out.println("(TRY AUTHENTICATE) decoded token: " + decodedToken);
        } catch (IllegalArgumentException e) {
            return false;
        }
        String[] data = decodedToken.split(":");
        String name = data[0];
        String password = data[1];
        User user = userService.load(name, password);
        System.out.println("(TRY AUTHENTICATE) user: " + user);
        if (user != null) {
            map.put(token, user);
            return true;
        }
        return false;
    }

    public User get(String token) {
        return map.get(token);
    }
}