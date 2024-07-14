package com.epam.model;

import com.epam.dto.BookDTO;
import com.epam.dto.UserDTO;

import java.util.List;

public class UserBookTemplate {
   private UserDTO userDTO;
   private List<BookDTO> books;

    public UserBookTemplate() {

    }

    public UserBookTemplate(UserDTO userDTO, List<BookDTO> books) {
        this.userDTO = userDTO;
        this.books = books;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
