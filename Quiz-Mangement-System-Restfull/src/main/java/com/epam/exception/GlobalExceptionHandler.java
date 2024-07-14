package com.epam.exception;

import org.springframework.dao.DataIntegrityViolationException;
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
        ErrorDetails errorDetails = new ErrorDetails(questionNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleQuestionNotFoundException(QuizNotFoundException quizNotFoundException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(quizNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException) {
        return new ResponseEntity<>(invalidCredentialsException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleQuestionNotFoundException(MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(methodArgumentNotValidException.getMessage(), webRequest.getDescription(false), methodArgumentNotValidException.getFieldError());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException, WebRequest request) {
        dataIntegrityViolationException.printStackTrace();
        ErrorDetails errorDetails= new ErrorDetails(dataIntegrityViolationException.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest) {
        exception.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}
