package org.example.Model;

public class Manga  extends Publication
{
    private int id;

    public Manga(int id, int id1, String title, String author, String publisher, String year, int id2) {
        super(id, id1, title, author, publisher, year);
        this.id = id2;
    }

    public Manga(int id, String title, String author, String publisher, String year, int id1) {
        super(id, title, author, publisher, year);
        this.id = id1;
    }
}
