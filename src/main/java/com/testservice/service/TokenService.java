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

    public boolean authenticate(String token) {
        boolean isTokenInStore = map.containsKey(token);
        
        if (!isTokenInStore) {
            String decodedToken = null;
            try {
                decodedToken = new String(Base64.getDecoder().decode(token));
            } catch (IllegalArgumentException e) {
                return false;
            }
            String[] data = decodedToken.split(":");
            String name = data[0];
            String password = data[1];
            User user = userService.load(name, password);
            if (user != null) {
                map.put(token, user);
                isTokenInStore = true;
            }
        }
        return isTokenInStore;
    }

    public User get(String token) {
        return map.get(token);
    }
}