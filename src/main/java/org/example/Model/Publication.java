package org.example.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public abstract class Publication implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private String publisher;
    private LocalDate year;
    private String type;
    @ManyToOne
    private User client;

    public Publication(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Publication(String title, String author, String publisher, LocalDate year, String type) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.type = type;
    }

    public Publication(String title, String author, String publisher, LocalDate year, String type, User client) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.type = type;
        this.client = client;
    }
}
