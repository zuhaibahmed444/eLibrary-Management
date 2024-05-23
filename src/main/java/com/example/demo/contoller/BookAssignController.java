package com.example.demo.contoller;

import com.example.demo.model.AccessReq;
import com.example.demo.model.Book;
import com.example.demo.model.BookAssigned;
import com.example.demo.model.User;
import com.example.demo.model.helper.BookBasedUserResponse;
import com.example.demo.model.helper.RevokeRequestModel;
import com.example.demo.repo.BookAssignedRepository;
import com.example.demo.service.AccessReqService;
import com.example.demo.service.BookAssignedService;
import com.example.demo.service.BookService;
import com.example.demo.service.Impl.CSVServiceImpl;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bookassign")
@CrossOrigin("*")
public class BookAssignController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookAssignedService bookAssignedService;

    @Autowired
    private AccessReqService accessReqService;

    @Autowired
    private BookAssignedRepository bookAssignedRepository;

    @Autowired
    private CSVServiceImpl csvServiceImpl;

    @PostMapping("/")
    public BookAssigned assignBook(@RequestBody String reqId) {
        AccessReq accessReq = accessReqService.getAccessReq(reqId);
        String email = accessReq.getUserEmail();
        String bookId = accessReq.getBookId();
        User user = userService.getUser(email);
        Book book = bookService.getBookById(bookId);
        accessReq.setActive(false);
        accessReqService.updateAccessReq(accessReq);
        return bookAssignedService.assignBook(book, user);
    }

    @GetMapping("/")
    public List<BookAssigned> getAllAssignedBooks() {
        return bookAssignedService.getAllAssignedBooks();
    }

    @GetMapping("/{id}")
    public List<BookAssigned> getAssignAllBookByUserdata(@PathVariable String id) {
        User user = userService.getUserById(id);
        return bookAssignedService.getAssignedByUser(user);
    }

    @GetMapping("/book-active/{id}")
    public List<BookAssigned> getAssignBookByUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        return bookAssignedService.getAssignedBooksByUser(user);
    }

    @GetMapping("/book-all/{id}")
    public List<BookAssigned> getAssignAllBookByUser(@PathVariable String id) {
        User user = userService.getUserById(id);
        return bookAssignedService.getAssignedByUserAll(user);
    }

    @GetMapping("/book-res/{id}")
    public List<BookBasedUserResponse> getAssignBookByUserRes(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        return bookAssignedService.getAssignedBooksByBook(book);
    }

    @PostMapping("/revoke")
    public BookAssigned revokeBookAccess(@RequestBody RevokeRequestModel revreq) {
        String email = revreq.getUserEmail();
        String bookId = revreq.getBookId();
        User user = userService.getUser(email);
        Book book = bookService.getBookById(bookId);
        return bookAssignedService.revokeBookAssigned(user, book);
    }

    @PostMapping("/csv")
    public ResponseEntity<?> uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        List<RevokeRequestModel> ot = csvServiceImpl.uploadMultipart(file);
        ot.forEach(revreq -> {
            User user = userService.getUser(revreq.getUserEmail());
            Book book = bookService.getBookById(revreq.getBookId());
            bookAssignedService.revokeBookAssigned(user, book);
        });
        return ResponseEntity.ok(ot);
    }

    @GetMapping("/book-active/count/{id}")
    public ResponseEntity<?> getUserActiveBookCount(@PathVariable String id) {
        User user = userService.getUserById(id);
        int activeCount = bookAssignedService.getAssignedBooksByUser(user).size();
        return ResponseEntity.ok(activeCount);
    }

    @GetMapping("/book-all/count/{id}")
    public ResponseEntity<?> getUserAllBookCount(@PathVariable String id) {
        User user = userService.getUserById(id);
        int allCount = bookAssignedService.getAssignedByUserAll(user).size();
        return ResponseEntity.ok(allCount);
    }
}
