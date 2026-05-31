package com.example.creation.exception;

public class ResourseAlreadyExistException extends RuntimeException {
    public ResourseAlreadyExistException(String message) {
        super(message);
    }
}
