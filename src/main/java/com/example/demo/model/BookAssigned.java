package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "bookAssigned")
public class BookAssigned{
    @Id
    private String id ;

    @DBRef
    private User user;
    @DBRef
    private Book book;
    private LocalDate expiryDate;
    private LocalDate issueDate;


    public BookAssigned(User user, Book book, LocalDate expiryDate, LocalDate issueDate) {
        this.user = user;
        this.book = book;
        this.expiryDate = expiryDate;
        this.issueDate = issueDate;
    }

    public BookAssigned() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
