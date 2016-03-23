package com.testservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.testservice.domain.User;

@Component
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User load(String name, String password) {
        try {
            return jdbcTemplate.queryForObject("select * from User where name=? and password=?",
                    new Object[] { name, password }, new BeanPropertyRowMapper<User>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}