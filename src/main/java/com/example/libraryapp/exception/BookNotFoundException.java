package com.example.libraryapp.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(long id) {
        super("Could not find book with id: " + id);
    }
}
