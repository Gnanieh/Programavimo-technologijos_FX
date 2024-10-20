package org.example.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends Publication
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    private String isbn;
    private int pageCount;
    private String summary;

    public Book(String title, String author, String publisher, LocalDate year, String type, String isbn, int pageCount, String summary) {
        super(title, author, publisher, year, type);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.summary = summary;
    }

    public Book(String title, String author, String publisher, LocalDate year, String type, Client client, String isbn, int pageCount, String summary) {
        super(title, author, publisher, year, type, client);
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.summary = summary;
    }
}
