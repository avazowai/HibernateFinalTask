package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.enums.Gender;

import java.util.List;
@Entity
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_gen"
    )
    @SequenceGenerator(
            name = "author_gen",
            sequenceName = "author_seq",
            allocationSize = 1
    )
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private int date_of_birth;
    private String country;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany( mappedBy = "author",cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Book> books;
    @ManyToMany (cascade ={
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    private List<Publisher> publishers;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(int date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public Author(String first_name, String last_name, String email, int date_of_birth, String country, Gender gender, List<Book> books, List<Publisher> publishers) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.books = books;
        this.publishers = publishers;

    }
}
