package org.example.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Comment
{
    private int id;
    private String title;
    private String body;
    private List<Comment> replies;
    private User user;

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
}
