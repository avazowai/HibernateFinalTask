package org.example.service.serviceImpl;

import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.example.repository.repositoryImpl.AuthorRepositoryImpl;
import org.example.service.AuthorService;

public class AuthorServiceImpl implements AuthorService {
    AuthorRepository authorRepository = new AuthorRepositoryImpl();

    @Override
    public String saveAuthor(Author author) {
        return authorRepository.saveAuthor(author);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        authorRepository.updateAuthor(id,author);
    }

    @Override
    public Author getAuthorById(Long id) {
return authorRepository.getAuthorById(id);
    }

    @Override
    public Author getAuthorsByPublisherId(Long id) {
        return (Author) authorRepository.getAuthorsByPublisherId(id);
    }

    @Override
    public void deleteAuthorById(Long id) {
         authorRepository.deleteAuthorById(id);

    }
}
