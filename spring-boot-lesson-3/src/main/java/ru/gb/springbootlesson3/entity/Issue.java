package ru.gb.springbootlesson3.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Issue {

    @Id
    @GeneratedValue(long l, long m, LocalDateTime localDateTime, Object objectstrategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime issuedAt;

    private LocalDateTime returnedAt;
}
