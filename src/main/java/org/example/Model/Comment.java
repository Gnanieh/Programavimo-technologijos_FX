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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String body;
    @ManyToOne
    private User user;
    private LocalDateTime timestamp;
    @Transient
    private List<Comment> replies;
    @Transient
    private Comment parentComment;
    @Transient
    private Chat chat;

    public Comment(int id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Comment(int id, String title, String body, List<Comment> replies, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.replies = replies;
        this.user = user;
    }

    public Comment(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.timestamp = LocalDateTime.now();
        this.user = user;
    }

    @Override
    public String toString() {
        return title + " " + timestamp;
    }
}
