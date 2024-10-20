package org.example.Model;

public class Book extends Publication
{
    private int id;
    private String publisher;
    private String isbn;
    private int pageCount;
    private int publicationYear;
    private String summary;

    public Book(int id, String title, String author, String publisher, String year, int id1, String publisher1, String isbn, int pageCount, int publicationYear, String summary) {
        super(id, title, author, publisher, year);
        this.id = id1;
        this.publisher = publisher1;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.publicationYear = publicationYear;
        this.summary = summary;
    }
}
