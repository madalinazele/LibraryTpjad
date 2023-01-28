package com.library.core.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String tokenValue) {
        super("Your token was not found " + tokenValue);
    }
}
