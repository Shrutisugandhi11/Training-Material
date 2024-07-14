package com.epam.exception;

public class QuizNotFoundException extends RuntimeException {


    public QuizNotFoundException(String message, Object value, Object field) {
        super(String.format("%s with %s:%s", message, value, field));
    }
}
