package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.springbootlesson3.services.BookService;

@Controller
public class BookUIController {

    @Autowired
    private BookService bookService;

    @GetMapping("/ui/books")
    public String showAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }
}
