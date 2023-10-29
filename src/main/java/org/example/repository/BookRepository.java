package org.example.repository;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;

import java.util.Map;

public interface BookRepository{
    String saveBook(Book book);
    //(Book сакталып жатканда кандайдыр бир авторго тиешелуу болуп сакталуусу керек),
    void updateBookAuthor(Long id , Long auhtorId, Author author);
    Map<Book, Publisher> getBookAndPublisherByBookId(Long id);
    //(Бир Id ге тиешелуу book тун маалыматтары жана ага тиешелуу издательствосу чыксын),
    void deleteBookByAuthorId(Long id,Long authorID);


}
