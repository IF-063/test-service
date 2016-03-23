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

import com.testservice.domain.Book;

@Component
public class BookService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> loadAll() {
        return jdbcTemplate.query("select * from Book", new BeanPropertyRowMapper<Book>(Book.class));
    }

    public Book load(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from Book where id=?", new Object[] { id },
                    new BeanPropertyRowMapper<Book>(Book.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from Book");
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from Book where id=?", new Object[] { id });
    }

    public Book save(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into Book values (?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, 0);
                ps.setString(2, book.getName());
                ps.setInt(3, book.getPages());
                ps.setInt(4, book.getAuthorId());
                return ps;
            }
        }, keyHolder);
        book.setId(keyHolder.getKey().intValue());
        return book;
    }

    public void update(Book book) {
        jdbcTemplate.update("update Book set name=?, pages=?, authorId=? where id=?",
                new Object[] { book.getName(), book.getPages(), book.getAuthorId(), book.getId() });
    }

    public void saveLogs(Book book) {
        jdbcTemplate.update("insert into BookLogs values (?, ?, ?)",
                new Object[] { null, book.getId(), book.getName() });
    }

    public List<Book> getBooksByAuthor(int id) {
        return jdbcTemplate.query("select * from Book where authorId=?", new BeanPropertyRowMapper<Book>(Book.class),
                new Object[] { id });
    }
}