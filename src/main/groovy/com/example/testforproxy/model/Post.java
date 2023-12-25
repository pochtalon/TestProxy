package com.example.testforproxy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "posts")
class Post {
    @Id
    String id;
    String text;
    LocalDateTime time;
    User user;
    List<User> likes;
    List<Comment> comments;

    Post() {}

    Post(String id, String text, LocalDateTime time, User user, List<User> likes, List<Comment> comments) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.user = user;
        this.likes = likes;
        this.comments = comments;
    }
}
