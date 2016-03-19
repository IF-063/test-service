package com.testservice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

    public void deleteAuthors() {
        jdbcTemplate.update("delete from Author");
    }

    public void deleteAuthor(int id) {
        jdbcTemplate.update("delete from Author where id = ?", new Object[] { id });
    }

    public Author saveAuthor(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into Author values (?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 0);
                ps.setString(2, author.getFirstName());
                ps.setString(3, author.getLastName());
                ps.setInt(4, author.getAge());
                ps.setDouble(5, author.getSalary());
                return ps;
            }
        }, keyHolder);
        author.setId(keyHolder.getKey().intValue());
        return author;
    }

    public void updateAuthor(Author author) {
        jdbcTemplate.update("update Author set firstName = ?, lastName = ?, age = ?, salary = ? where id = ?",
                new Object[] { author.getFirstName(), author.getLastName(), author.getAge(), author.getSalary(),
                        author.getId() });
    }
}