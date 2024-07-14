package com.epam.service;

import com.epam.api.BookClient;
import com.epam.api.UserClient;
import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.Library;
import com.epam.model.UserBookTemplate;
import com.epam.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private BookClient bookClient;
    private  LibraryRepository libraryRepository;
    @Autowired
    private UserClient userClient;

    public LibraryService(LibraryRepository libraryRepository) {

        this.libraryRepository = libraryRepository;
    }

    public ResponseEntity<List<BookDTO>> getBooks() {
        return bookClient.getAllBooks();

    }

    public ResponseEntity<BookDTO> getBook(Long id) {
        return bookClient.getBook(id);
    }

    public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
        return bookClient.addBook(bookDTO);
    }

    public ResponseEntity<BookDTO> updateBook(Long id, BookDTO bookDTO) {
        return bookClient.updateBook(id, bookDTO);
    }

    public ResponseEntity<String> deleteBook(Long id) {
        return bookClient.deleteBook(id);
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return userClient.getAllUsers();
    }


    public ResponseEntity<UserDTO> getUser(String username) {
        return userClient.getUser(username);
    }


    public ResponseEntity<UserDTO> addUser(UserDTO userDTO) {
        return userClient.addUser(userDTO);
    }

    public ResponseEntity<UserDTO> updateUser(String username, UserDTO userDTO) {
        return userClient.updateUser(username, userDTO);
    }

    public ResponseEntity<String> deleteUser(String username) {
        return userClient.deleteUser(username);
    }


    public Library issueBookToUser(String username, Long id) {
        Optional<Library> existingUser = libraryRepository.findByUsernameAndBookId(username, id);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already have book!");
        }
        BookDTO book = getBook(id).getBody();
        UserDTO userDTO = getUser(username).getBody();
        Library library = new Library();
        library.setUsername(username);
        library.setBookId(id);
        return libraryRepository.save(library);

    }

    public void removeBookAndUser(String username, Long id) {
        Library existingUser = libraryRepository.findByUsernameAndBookId(username, id).orElseThrow(() -> new RuntimeException("Book and User doesn't exist!!"));
        libraryRepository.delete(existingUser);
    }

    public ResponseEntity<String> removeUser(String username) {
        Library existingUser = libraryRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(" User doesn't exist in library!!"));
        libraryRepository.delete(existingUser);
        return deleteUser(username);
    }

    public UserBookTemplate getUserWithBooks(String username) {
        UserDTO userDTO = getUser(username).getBody();
        List<Long> bookId = libraryRepository.findAllBookIdWithUsername(username);
        List<BookDTO> books = new ArrayList<>();
        for (Long id : bookId) {
            books.add(getBook(id).getBody());
        }
        return new UserBookTemplate(userDTO, books);
    }

}