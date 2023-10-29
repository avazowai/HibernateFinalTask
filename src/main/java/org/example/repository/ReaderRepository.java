package org.example.repository;


import org.example.entity.Reader;

public interface ReaderRepository {
    String saveReader(Reader reader);
    void updateReader(Long bookId, Long readerId, Reader reader);
    Reader getReaderByBookId(Long id);
    Reader deleteReaderById(Long id);
    Reader getReadersByAuthorId(Long id);
}
