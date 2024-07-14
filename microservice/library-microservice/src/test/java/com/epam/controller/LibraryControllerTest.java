package com.epam.controller;

import com.epam.api.BookClient;
import com.epam.api.UserClient;
import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.Library;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
 class LibraryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private BookClient bookClient;
    @MockBean
    private UserClient userClient;

    private ObjectMapper objectMapper = new ObjectMapper();
    private String bookDTOJson;
    private String userDTOJson;


    private BookDTO book;
    private UserDTO user;
    private Library library;

    private List<BookDTO> books;
    private List<Library> libraries;
    private List<UserDTO> users;


    @BeforeEach
    void setUpObjects() throws JsonProcessingException {
        book = new BookDTO();
        book.setPublisher("xyz");
        ;
        book.setBookName("Book");
        book.setAuthor("ABC");
        bookDTOJson = objectMapper.writeValueAsString(book);
        books = Arrays.asList(book, book);
        user = new UserDTO();
        user.setUsername("abc");
        user.setEmail("abc@gmail.com");
        user.setFullName("abcxyz");
        userDTOJson = objectMapper.writeValueAsString(user);
        users = Arrays.asList(user, user);
        library = new Library();
        library.setUsername(user.getUsername());
        library.setBookId(book.getId());
        libraries = Arrays.asList(library);
    }


    @Test
    void testGetAllBooks() throws Exception {
        when(bookClient.getAllBooks()).thenReturn(new ResponseEntity<List<BookDTO>>(books, HttpStatus.OK));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/books").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userClient.getAllUsers()).thenReturn(new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/users").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testGetBook() throws Exception {
        when(bookClient.getBook(anyLong())).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/books/1").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testGetUser() throws Exception {
        when(userClient.getUser(any())).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/library/users/1").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }


    @Test
    void testAddBook() throws Exception {
        book.setAuthor("");
        bookDTOJson = objectMapper.writeValueAsString(book);
        when(bookClient.addBook(any(BookDTO.class))).thenReturn(new ResponseEntity<>(book, HttpStatus.CREATED));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/library/books").contentType(MediaType.APPLICATION_JSON).content(bookDTOJson);
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testAddUser() throws Exception {
        user.setUsername("");
        bookDTOJson = objectMapper.writeValueAsString(user);
        when(userClient.addUser(any(UserDTO.class))).thenReturn(new ResponseEntity<>(user, HttpStatus.CREATED));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/library/users").contentType(MediaType.APPLICATION_JSON).content(userDTOJson);
        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
    }


    @Test
    void testDeleteBook() throws Exception {
        when(bookClient.deleteBook(anyLong())).thenReturn(new ResponseEntity<>("Book deleted", HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/library/books/1").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userClient.deleteUser(any())).thenReturn(new ResponseEntity<>("User deleted", HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/library/users/abc").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }


    @Test
    void testUpdateBook() throws Exception {
        when(bookClient.updateBook(anyLong(), any(BookDTO.class))).thenReturn(new ResponseEntity<BookDTO>(book, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/library/books/1").contentType(MediaType.APPLICATION_JSON).content(bookDTOJson);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userClient.updateUser(any(), any(UserDTO.class))).thenReturn(new ResponseEntity<UserDTO>(user, HttpStatus.OK));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/library/users/abc").contentType(MediaType.APPLICATION_JSON).content(userDTOJson);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }

    @Test
    void testIssueBookToUser() {
        when(libraryService.issueBookToUser(any(), any())).thenReturn(library);
        assertEquals(library, libraryService.issueBookToUser(user.getUsername(), book.getId()));
    }




}
