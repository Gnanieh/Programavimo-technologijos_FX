package org.example.Model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Manga  extends Publication
{
    private String illustrator;
    private String originalLanguage;
    private int volumeNumber;
    private boolean isColor;

    public Manga(int id, String title, String author, String publisher, String year, String illustrator, String originalLanguage, int volumeNumber, boolean isColor) {
        super(id, title, author, publisher, year);
        this.illustrator = illustrator;
        this.originalLanguage = originalLanguage;
        this.volumeNumber = volumeNumber;
        this.isColor = isColor;
    }
}
