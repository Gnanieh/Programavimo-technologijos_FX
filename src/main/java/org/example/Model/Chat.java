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
@AllArgsConstructor
@Entity
public class Chat
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private List<Comment> messages;

    private String body;
    private LocalDateTime dateTime;

    public Chat(String body, LocalDateTime dateTime) {
        this.body = body;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return body + " " + dateTime;
    }
}