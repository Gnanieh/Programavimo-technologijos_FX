package org.example.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Chat extends Comment
{
    private int id;
    private String message;
    private LocalDateTime dateTime;

    public Chat(int id, String title, String body, User user, int id1, String message, LocalDateTime dateTime) {
        super(id, title, body, user);
        this.id = id1;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Chat(int id, String title, String body, List<Comment> replies, User user, int id1, String message, LocalDateTime dateTime) {
        super(id, title, body, replies, user);
        this.id = id1;
        this.message = message;
        this.dateTime = dateTime;
    }
}
