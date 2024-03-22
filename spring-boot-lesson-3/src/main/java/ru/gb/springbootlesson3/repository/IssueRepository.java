package ru.gb.springbootlesson3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {
    private List<Issue> list = new ArrayList<>();

    public void createIssue(Issue issue) {
        list.add(issue);
    }

    public Issue findById(long id) {
        return list.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public int countBooksIssuedToReader(long readerId) {
        return (int) list.stream()
                .filter(issue -> issue.getReaderId() == readerId)
                .count();
    }

    public List<Issue> findIssuesByReaderId(long readerId) {
        return list.stream()
                .filter(issue -> issue.getReaderId() == readerId)
                .collect(Collectors.toList());
    }
    
    public void save(Issue issue) {
        list.add(issue);
    }
}
