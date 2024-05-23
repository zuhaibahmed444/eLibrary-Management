package com.example.demo.model.helper;

import java.time.LocalDate;

public class BookBasedUserResponse {
    private String userEmail ;
    private LocalDate expiredDate ;
    private LocalDate  issuedDate ;
    private Boolean active ;

    public BookBasedUserResponse(String userEmail, LocalDate expiredDate, LocalDate issuedDate, Boolean active) {
        this.userEmail = userEmail;
        this.expiredDate = expiredDate;
        this.issuedDate = issuedDate;
        this.active = active;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
