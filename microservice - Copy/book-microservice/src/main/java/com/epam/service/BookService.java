package com.epam.service;

import com.epam.model.Book;
import com.epam.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found:"));

    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found:"));
        existingBook.setBookName(book.getBookName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        return bookRepository.save(existingBook);

    }

    public void deleteBookById(Long id) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found:"));
        bookRepository.delete(existingBook);
    }


}
