package org.example.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Client extends User
{
    private String email;
    private String address;
    private LocalDate birthDate;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Comment> reviews;
    @Transient
    private List<Publication> ownPublication;
    @Transient
    private List<Publication> borrowPublication;

    public Client(int id, String login, String password, String name, String surname, LocalDate dateCreated, String email, String address, LocalDate birthDate) {
        super(id, login, password, name, surname, dateCreated);
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
    }

    public Client(int id, String login, String password, String name, String surname) {
        super(id, login, password, name, surname);
    }

    public Client(String login, String password, String name, String surname, String address, LocalDate birthDate) {
        super(login, password, name, surname);
        this.address = address;
    }

    @Override
    public String toString() {
        return name + " " + surname + " " + email + " " + address + " " + birthDate;
    }
}
