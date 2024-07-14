package com.epam.controller;


import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.Library;
import com.epam.model.UserBookTemplate;
import com.epam.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
   private LibraryService libraryService;


    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getBooks() {
        return libraryService.getBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
        return libraryService.getBook(id);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return libraryService.addBook(bookDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return libraryService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return libraryService.deleteBook(id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return libraryService.getUsers();
    }


    @PostMapping("/users")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        return libraryService.addUser(userDTO);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        return libraryService.updateUser(username, userDTO);
    }

    @PostMapping("/users/{username}/books/{id}")
    public ResponseEntity<Library> issueBookToUser(@PathVariable String username, @PathVariable Long id) {
        return new ResponseEntity<>(libraryService.issueBookToUser(username, id), HttpStatus.OK);
    }


    @DeleteMapping("/users/{username}/books/{id}")
    public ResponseEntity<String> removeUserAndBook(@PathVariable String username, @PathVariable Long id) {
        libraryService.removeBookAndUser(username, id);
        return new ResponseEntity<>("Book and User deleted successfully", HttpStatus.OK);
    }
    @DeleteMapping("/users/{username}")
    public ResponseEntity<String> removeUser(@PathVariable String username) {
        libraryService.removeUser(username);
        return new ResponseEntity<>("Book and User deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/users/{username}")
    public ResponseEntity<UserBookTemplate> getUserWithBooks(@PathVariable String username) {
        UserBookTemplate userBookTemplate=libraryService.getUserWithBooks(username);
        return new ResponseEntity<>(userBookTemplate, HttpStatus.OK);
    }

}
