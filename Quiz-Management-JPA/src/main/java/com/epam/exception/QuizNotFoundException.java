package com.epam.exception;

public class QuizNotFoundException extends Exception{
    QuizNotFoundException(){
        super();
    }
    public QuizNotFoundException(String message){
        super( message);
    }
}
