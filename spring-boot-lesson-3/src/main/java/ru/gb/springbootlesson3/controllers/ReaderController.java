package ru.gb.springbootlesson3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.services.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @GetMapping("/{id}")
    public ResponseEntity<Reader> getReader(@PathVariable long id) {
        return readerService.getReaderById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable long id) {
        readerService.deleteReaderById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody Reader reader) {
        Reader createdReader = readerService.createReader(reader);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReader);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Reader>> getAllReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return ResponseEntity.ok(readers);
    }
}
