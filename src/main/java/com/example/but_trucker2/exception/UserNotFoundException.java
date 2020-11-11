package com.example.but_trucker2.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find user with Id = " + id);
    }
}