package org.example.service.serviceImpl;

import org.example.entity.Reader;
import org.example.repository.ReaderRepository;
import org.example.repository.repositoryImpl.ReaderRepositoryImpl;
import org.example.service.ReaderService;


public abstract class ReaderServiceImpl implements ReaderService {
    ReaderRepository readerRepository = new ReaderRepositoryImpl() {

        @Override
        public String saveReader(Reader reader) {
            return readerRepository.saveReader(reader);
        }

        @Override
        public void updateReader(Long bookId, Long readerId, Reader reader) {
            readerRepository.updateReader(bookId, readerId, reader);
        }

        @Override
        public Reader getReaderByBookId(Long id) {
            return readerRepository.getReaderByBookId(id);
        }

        @Override
        public Reader deleteReaderById(Long id) {
            return readerRepository.deleteReaderById(id);
        }

        @Override
        public Reader getReadersByAuthorId(Long id) {
            return readerRepository.getReadersByAuthorId(id);
        }
    };
}
