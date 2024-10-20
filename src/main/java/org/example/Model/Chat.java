package org.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat extends Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    private Book book;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Comment> messages;

    private LocalDateTime dateTime;

    public Chat(int id, String title, String body, User user, int id1, LocalDateTime dateTime) {
        super(id, title, body, user);
        this.id = id1;
        this.dateTime = dateTime;
    }

    public Chat(int id, String title, String body, List<Comment> replies, User user, int id1, LocalDateTime dateTime) {
        super(id, title, body, replies, user);
        this.id = id1;
        this.dateTime = dateTime;
    }

    public Chat(Book book, List<Comment> messages) {
        this.book = book;
        this.messages = messages;
    }
}
