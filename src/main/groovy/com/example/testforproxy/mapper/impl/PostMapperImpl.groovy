package com.example.testforproxy.mapper.impl

import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.dto.post.PostLikesDto
import com.example.testforproxy.mapper.PostMapper
import com.example.testforproxy.model.Post
import org.springframework.stereotype.Component

@Component
class PostMapperImpl implements PostMapper {
    @Override
    PostDto toDto(Post post) {
        def dto = [id: post.id, text: post.text, time: post.time, user: post.user.id] as PostDto
        dto.likes = post.likes.stream()
        .map {user -> user.id}
        .collect()
        dto.comments = post.comments
        return dto
    }

    @Override
    PostLikesDto toLikesDto(Post post) {
        def likes = post.likes.stream()
        .map {u ->u.id}
        .toList()
        [likes: likes] as PostLikesDto
    }
}
