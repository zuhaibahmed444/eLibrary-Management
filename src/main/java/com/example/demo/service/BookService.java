package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {
        // To upload a book
        Book uploadBook(Book book);

        // To get a book by id
        Book getBookById(String id);

        // To get all books
        List<Book> getAllBooks();

}
