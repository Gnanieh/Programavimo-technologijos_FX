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
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Transient
    private List<Comment> messages;

    private LocalDateTime dateTime;

//    public Chat(String title, String body, LocalDateTime timestamp, List<Comment> replies, Comment parentComment, Client client, Book book, List<Comment> messages, LocalDateTime dateTime) {
//        super(title, body, replies, parentComment);
//        this.book = book;
//        this.messages = messages;
//        this.dateTime = dateTime;
//    }

    public Chat(String title, String body, User user) {
        super(title, body, user);
    }
}
