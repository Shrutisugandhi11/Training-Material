package com.epam.api;

import com.epam.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "BOOK-SERVICE/books")
public interface BookClient {

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getAllBooks();

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id);

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO book);

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO book);

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id);


}

