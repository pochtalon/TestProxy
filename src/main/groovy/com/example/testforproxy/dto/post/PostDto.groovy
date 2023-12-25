package com.example.testforproxy.dto.post

import com.example.testforproxy.model.Comment
import lombok.Data

import java.time.LocalDateTime

@Data
class PostDto {
    String id
    String text
    LocalDateTime time
    String user
    Set<String> likes
    List<Comment> comments
}
