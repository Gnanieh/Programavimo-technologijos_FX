package org.example.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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
