package org.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Chat extends Comment {

    public Chat(String title, String body, Client client, Client commentOwner) {
        super(title, body, client, commentOwner);
    }

    public Chat(String title, String body, Comment parentComment, Client commentOwner) {
        super(title, body, parentComment, commentOwner);
    }
}