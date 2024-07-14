package com.epam.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
import javax.persistence.Entity;

@Entity
@Component
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String bookName;
    private String author;
    private String publisher;


    public Book() {
    }

    public Book(String bookName, String author, String publisher) {
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
