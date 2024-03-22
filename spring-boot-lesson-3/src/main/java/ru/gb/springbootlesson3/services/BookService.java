package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.BookRequest;
import ru.gb.springbootlesson3.entity.Book;
import ru.gb.springbootlesson3.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElse(null);
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    public Book createBook(BookRequest bookRequest) {
        Book book = new Book(bookRequest.getName());
        return bookRepository.save(book);
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book updateBook(long id, BookRequest bookRequest) {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            existingBook.setName(bookRequest.getName());
            return bookRepository.save(existingBook);
        } else {
            throw new NoSuchElementException("Book with id " + id + " not found");
        }
    }
}
