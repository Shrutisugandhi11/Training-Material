package com.epam.controller;

import com.epam.model.Book;
import com.epam.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookService.getById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = bookService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updateBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updateBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);

    }


}
