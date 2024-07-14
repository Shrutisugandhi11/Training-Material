package com.epam.service;


import com.epam.model.Book;
import com.epam.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class BookServiceTest {
    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    private Book book;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        book = new Book("python", "xyz", "abc");
        books = Arrays.asList(book);
    }


    @Test
    void testAddBook() {
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.addBook(book));
    }

    @Test
    void testGetBooks() {
        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(books.size(), bookService.getAllBooks().size());

    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(any())).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getById(book.getId()));
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(any())).thenReturn(Optional.ofNullable(book));
        when(bookRepository.save(any())).thenReturn(book);
        Book book1 = bookService.updateBook(book.getId(), book);
        book1.setBookName("demo");
        assertEquals("demo", book1.getBookName());
    }

    @Test
    void testDeleteBook() throws Exception {
        Book book1 = new Book("python", "xyz", "abc");
        when(bookRepository.findById(any())).thenReturn(Optional.of(book1));
        bookService.deleteBookById(book1.getId());
        verify(bookRepository).delete(book1);
    }


}
