package com.example.demo.service.Impl;

import com.example.demo.model.Book;
import com.example.demo.model.BookAssigned;
import com.example.demo.model.User;
import com.example.demo.model.helper.BookBasedUserResponse;
import com.example.demo.repo.BookAssignedRepository;
import com.example.demo.service.BookAssignedService;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookAssignedServiceImpl implements BookAssignedService {

    @Autowired
    private BookAssignedRepository bookAssignedRepository;

    @Override
    public BookAssigned assignBook(Book book, User user) {
        LocalDate expiryDate = LocalDate.now().plusDays(15);
        LocalDate issueDate = LocalDate.now();
        BookAssigned bookAssigned = new BookAssigned();
        bookAssigned.setBook(book);
        bookAssigned.setUser(user);
        bookAssigned.setExpiryDate(expiryDate);
        bookAssigned.setIssueDate(issueDate);
        bookAssigned.setId(Generators.timeBasedGenerator().generate().toString());
        return bookAssignedRepository.save(bookAssigned);
    }

    @Override
    public List<BookAssigned> getAllAssignedBooks() {
        return bookAssignedRepository.findAll();
    }

    @Override
    public List<BookAssigned> getAssignedByUser(User user) {
        return bookAssignedRepository.findByUser(user);
    }

    @Override
    public List<BookAssigned> getAssignedBooksByUser(User user) {
        List<BookAssigned> booksAssigned = bookAssignedRepository.findByUser(user);
        LocalDate currentDate = LocalDate.now();
        List<BookAssigned> books = new ArrayList<>();
        for (BookAssigned assigned : booksAssigned) {
            if (assigned.getExpiryDate().isAfter(currentDate)) {
                books.add(assigned);
            }
        }
        return books;
    }

    @Override
    public List<BookAssigned> getAssignedByUserAll(User user) {
        return bookAssignedRepository.findByUser(user);
    }

    @Override
    public BookAssigned updateBookAssigned(BookAssigned bookAssigned) {
        return bookAssignedRepository.save(bookAssigned);
    }

    @Override
    public BookAssigned getAssignedBookById(String id) {
        return bookAssignedRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookBasedUserResponse> getAssignedBooksByBook(Book book) {
        List<BookAssigned> bookAssignedList = bookAssignedRepository.findByBook(book);
        List<BookBasedUserResponse> responseList = new ArrayList<>();
        for (BookAssigned assigned : bookAssignedList) {
            responseList.add(new BookBasedUserResponse(assigned.getUser().getEmail(),
                    assigned.getExpiryDate(), assigned.getIssueDate(),
                    assigned.getExpiryDate().isAfter(LocalDate.now())));
        }
        return responseList;
    }

    @Override
    public BookAssigned revokeBookAssigned(User user, Book book) {
        BookAssigned bookAssigned = bookAssignedRepository.findByBookAndUser(book, user);
        if (bookAssigned != null) {
            bookAssigned.setExpiryDate(LocalDate.now().minusDays(1));
            return bookAssignedRepository.save(bookAssigned);
        }
        return null;
    }
}

