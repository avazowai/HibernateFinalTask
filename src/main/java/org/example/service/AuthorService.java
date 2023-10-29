package org.example.service;

import org.example.entity.Author;

public interface AuthorService {
    String saveAuthor(Author author);
    void updateAuthor(Long id,Author author);
    Author getAuthorById(Long id);
    Author getAuthorsByPublisherId(Long id);
    //(тиешелуу издательствонун авторлорун чыгарып беруу),
    void deleteAuthorById(Long id);
    //(автор очкондо, авторго тиешелуу издательство очпошу керек, китептер очуш керек), assignAuthorToPublisher()(авторду издательствого кошуп коюу(назначить)).

}
