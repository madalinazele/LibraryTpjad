package com.library.core.exception;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException(String tokenValue) {
        super("Expired token " + tokenValue);
    }

}
