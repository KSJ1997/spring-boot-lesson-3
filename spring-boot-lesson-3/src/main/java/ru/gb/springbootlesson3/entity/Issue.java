package ru.gb.springbootlesson3.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static long genId;

    private final long id;
    private final long readerId;
    private final long bookId;
    private final LocalDateTime issuedAt;
    private LocalDateTime returnedAt;

    public Issue(long readerId, long bookId) {
        id = genId++;
        this.readerId = readerId;
        this.bookId = bookId;
        this.issuedAt = LocalDateTime.now();
        this.returnedAt = null;
    }

    public Issue(long readerId, long bookId, LocalDateTime issuedAt, LocalDateTime returnedAt) {
        id = genId++;
        this.readerId = readerId;
        this.bookId = bookId;
        this.issuedAt = issuedAt;
        this.returnedAt = returnedAt;
    }

    public LocalDateTime getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }
}
