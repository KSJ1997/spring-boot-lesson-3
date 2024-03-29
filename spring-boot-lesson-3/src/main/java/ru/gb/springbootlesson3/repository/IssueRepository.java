package ru.gb.springbootlesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    long countByReaderId(long readerId);
    List<Issue> findByReaderId(long readerId);
}
