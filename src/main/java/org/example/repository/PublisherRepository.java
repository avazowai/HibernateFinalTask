package org.example.repository;

import org.example.entity.Publisher;

import java.util.List;

public interface PublisherRepository {
    String savePublisher (Publisher publisher);
    Publisher getPublisherById(Long id) ;
    List<Publisher> getAllPublishers();
    //(аты боюнча сорттоп чыгаруу),
    void updatePublisher(Long id, Publisher publisher);
    void deletePublisherByName(String name);
    //(издательствону очургондо, ага тиешелуу китептер жана авторлор  очпошу керек), методдорун тузуп, ишке ашыруу.
}
