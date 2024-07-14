package com.epam.exception;

public class QuestionNotFoundException extends Exception{
    QuestionNotFoundException(){
    super();
    }
    public QuestionNotFoundException(String message){
       super(message);
    }
}
