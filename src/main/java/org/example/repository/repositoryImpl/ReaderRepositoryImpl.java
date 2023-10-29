package org.example.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.HiberbateConfig;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Reader;
import org.example.repository.ReaderRepository;
import org.hibernate.HibernateException;

import java.util.List;


public abstract class ReaderRepositoryImpl implements ReaderRepository {
 EntityManagerFactory entityManagerFactory = HiberbateConfig.getEntityManager();

 @Override
 public String saveReader(Reader reader) {
  Reader reader1;
  try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
   entityManager.getTransaction().begin();
   Reader existingReader = entityManager.find(Reader.class, reader.getId());

   if (existingReader != null) {
    existingReader.setName(reader.getName());
    existingReader.setEmail(reader.getEmail());
    existingReader.setBook(reader.getBook());
    entityManager.merge(existingReader);
   } else {
    entityManager.persist(reader);
   }

   entityManager.getTransaction().commit();
   return "Успешно";
  } catch (HibernateException e) {
   throw new RuntimeException(e.getMessage());
  }
 }


 @Override
 public void updateReader(Long bookId, Long readerId, Reader newReader) {
  Reader updatedReader;
  try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
   entityManager.getTransaction().begin();
   updatedReader = entityManager.find(Reader.class, readerId);

   if (updatedReader != null) {
    updatedReader.setName(newReader.getName());
    updatedReader.setEmail(newReader.getEmail());
    updatedReader.setBook(newReader.getBook());
    entityManager.getTransaction().commit();
   } else {
    throw new RuntimeException("Читатель с указанным ID не найден");
   }
  } catch (HibernateException e) {
   throw new RuntimeException(e.getMessage());
  }
 }

 @Override
 public Reader getReaderByBookId(Long bookId) {
  Reader reader = null;
  try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
   entityManager.getTransaction().begin();
   Book book = entityManager.find(Book.class, bookId);

   if (book != null && book.getReader() != null) {
    reader = book.getReader();
   } else {
    System.out.println("Читатель не найден");
   }
   entityManager.getTransaction().commit();
  } catch (HibernateException e) {
   throw new RuntimeException(e.getMessage());
  }
  return reader;
 }


 @Override
 public Reader getReadersByAuthorId(Long authorId) {
  Reader reader = null;

  try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
   entityManager.getTransaction().begin();
   Author author = entityManager.find(Author.class, authorId);

   if (author != null) {
    List<Book> books = author.getBooks();

    if (books != null && !books.isEmpty()) {
     Book book = books.get(0);
     reader = book.getReader();
    }
   }

   entityManager.getTransaction().commit();
  } catch (HibernateException e) {
   throw new RuntimeException(e.getMessage());
  }

  return reader;
 }
}

