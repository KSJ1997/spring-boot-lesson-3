package ru.gb.springbootlesson3.entity;

import lombok.Data;

@Data
public class Book {
    private static long genId;

    private final long id;
    private String name; // Убрано ключевое слово final

    public Book(String name) {
        id = genId++;
        this.name = name;
    }
}
