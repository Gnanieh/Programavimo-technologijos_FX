package org.example.Model;

import java.time.LocalDate;
import java.util.List;

public class Admin extends User
{
    private int phoneNumber;


    public Admin(int id, String login, String password, String name, String surname, LocalDate dateCreated, int phoneNumber)
    {
        super(id, login, password, name, surname, dateCreated);
        this.phoneNumber = phoneNumber;
    }

    public Admin(int id, String login, String password, String name, String surname, int phoneNumber)
    {
        super(id, login, password, name, surname);
        this.phoneNumber = phoneNumber;
        dateCreated = LocalDate.now();
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + phoneNumber;
    }
}
