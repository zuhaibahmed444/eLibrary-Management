package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "access_req")
public class AccessReq {
    @Id
    private String reqID ;
    private String bookId ;
    private String bookName ;
    private String userEmail;
    private boolean active;

    public AccessReq(String bookId, String bookName, String userEmail, boolean active) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.userEmail = userEmail;
        this.active = active;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}