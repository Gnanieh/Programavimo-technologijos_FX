package org.example.Model;


import lombok.Setter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookExchange {
    private List<Publication> allPublications;
    private List<User> users;

    public BookExchange(List<User> users, List<Publication> allPublications) {
        this.users = users;
        this.allPublications = allPublications;
    }

    public BookExchange(List<User> users) {
        this.users = users;
    }

    public BookExchange() {
        this.users = new ArrayList<>();
        this.allPublications = new ArrayList<>();
    }
}
