package com.epam.controller;

import com.epam.model.Book;
import com.epam.repository.BookRepository;
import com.epam.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
@ExtendWith(SpringExtension.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookService bookService;

    private Book book;

    private List<Book> books = new ArrayList<>();

    @BeforeEach
    void setUp() {
        book = new Book("abcd", "xyz", "pqr");
        books = Arrays.asList(book);
    }

    @Test
    void testCreateBook() throws Exception {

        when(bookService.addBook(book)).thenReturn(book);
        mockMvc.perform(post("/books").contentType("application/json").content(objectMapper.writeValueAsString(book))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testGetBook() throws Exception {
        when(bookService.getById(any())).thenReturn(book);
        mockMvc.perform(get("/books/1")).andExpect(status().isOk());
    }

    @Test
    void testGetBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(books);
        mockMvc.perform(get("/books")).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testUpdateBook() throws Exception {
        when(bookService.updateBook(book.getId(), book)).thenReturn(book);
        mockMvc.perform(put("/books/1").contentType("application/json").content(objectMapper.writeValueAsString(book))).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/1")).andExpect(status().isOk());
    }

}
