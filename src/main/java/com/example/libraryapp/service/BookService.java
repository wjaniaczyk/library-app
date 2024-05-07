package com.example.libraryapp.service;

import com.example.libraryapp.exception.BookNotFoundException;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book add(Book book){
        return bookRepo.save(book);
    }

    public List<Book> getAll(){
        return bookRepo.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Book getById(long id){
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book update(long id, Book newBook){
       return bookRepo.findById(id)
                .map(element -> {
                    element.setTitle(newBook.getTitle());
                    element.setAuthor(newBook.getAuthor());
                    return bookRepo.save(element);
                })
                .orElseGet(() -> {
                    newBook.setId(id);
                    return bookRepo.save(newBook);
                });
    }

    public void delete(long id){
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookRepo.delete(book);
    }
 }
