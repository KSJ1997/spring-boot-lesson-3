package ru.gb.springbootlesson3.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.springbootlesson3.entity.Reader;
import ru.gb.springbootlesson3.repository.ReaderRepository;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ResponseEntity<Reader> getReaderById(long id) {
        return readerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public void deleteReaderById(long id) {
        readerRepository.deleteById(id);
    }

    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }
}
