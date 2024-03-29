package ru.gb.springbootlesson3.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime issuedAt;

    private LocalDateTime returnedAt;

    public Issue() {
    }

    public Issue(Reader reader, Book book, LocalDateTime issuedAt, LocalDateTime returnedAt) {
        this.reader = reader;
        this.book = book;
        this.issuedAt = issuedAt;
        this.returnedAt = returnedAt;
    }
}
