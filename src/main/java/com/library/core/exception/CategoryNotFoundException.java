package com.library.core.exception;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(Integer id) {
        super("Category with " + id + " not found!");
    }
}
