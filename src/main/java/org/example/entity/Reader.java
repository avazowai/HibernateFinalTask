package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "readers")
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reader_gen")
    @SequenceGenerator(name = "reader_gen",
            sequenceName = "reader_seq",
            allocationSize = 1)
    private Long id;
    private String name;
    private int age;
    private String email;

    @OneToMany(mappedBy = "reader",cascade = CascadeType.ALL)
    private List<Book> book;

    public Reader() {
    }

    public Reader(String name, int age, String email, List<Book> book) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.book = book;
    }
}
