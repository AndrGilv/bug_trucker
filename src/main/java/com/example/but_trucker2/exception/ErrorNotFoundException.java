package com.example.but_trucker2.exception;

public class ErrorNotFoundException extends RuntimeException {

    public ErrorNotFoundException(Long id) {
        super("Could not find error with Id = " + id);
    }
}