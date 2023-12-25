package com.example.testforproxy.mapper

import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.dto.post.PostLikesDto
import com.example.testforproxy.model.Post

interface PostMapper {

    PostDto toDto(Post post)

    PostLikesDto toLikesDto(Post post)
}