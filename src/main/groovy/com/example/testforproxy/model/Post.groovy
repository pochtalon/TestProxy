package com.example.testforproxy.model

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Data
@Document(collection = "posts")
class Post {
    @Id
    String id
    String text
    LocalDateTime time
    User user
    Set<User> likes
    List<Comment> comments

    Post() {

    }
}
