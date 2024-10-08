package org.example.Model;

import java.util.Date;

public class PublicationRecord
{
    private int id;
    private Date borrowDate;
    private Date returnDate;
    private String status;

    public PublicationRecord(int id)
    {
        this.id = id;
    }
}
