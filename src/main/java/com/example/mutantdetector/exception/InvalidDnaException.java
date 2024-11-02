package com.example.mutantdetector.exception;

public class InvalidDnaException extends RuntimeException {
    public InvalidDnaException(String message) {
        super(message);
    }
}