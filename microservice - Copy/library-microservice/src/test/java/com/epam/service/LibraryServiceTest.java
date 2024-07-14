package com.epam.service;

import com.epam.controller.LibraryController;
import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.model.Library;
import com.epam.repository.LibraryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;
import com.epam.repository.LibraryRepository;
import com.epam.service.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
@SpringBootTest()
@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {


    private static MockWebServer mockWebServer;

    private static MockWebServer userMockWebServer;

    @Mock
    LibraryRepository libraryRepository;


    @Autowired
    org.springframework.web.reactive.function.client.WebClient WebClient;

    @InjectMocks
    LibraryService libraryService;
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpecMock;
    @Mock
    private WebClient.RequestBodySpec requestBodySpecMock;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
    @Mock
    private WebClient.ResponseSpec responseSpecMock;

    @Autowired
    ObjectMapper objectMapper;
    Library library;
    BookDTO bookDTO;
    UserDTO userDTO;

    @BeforeEach
    void init() {
        bookDTO = new BookDTO("a", "b", "c");
        userDTO = new UserDTO("x", "y", "z");
        library = new Library();
        library.setUsername(userDTO.getUsername());
        library.setBookId(bookDTO.getId());

    }


    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(8081);

        userMockWebServer = new MockWebServer();
        userMockWebServer.start(8080);

    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
        userMockWebServer.shutdown();
    }


//

    @Test
    void issueBookMethodTest() throws JsonProcessingException {
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(BookDTO.class)).thenReturn(Mono.just(bookDTO));
        when(responseSpecMock.bodyToMono(UserDTO.class)).thenReturn(Mono.just(userDTO));
//        Mono<BookDTO> bookResponse = libraryService.getBook(1L);
//        Mono<UserDTO> userResponse = libraryService.getUser(userDTO.getUsername());
        when(libraryRepository.save(library)).thenReturn(library);
        assertEquals(library, libraryService.issueBookToUser("a", 1L));
    }
}
//
//    @Test
//    void removeBookTest() {
//        when(libraryRepository.findByUsernameAndBookId(library.getUsername(), library.getBookId())).thenReturn(Optional.ofNullable(library));
//        doNothing().when(libraryRepository).delete(library);
//        assertDoesNotThrow(() -> libraryService.removeBookAndUser(library.getUsername(), library.getBookId()));
//    }
//
////    @Test
////    void getAllUserBookIdsTest() {
////        when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
////        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
////        when(requestHeadersSpecMock.retrieve())
////                .thenReturn(responseSpecMock);
////        when(responseSpecMock.bodyToMono(UserDTO.class)).thenReturn(Mono.just(user));
////         when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<BookDTO>>notNull())).thenReturn(Mono.just(book));
////        when(libraryRepository.findAllBookIdWithUsername(library.getUsername())).thenReturn(bookIds);
////        assertNotNull(libraryService.getUserWithBooks(library.getUsername()));
////    }
//}
