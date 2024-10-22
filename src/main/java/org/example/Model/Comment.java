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
    @ManyToOne
    private User user;
    private LocalDate timestamp;
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL)
    private List<Comment> replies;
    @ManyToOne
    private Comment parentComment;
    @ManyToOne
    private Publication publication;


    public Comment(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.timestamp = LocalDate.now();
        this.user = user;
    }

    public Comment(String title, String body, User user, Publication publication) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.timestamp = LocalDate.now();
        this.publication = publication;
    }

    @Override
    public String toString() {
        return title + " " + body + " " + timestamp;
    }
}
