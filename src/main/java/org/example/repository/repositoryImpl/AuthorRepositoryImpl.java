package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.HiberbateConfig;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository {
    EntityManagerFactory entityManagerFactory = HiberbateConfig.getEntityManager();

    @Override
    public String saveAuthor(Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(author);
            entityManager.getTransaction().commit();
            return "Saved successfully";
        } catch (Exception e) {
            // Обработка ошибки
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Вывод информации об ошибке
            return "Failed to save author";
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Author existingAuthor = entityManager.find(Author.class, id);

            if (existingAuthor != null) {
                existingAuthor.setFirst_name(author.getFirst_name());
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Author getAuthorById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public List<Author> getAuthorsByPublisherId(Long id) {
        List<Author> authors;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<Author> query = entityManager.createQuery(
                    "SELECT a FROM Author a JOIN a.publishers p WHERE p.id = :publisherId", Author.class
            );
            query.setParameter("publisherId", id);
            authors = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }
        return authors;
    }
    @Override
    public void deleteAuthorById(Long id) {

    }
}
