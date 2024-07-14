package com.epam.service;

import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.Library;
import com.epam.model.UserBookTemplate;
import com.epam.repository.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final String BOOK_URL = "http://localhost:8080/books";
    private final String USER_URL = "http://localhost:8081/users";

    WebClient webClient;

    LibraryRepository libraryRepository;

    public LibraryService(WebClient webClient, LibraryRepository libraryRepository) {
        this.webClient = webClient;
        this.libraryRepository = libraryRepository;
    }

    public Flux<BookDTO> getBooks() {
        return webClient.get().uri(BOOK_URL).retrieve().bodyToFlux(BookDTO.class);
    }

    public Mono<BookDTO> getBook(Long id) {
        return webClient.get().uri(BOOK_URL + "/" + id).retrieve().bodyToMono(BookDTO.class);
    }

    public Mono<BookDTO> addBook(BookDTO bookDTO) {
        return webClient.post().uri(BOOK_URL)
                .body(Mono.just(bookDTO), BookDTO.class)
                .retrieve()
                        .bodyToMono(BookDTO.class);
    }

    public Mono<BookDTO> updateBook(Long id, BookDTO bookDTO) {
        return webClient.put().uri(BOOK_URL + "/" + id)
                .body(Mono.just(bookDTO), BookDTO.class)
                .retrieve().bodyToMono(BookDTO.class);
    }

    public Mono<String> deleteBook(Long id) {
        return webClient.delete().uri(BOOK_URL + "/" + id).retrieve().bodyToMono(String.class);
    }

    public Flux<UserDTO> getUsers() {
        return webClient.get().uri(USER_URL).retrieve().bodyToFlux(UserDTO.class);
    }

    public Mono<UserDTO> getUser(String username) {
        return webClient.get().uri(USER_URL + "/" + username).retrieve().bodyToMono(UserDTO.class);
    }

    public Mono<UserDTO> addUser(UserDTO userDTO) {
        return webClient.post().uri(USER_URL )
                .body(Mono.just(userDTO), UserDTO.class)
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Mono<UserDTO> updateUser(String username, UserDTO userDTO) {
        return webClient.put().uri(USER_URL + "/" + username)
                .body(Mono.just(userDTO), UserDTO.class)
                .retrieve()
               .bodyToMono(UserDTO.class);
    }

    public Mono<String> deleteUser(String username) {
        return webClient.delete().uri(USER_URL + "/" + username).retrieve().bodyToMono(String.class);
    }


    public Library issueBookToUser(String username, Long id) {
        Optional<Library> existingUser = libraryRepository.findByUsernameAndBookId(username, id);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already have book!");
        }
        BookDTO book = getBook(id).blockOptional().orElseThrow(() -> new RuntimeException("Book doesn't exist!!"));
        UserDTO userDTO = getUser(username).blockOptional().orElseThrow(() -> new RuntimeException("User doesn't exist!!"));
        Library library = new Library();
        library.setUsername(username);
        library.setBookId(id);
        return libraryRepository.save(library);

    }

    public void removeBookAndUser(String username, Long id) {
        Library existingUser = libraryRepository.findByUsernameAndBookId(username, id).orElseThrow(() -> new RuntimeException("Book and User doesn't exist!!"));
        libraryRepository.delete(existingUser);
    }

    public Mono<String> removeUser(String username) {
        Library existingUser = libraryRepository.findByUsername(username).orElseThrow(() -> new RuntimeException(" User doesn't exist in library!!"));
        libraryRepository.delete(existingUser);
        return deleteUser(username);
    }
    public UserBookTemplate getUserWithBooks(String username){
       UserDTO userDTO=getUser(username).share().block();
        List<Long> bookId=libraryRepository.findAllBookIdWithUsername(username);
        List<BookDTO> books=new ArrayList<>();
        for (Long id:bookId) {
           books.add(getBook(id).block());
        }
        return  new UserBookTemplate(userDTO,books);
           }

}