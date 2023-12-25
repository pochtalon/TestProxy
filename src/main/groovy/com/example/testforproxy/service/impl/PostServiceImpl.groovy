package com.example.testforproxy.service.impl

import com.example.testforproxy.dto.comment.CommentDto
import com.example.testforproxy.dto.comment.CommentRequestDto
import com.example.testforproxy.dto.post.PostLikesDto
import com.example.testforproxy.dto.post.PostRequestDto
import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.exception.EntityNotFoundException
import com.example.testforproxy.mapper.CommentMapper
import com.example.testforproxy.mapper.PostMapper
import com.example.testforproxy.model.Comment
import com.example.testforproxy.model.Post
import com.example.testforproxy.model.User
import com.example.testforproxy.repository.CommentRepository
import com.example.testforproxy.repository.PostRepository
import com.example.testforproxy.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.time.LocalDateTime

@Service
class PostServiceImpl implements PostService {
    @Autowired PostRepository postRepository
    @Autowired PostMapper postMapper
    @Autowired CommentRepository commentRepository
    @Autowired CommentMapper commentMapper

    @Override
    PostDto save(PostRequestDto requestDto, User user) {
        Post post = new Post()
        post.text = requestDto.text
        post.time = LocalDateTime.now()
        post.user = user
        post.likes = Collections.emptySet()
        post.comments = Collections.emptyList()
        postMapper.toDto(postRepository.save(post))
    }

    @Override
    PostDto update(PostRequestDto requestDto, User user, String id) {
        def post = getPostById(id, user)
        post.text = requestDto.text
        postMapper.toDto(postRepository.save(post))
    }

    @Override
    void delete(User user, String id) {
        postRepository.delete(getPostById(id, user))
    }

    @Override
    PostDto addLike(String postId, User user) {
        def post = postRepository.findById(postId).orElseThrow () ->
                new EntityNotFoundException("Post with id " + postId + " was not found")
        post.likes.add(user)
        postMapper.toDto(postRepository.save(post))
    }

    @Override
    PostDto deleteLike(String postId, User user) {
        def post = postRepository.findById(postId).orElseThrow () ->
                new EntityNotFoundException("Post with id " + postId + " was not found")
        post.likes.removeElement(user)
        postMapper.toDto(postRepository.save(post))
    }

    @Override
    PostLikesDto getLikes(String id) {
        def post = postRepository.findById(id).orElseThrow () ->
                new EntityNotFoundException("Post with id " + id + " was not found")
        postMapper.toLikesDto(post)
    }

    @Override
    CommentDto addComment(String postId, User user, CommentRequestDto requestDto) {
        def post = postRepository.findById(postId).orElseThrow () ->
                new EntityNotFoundException("Post with id " + postId + " was not found")
        def comment = [text: requestDto.text, time: LocalDateTime.now(), user: user] as Comment
        comment = commentRepository.save(comment)
        post.getComments().add(comment)
        postRepository.save(post)
        commentMapper.toDto(comment)
    }

    private Post getPostById(String id, User user) {
        def post = postRepository.findById(id).orElseThrow () ->
                new EntityNotFoundException("Post with id " + id + " was not found")
        if (post.user.id != user.id) () ->
                new RuntimeException("Current user doesnt have access for post")
        return post
    }
}
