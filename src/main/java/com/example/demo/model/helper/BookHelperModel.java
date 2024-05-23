package com.example.demo.model.helper;

public class BookHelperModel{
    private String bookName;
    private String bookAuthor;
    private String yearPublished;

    public BookHelperModel(){
        this.bookName = "";
        this.bookAuthor = "";
        this.yearPublished = "";
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getYearPublished() {
        return yearPublished;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setYearPublished(String yearPublished) {
        this.yearPublished = yearPublished;
    }
}