package com.example.demo.repo;

import com.example.demo.model.Book;
import com.example.demo.model.BookAssigned;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAssignedRepository extends MongoRepository<BookAssigned, String> {
    BookAssigned save(BookAssigned bookAssigned);
    List<BookAssigned> findByUser(User user);
    List<Book> findBookByUser(User user);
    List<BookAssigned> findByBook(Book book);
    BookAssigned findByBookAndUser(Book book, User user);
}
