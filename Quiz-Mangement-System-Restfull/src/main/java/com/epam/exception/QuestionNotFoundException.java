package com.epam.exception;

public class QuestionNotFoundException extends RuntimeException {


    QuestionNotFoundException() {

    }

    public QuestionNotFoundException(String message, Object value, Object field) {
        super(String.format("%s with %s:%s", message, value, field));
    }

}
