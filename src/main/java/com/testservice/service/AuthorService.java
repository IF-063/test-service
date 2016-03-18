package com.testservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.testservice.domain.Author;

@Component
public class AuthorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Author> getAuthors() {
        return jdbcTemplate.query("select * from Author", new BeanPropertyRowMapper<Author>(Author.class));
    }

    public Author getAuthor(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from Author where id = ?", new Object[] { id },
                    new BeanPropertyRowMapper<Author>(Author.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}