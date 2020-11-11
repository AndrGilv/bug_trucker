package com.example.but_trucker2.exception;

public class ResponsibleNotFoundException extends RuntimeException {

    public ResponsibleNotFoundException(Long id) {
        super("Could not find developer with Id = " + id + " that responsible for fixing the error");
    }
}