package org.example;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Publisher;
import org.example.entity.Reader;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Map;
import java.util.Properties;

public class HiberbateConfig {
    public static EntityManagerFactory getEntityManager() {
        Properties properties = new Properties();
        try {
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "1234");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.HBM2DDL_AUTO, "create");
            Configuration configuration = new Configuration();
            configuration.addProperties(properties);

            configuration.addAnnotatedClass(Author.class);
            configuration.addAnnotatedClass(Publisher.class);
            configuration.addAnnotatedClass(Book.class);
            configuration.addAnnotatedClass(Reader.class);

            System.out.println("successfully connected");
            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}