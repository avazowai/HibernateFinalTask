package org.example.service.serviceImpl;

import org.example.entity.Publisher;
import org.example.repository.AuthorRepository;
import org.example.repository.PublisherRepository;
import org.example.repository.repositoryImpl.AuthorRepositoryImpl;
import org.example.repository.repositoryImpl.PublisherRepositoryImpl;
import org.example.service.PublisherService;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {
    PublisherRepository publisherRepository = new PublisherRepositoryImpl();
    @Override
    public String savePublisher(Publisher publisher) {
        return publisherRepository.savePublisher(publisher);
    }

    @Override
    public Publisher getPublisherById(Long id) {
        return publisherRepository.getPublisherById(id);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.getAllPublishers();
    }

    @Override
    public void updatePublisher(Long id, Publisher publisher) {
     publisherRepository.updatePublisher(id,publisher);
    }

    @Override
    public void deletePublisherByName(String name) {
        publisherRepository.deletePublisherByName(name);
    }
}
