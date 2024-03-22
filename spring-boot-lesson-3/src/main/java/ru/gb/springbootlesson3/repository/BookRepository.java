package ru.gb.springbootlesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson3.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
