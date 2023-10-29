package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "publishers")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Publisher {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pub_gen")
    @SequenceGenerator(
            name = "pub_gen",
            sequenceName = "pub_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    @ManyToMany(mappedBy = "publishers", cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private List<Author> authors;
    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.ALL
    })
    private List<Book> books;

    public Publisher() {
    }

    public Publisher(String name, String address, List<Author> authors, List<Book> books) {
        this.name = name;
        this.address = address;
        this.authors = authors;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}