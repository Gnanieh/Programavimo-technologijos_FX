package org.example.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate timestamp;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Comment> replies;
    @ManyToOne
    private Comment parentComment;
    @ManyToOne
    private Publication publication;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private Client commentOwner;


    public Comment(String title, String body, Client client) {
        this.title = title;
        this.body = body;
        this.timestamp = LocalDate.now();
        this.client = client;
    }

    public Comment(String title, String body, Client client, Publication publication) {
        this.title = title;
        this.body = body;
        this.client = client;
        this.timestamp = LocalDate.now();
        this.publication = publication;
    }

    public Comment(String title, String body, LocalDate timestamp, List<Comment> replies, Comment parentComment, Client client) {
        this.title = title;
        this.body = body;
        this.timestamp = timestamp;
        this.replies = replies;
        this.parentComment = parentComment;
        this.client = client;
    }

    public Comment(String title, String body, Client commentOwner, Comment parentComment, LocalDate timestamp) {
        this.title = title;
        this.body = body;
        this.commentOwner = commentOwner;
        this.parentComment = parentComment;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return commentOwner.name + " " + title + " " + body + " " + timestamp;
    }
}
