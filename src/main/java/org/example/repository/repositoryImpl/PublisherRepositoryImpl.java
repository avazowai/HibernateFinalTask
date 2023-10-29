package org.example.repository.repositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.HiberbateConfig;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.repository.PublisherRepository;
import org.hibernate.HibernateException;
import java.util.List;

public class PublisherRepositoryImpl implements PublisherRepository {
    EntityManagerFactory entityManagerFactory = HiberbateConfig.getEntityManager();

    @Override
    public String savePublisher(Publisher publisher) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(publisher);
            entityManager.getTransaction().commit();
            return "Saved successfully";
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
            return "Failed to save publisher";
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Publisher getPublisherById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Publisher publisher = entityManager.find(Publisher.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }


    @Override
    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Publisher> criteria = builder.createQuery(Publisher.class);
            Root<Publisher> root = criteria.from(Publisher.class);
            criteria.orderBy(builder.desc(root.get("name")));
            publishers = entityManager.createQuery(criteria).getResultList();

            for (Publisher publisher1 : publishers) {
                System.out.println("Name: " + publisher1.getName());
                System.out.println("ID: " + publisher1.getId());
                System.out.println("Address: " + publisher1.getAddress());
            }
            entityManager.getTransaction().commit();
            return publishers;
        } finally {

        }
    }

    @Override
    public void updatePublisher(Long id, Publisher publisher) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.merge(publisher);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
        public void deletePublisherByName (String name){
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT p FROM Publisher p WHERE p.name = :name");
            query.setParameter("name", name);
            Publisher publisher = (Publisher) query.getSingleResult();
            List<Book> books = publisher.getBooks();
            for (Book b : books) {
                b.setPublisher(null);
                entityManager.merge(b);
            }
            publisher.getBooks().clear();
            List<Author> authors = publisher.getAuthors();
            for (Author a : authors) {
                a.getPublishers().remove(publisher);
                entityManager.merge(a);
            }
            publisher.getAuthors().clear();
            entityManager.remove(publisher);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
