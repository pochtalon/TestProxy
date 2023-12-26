package com.example.testforproxy.service

import com.example.testforproxy.dto.comment.CommentDto
import com.example.testforproxy.dto.comment.CommentRequestDto
import com.example.testforproxy.dto.post.PostLikesDto
import com.example.testforproxy.dto.post.PostRequestDto
import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.model.User

import org.springframework.data.domain.Pageable

interface PostService {

    PostDto save(PostRequestDto createPostRequestDto, User user)

    PostDto update(PostRequestDto postRequestDto, User user, String id)

    void delete(User user, String id)

    PostDto addLike(String postId, User user)

    PostDto deleteLike(String postId, User user)

    PostLikesDto getLikes(String id)

    CommentDto addComment(String postId, User user, CommentRequestDto requestDto)

    List<PostDto> getPostsForUser(String userId, Pageable page)

    List<CommentDto> getComments(String postId, Pageable page)
}