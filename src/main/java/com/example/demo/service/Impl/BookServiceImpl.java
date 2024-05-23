package com.example.demo.service.Impl;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepository;
import com.example.demo.service.BookService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book uploadBook(Book book) {
        book.setBookId(Generators.timeBasedGenerator().generate().toString());
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}

