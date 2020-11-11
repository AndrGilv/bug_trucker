package com.example.but_trucker2.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
	public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
