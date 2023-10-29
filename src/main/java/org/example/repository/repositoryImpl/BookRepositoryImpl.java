package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.HiberbateConfig;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.repository.BookRepository;
import org.hibernate.HibernateException;

import java.util.Map;

public class BookRepositoryImpl implements BookRepository {
    EntityManagerFactory entityManagerFactory = HiberbateConfig.getEntityManager();

    @Override
    public String saveBook(Book book) {
        Book books;
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return "The book saved successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save the book: " + e.getMessage();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateBookAuthor(Long id, Long auhtorId, Author author) {
        Author author1;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Book book = entityManager.find(Book.class, id);
            author1 = entityManager.find(Author.class, id);
            if (author1.getId() == book.getAuthor().getId()) {
                book.setAuthor(author);
                entityManager.merge(book);
            } else {
                System.out.println("Этой айдишки нету");
                entityManager.getTransaction().rollback();
            }
            entityManager.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Map<Book, Publisher> getBookAndPublisherByBookId(Long id) {
        Book book1;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            Query query = entityManager.createQuery("select " +
                    "b from Book b join Publisher p ON p.id=:bookId", Book.class);
            query.setParameter("bookId", id);
            book1 = (Book) query.getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteBookByAuthorId(Long bookID, Long authorID) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Author author = entityManager.find(Author.class, authorID);
            Book book = entityManager.find(Book.class, bookID);

            if (book != null && author != null) {
                if (book.getAuthor().equals(author)) {
                    book.setAuthor(null);
                    entityManager.remove(book);
                } else {
                    System.out.println("Книга не связана с указанным автором");
                }
            } else {
                System.out.println("Книга или автор не найдены");
            }

            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}