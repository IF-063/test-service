package com.testservice.config;

import java.util.HashMap;
import java.util.Map;

public enum TokenStore {

    STORE;

    Map<String, String> map = new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;

        {
            put("b643893e-8c44-4d0d-8eae-6c61eadf6e25", "user1");
            put("7961b798-3cf7-4544-a60d-b5b22888d3ba", "user2");
            put("68d8e663-94fd-4f63-b964-15733e1dcc18", "user3");
            put("1c5f5e6a-88b7-4a6f-9e3f-97de35070077", "user4");
            put("b1b74266-afad-4817-8ae8-54307da61208", "user5");
        }
    };

    public String gerUserName(String token) {
        return map.get(token);
    }

}