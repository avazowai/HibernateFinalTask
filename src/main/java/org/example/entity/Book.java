package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Genre;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "pub_gen")
    @SequenceGenerator(name ="pub_gen",sequenceName = "pub_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private int published_year;
    private int price;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    private Reader reader;
    @ManyToOne
    private Author author;
    @ManyToOne (cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    private Publisher publisher;

    public Book() {
    }
    public Book(String name, String country,
                int published_year, int price, Genre genre,
                Reader reader, Author author,
                Publisher publisher) {
        this.name = name;
        this.country = country;
        this.published_year = published_year;
        this.price = price;
        this.genre = genre;
        this.reader = reader;
        this.author = author;
        this.publisher = publisher;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
