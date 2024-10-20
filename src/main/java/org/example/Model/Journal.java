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

public class Journal  extends Publication
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    public Journal(String title, String author, String publisher, LocalDate year, String type, Client client) {
        super(title, author, publisher, year, type, client);
    }
}
