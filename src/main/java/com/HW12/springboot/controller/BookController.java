package com.HW12.springboot.controller;

import com.HW12.springboot.datamodel.Book;
import com.HW12.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/bookStore")
@RestController
public class BookController {

    @Autowired
    BookService bookSer;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = new ArrayList<Book>();
        books = bookSer.findBooks();

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return new ResponseEntity<>(bookSer.fetchBook(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody  Book book) {
        bookSer.addBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> removeBookById(@PathVariable int id) {
        Book book = bookSer.fetchBook(id).get();
        if (bookSer.deleteBook(id)) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(book, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/old")
    public ResponseEntity<Book> getOldBook() {
        List<Book> books = bookSer.findBooks();
        Integer index = 0;
        Integer year = Integer.MAX_VALUE;
        for (int i = 0;i < books.size(); i++) {
            if (books.get(i).getYear() < year) {
                year = books.get(i).getYear();
                index = i;
            }
        }
        return new ResponseEntity<>(books.get(index), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<Book> getNewBook() {
        List<Book> books = bookSer.findBooks();
        Integer index = 0;
        Integer year = Integer.MIN_VALUE;
        for (int i = 0;i < books.size(); i++) {
            if (books.get(i).getYear() > year) {
                year = books.get(i).getYear();
                index = i;
            }
        }
        return new ResponseEntity<>(books.get(index), HttpStatus.OK);
    }
}
