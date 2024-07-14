package com.epam.api;

import com.epam.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "USER-SERVICE/users")
public interface UserClient {
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers();

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username);

    @PostMapping()
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user);

    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username, @RequestBody UserDTO user);

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username);
}
