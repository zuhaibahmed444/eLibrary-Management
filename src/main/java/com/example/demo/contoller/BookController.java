package com.example.demo.contoller;

import com.example.demo.model.Book;
import com.example.demo.model.helper.BookHelperModel;
import com.example.demo.service.BookService;
import com.example.demo.service.Impl.S3ServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    private S3ServiceImpl s3ServiceImpl;

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book upload(@RequestParam("book") String bookJson,
                       @RequestParam("image") MultipartFile image,
                       @RequestParam("file") MultipartFile file) {
        String imageName = image.getOriginalFilename();
        String filename = file.getOriginalFilename();
        Gson gson = new Gson();
        BookHelperModel bookHelperModel = gson.fromJson(bookJson, BookHelperModel.class);

        String url = s3ServiceImpl.uploadFile(file);
        String imageUrl = s3ServiceImpl.uploadFile(image);
        Book book = new Book(bookHelperModel.getBookName(), bookHelperModel.getBookAuthor(),
                bookHelperModel.getYearPublished(), url, filename, imageName, imageUrl);

        return bookService.uploadBook(book);
    }

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/download")
    public String download(@RequestBody String id) {
        Book book = bookService.getBookById(id);
        String filename = book != null ? book.getFilename() : null;
        byte[] file = s3ServiceImpl.downloadFile(filename);
        return Base64.getEncoder().encodeToString(file);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadfile(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        String filename = book != null ? book.getFilename() : null;
        byte[] data = s3ServiceImpl.downloadFile(filename);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .contentLength(data != null ? data.length : 0)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/count")
    public ResponseEntity<?> getBookCount() {
        Integer count = bookService.getAllBooks().size();
        return ResponseEntity.ok(count);
    }
}

