package org.example.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Publication implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    private String title;
    private String author;
    private String publisher;
    private String year;

    public Publication(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}
