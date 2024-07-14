package com.epam.controller;

import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.UserBookTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
public class LibraryControllerTest {
    private static MockWebServer mockWebServer;
    private static MockWebServer userMockWebServer;

    @Autowired
    private LibraryController controller;

    @Autowired
    private ObjectMapper objectMapper;
    private BookDTO bookDto;
    private UserDTO userDto;
    private BookDTO newBookDto;
    private UserDTO newUserDto;
    private UserBookTemplate userBookTemplate;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8080);

        userMockWebServer = new MockWebServer();
        userMockWebServer.start(8081);

    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
        userMockWebServer.shutdown();
    }

    @BeforeEach
    void init() {
        bookDto = new BookDTO("Intro to Java", "TMH", "2");
        newBookDto = new BookDTO("Servlets & Jsp", "Pearson", "3");

        userDto = new UserDTO("a", "a@gmail.com", "a");
        newUserDto = new UserDTO("b", "b@gmail.com", "b");
        userBookTemplate = new UserBookTemplate(userDto, Arrays.asList(bookDto));

    }

    @Test
    void getBooksMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bookDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );

        Flux<BookDTO> response = controller.getBooks();
        Assertions.assertNotNull(response);

    }

    @Test
    void getBookMethodTest() throws JsonProcessingException {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bookDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );

        Mono<BookDTO> response = controller.getBook(bookDto.getId());
        Assertions.assertNotNull(response);
    }

    @Test
    void getUsersMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(userDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Flux<UserDTO> response = controller.getUsers();
        Assertions.assertNotNull(response);

    }

    @Test
    void addBookMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bookDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Mono<BookDTO> response = controller.addBook(bookDto);
        Assertions.assertNotNull(response);

    }


    @Test
    void addUserMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(userDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Mono<UserDTO> response = controller.addUser(userDto);
        Assertions.assertNotNull(response);

    }

    @Test
    void updateUserMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(userDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Mono<UserDTO> response = controller.updateUser(userDto.getUsername(), userDto);
        Assertions.assertNotNull(response);

    }

    @Test
    void updateBookMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bookDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Mono<BookDTO> response = controller.updateBook(bookDto.getId(), bookDto);
        Assertions.assertNotNull(response);

    }
    @Test
    void deleteBookMethodTest() throws Exception {
        mockWebServer.enqueue(new MockResponse()
                .setBody(new ObjectMapper().writeValueAsString(bookDto))
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
        Mono<String> response = controller.deleteBook(bookDto.getId());
        Assertions.assertNotNull(response);

    }
//    @Test
//    void issueBookMethodTest() throws Exception {
//        mockWebServer.enqueue(new MockResponse()
//                .setBody(new ObjectMapper().writeValueAsString(userDto))
//                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        );
//        ResponseEntity<Library> response = controller.issueBookToUser(userDto.getUsername(),bookDto.getId());
//        Assertions.assertNotNull(response);
//
//    }
//    @Test
//    void  removeUser() throws JsonProcessingException {
//        mockWebServer.enqueue(new MockResponse()
//                .setBody(new ObjectMapper().writeValueAsString(userDto))
//                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        );
//
//        ResponseEntity<String> response = controller.removeUser(userDto.getUsername());
//        Assertions.assertNotNull(response);
//    }

//    @Test
//    void getUserWithBooks() throws JsonProcessingException {
//        mockWebServer.enqueue(new MockResponse()
//                .setBody(new ObjectMapper().writeValueAsString(userDto.getUsername()))
//                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
//        ResponseEntity<UserBookTemplate> response = controller.getUserWithBooks(userDto.getUsername());
//        Assertions.assertNotNull(response);
//
//    }


}
