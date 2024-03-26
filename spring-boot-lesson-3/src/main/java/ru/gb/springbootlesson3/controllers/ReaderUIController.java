package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.ReaderService;

@Controller
public class ReaderUIController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/ui/reader")
    public String showAllReaders(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        return "readers";
    }

    @GetMapping("/ui/reader/{id}")
    public String showReaderDetails(@PathVariable long id, Model model) {
        ResponseEntity<Reader> reader = readerService.getReaderById(id);
        model.addAttribute("reader", reader);
        return "readerDetails";
    }
}
