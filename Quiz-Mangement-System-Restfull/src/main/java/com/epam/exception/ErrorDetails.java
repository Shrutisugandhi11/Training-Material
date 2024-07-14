package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ErrorDetails {

    Object fieldValue;
    private String message;
    private String details;


    public ErrorDetails() {

    }

    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;

    }

    public ErrorDetails(String message, String details, Object fieldValue) {

        this.message = message;
        this.details = details;
        this.fieldValue = fieldValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
