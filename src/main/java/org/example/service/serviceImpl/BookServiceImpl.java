package org.example.service.serviceImpl;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.repository.BookRepository;
import org.example.repository.repositoryImpl.BookRepositoryImpl;
import org.example.service.BookService;

import java.util.Map;

public class BookServiceImpl implements BookService {
    BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public String saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public void updateBookAuthor(Long bookId ,Long auhtorId, Author author) {
       bookRepository.updateBookAuthor(bookId,auhtorId,author);

    }

    @Override
    public Map<Book, Publisher> getBookAndPublisherByBookId(Long id) {
        return bookRepository.getBookAndPublisherByBookId(id);
    }

    @Override
    public void deleteBookByAuthorId(Long id,Long authorID) {
        deleteBookByAuthorId(id, authorID);
    }
}
