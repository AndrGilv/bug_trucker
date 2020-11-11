package com.example.but_trucker2.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Long id) {
        super("Could not find comment with Id = " + id);
    }
}