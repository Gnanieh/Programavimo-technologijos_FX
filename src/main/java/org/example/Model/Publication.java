package org.example.Model;

public class Publication extends PublicationRecord
{

    private int id;
    private String title;
    private String author;
    private String publisher;
    private String year;

    public Publication(int id, int id1, String title, String author, String publisher, String year) {
        super(id);
        this.id = id1;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    public Publication(int id, String title, String author, String publisher, String year) {
        super(id);
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }
}
