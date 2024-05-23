package com.example.demo.model.helper;

public class RevokeRequestModel {

    private String bookId ;
    private String  userEmail;

    public RevokeRequestModel(String bookId, String userEmail) {
        this.bookId = bookId;
        this.userEmail = userEmail;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
