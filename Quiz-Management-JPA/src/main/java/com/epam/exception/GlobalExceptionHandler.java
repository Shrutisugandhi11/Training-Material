package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleQuestionNotFoundException(QuestionNotFoundException questionNotFoundException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(questionNotFoundException.getMessage(), webRequest.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleQuestionNotFoundException(QuizNotFoundException quizNotFoundException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(quizNotFoundException.getMessage(), webRequest.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(invalidCredentialsException.getMessage(), webRequest.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleQuestionNotFoundException(MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(methodArgumentNotValidException.getMessage(), webRequest.toString());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
