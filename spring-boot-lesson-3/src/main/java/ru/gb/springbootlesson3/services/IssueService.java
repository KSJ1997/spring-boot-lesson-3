package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.controllers.IssueRequest;
import ru.gb.springbootlesson3.entity.Issue;
import ru.gb.springbootlesson3.repository.BookRepository;
import ru.gb.springbootlesson3.repository.IssueRepository;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    @Value("${application.issue.max-allowed-books:1}")
    private int maxAllowedBooks;

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()).isEmpty()) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getReaderId()).isEmpty()) {
            log.info("Не удалось найти читателя с id " + request.getReaderId());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getReaderId());
        }

        if (issueRepository.countBooksIssuedToReader(request.getReaderId()) >= maxAllowedBooks) {
            log.info("Пользователь уже имеет на руках максимально допустимое количество книг");
            throw new IllegalStateException("Пользователь уже имеет на руках максимально допустимое количество книг");
        }

        Issue issue = new Issue(request.getReaderId(), request.getBookId(), LocalDateTime.now(), null);
        issueRepository.createIssue(issue);
        return issue;
    }

    public List<Issue> getIssuesForReader(long readerId) {
        return issueRepository.findIssuesByReaderId(readerId);
    }

    public void closeIssue(long issueId) {
        Issue issue = issueRepository.findById(issueId);
        if (issue == null) {
            log.info("Не удалось найти выдачу с id " + issueId);
            throw new NoSuchElementException("Не удалось найти выдачу с id " + issueId);
        }
        if (issue.getReturnedAt() != null) {
            log.info("Выдача с id " + issueId + " уже закрыта");
            throw new IllegalStateException("Выдача с id " + issueId + " уже закрыта");
        }

        issue.setReturnedAt(LocalDateTime.now());
        issueRepository.save(issue);
    }

    public Issue getIssueById(long id) {
        return issueRepository.findById(id);
    }

    public Object getAllIssues() {
        throw new UnsupportedOperationException("Unimplemented method 'getAllIssues'");
    }
}