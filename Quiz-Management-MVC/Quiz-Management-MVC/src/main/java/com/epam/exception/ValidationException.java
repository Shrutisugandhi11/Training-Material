package com.epam.exception;

public class ValidationException extends Exception{
    public ValidationException(){
        super();
    }
    public ValidationException(String string){
        super(string);
    }
}
