package org.example.service;


import org.example.entity.Reader;

public interface ReaderService {
    String saveReader(Reader reader);
    void updateReader(Long bookId, Long readerId, Reader reader);
    Reader getReaderByBookId(Long id);
    Reader deleteReaderById(Long id);
    Reader getReadersByAuthorId(Long id);
}
