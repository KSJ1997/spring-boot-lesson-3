package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Book createdBook = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody BookRequest bookRequest) {
        Book updatedBook = bookService.updateBook(id, bookRequest);
        return ResponseEntity.ok(updatedBook);
    }
}
