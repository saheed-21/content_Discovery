package com.Saheed.content.controller;

import com.Saheed.content.model.*;


import com.Saheed.content.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class contentController {

    @Autowired
    private contentService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<content> addBook(@RequestBody content book) {
        content savedBook = contentService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<content> getBookById(@PathVariable Long id) {
        content book = contentService.getBookById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllcontent")
    public ResponseEntity<List<content>> getAllBooks() {
        List<content> books = contentService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        boolean deleted = bookService.deleteBookById(id);
        if (deleted) {
            return ResponseEntity.ok("Deleted Book successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

       // @Query

       @GetMapping("/books/rating/{rating}")
       public ResponseEntity<List<Book>> getBooksByRatingGreaterThan(@PathVariable float rating) {
           List<Book> books = bookService.findBooksByRatingGreaterThan(rating);
           return ResponseEntity.ok(books);
       }
   
       @GetMapping("/books/year/{year}")
       public ResponseEntity<List<Book>> getBooksByYear(@PathVariable String year) {
           List<Book> books = bookService.findBooksByYear(year);
           return ResponseEntity.ok(books);
       }
}
