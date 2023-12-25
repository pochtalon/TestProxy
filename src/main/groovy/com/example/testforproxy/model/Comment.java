package com.example.testforproxy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "comments")
class Comment {
    @Id
    String id;
    String text;
    LocalDateTime time;
    User user;

    Comment() {}

    Comment(String id, String text, LocalDateTime time, User user) {
        this.id = id;
        this.text = text;
        this.time = time;
        this.user = user;
    }
}
