package com.testservice.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Book class represents {@code Book} entity stored in the database.
 * 
 * @author taras
 *
 */
@XmlRootElement
public class Book {

    private int id;
    private String name;
    private int year;
    private int authorId;

    public Book() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", year=");
        builder.append(year);
        builder.append(", authorId=");
        builder.append(authorId);
        builder.append("]");
        return builder.toString();
    }
}