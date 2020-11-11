package com.example.but_trucker2.exception;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id) {
        super("Could not find project with Id = " + id);
    }
}