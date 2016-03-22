package com.testservice.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Book implements Serializable {

    private static final long serialVersionUID = -3665432006352202873L;
    
    private int id;
    private String name;
    private int pages;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
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
        builder.append(", pages=");
        builder.append(pages);
        builder.append(", authorId=");
        builder.append(authorId);
        builder.append("]");
        return builder.toString();
    }
}