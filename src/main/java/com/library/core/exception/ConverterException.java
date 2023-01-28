package com.library.core.exception;

public class ConverterException extends RuntimeException {

    public <TARGET> ConverterException(String message, Class<TARGET> targetClass) {
        super("Converter exception: \"" + message + "\" when accessing target: " + targetClass.getName());
    }
}
