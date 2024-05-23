package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.model.BookAssigned;
import com.example.demo.model.User;
import com.example.demo.model.helper.BookBasedUserResponse;

import java.util.List;

public interface BookAssignedService {
    BookAssigned assignBook(Book book, User user);
    List<BookAssigned> getAllAssignedBooks();
    List<BookAssigned> getAssignedByUser(User user);
    List<BookAssigned> getAssignedBooksByUser(User user);
    List<BookAssigned> getAssignedByUserAll(User user);
    BookAssigned updateBookAssigned(BookAssigned bookAssigned);
    BookAssigned getAssignedBookById(String id);
    List<BookBasedUserResponse> getAssignedBooksByBook(Book book);
    BookAssigned revokeBookAssigned(User user, Book book);
}
