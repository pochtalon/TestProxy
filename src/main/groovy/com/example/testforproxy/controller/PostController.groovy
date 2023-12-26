package com.example.testforproxy.controller

import com.example.testforproxy.dto.comment.CommentDto
import com.example.testforproxy.dto.comment.CommentRequestDto
import com.example.testforproxy.dto.post.PostLikesDto
import com.example.testforproxy.dto.post.PostRequestDto
import com.example.testforproxy.dto.post.PostDto
import com.example.testforproxy.model.User
import com.example.testforproxy.service.PostService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.data.domain.Pageable

@Tag(name = "Posts management", description = "Endpoints for managing posts")
@RestController
@RequestMapping('/api/posts')
class PostController {
    @Autowired PostService postService

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping()
    @Operation(summary = "Create new post", description = "Create new post")
    PostDto createPost(Authentication authentication,
                       @RequestBody @Valid PostRequestDto requestDto) {
        User user = (User) authentication.getPrincipal()
        postService.save(requestDto, user)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping()
    @Operation(summary = "Update post", description = "Update post by id")
    PostDto updatePost(Authentication authentication, @RequestParam String id,
                       @RequestBody @Valid PostRequestDto requestDto) {
        User user = (User) authentication.getPrincipal()
        postService.update(requestDto, user, id)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping()
    @Operation(summary = "Delete post", description = "Delete post by id")
    void deletePost(Authentication authentication, @RequestParam String postId) {
        User user = (User) authentication.getPrincipal()
        postService.delete(user, postId)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping()
    @Operation(summary = "Get all user's post", description = "Get all user's post by user's id")
    List<PostDto> getAllPostByUserId(@RequestParam String userId, Pageable page) {
        postService.getPostsForUser(userId, page)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping('/{postId}/likes')
    @Operation(summary = "Add like", description = "Add like to post by post id")
    PostDto addLike(Authentication authentication,
                    @PathVariable String postId) {
        User user = (User) authentication.getPrincipal()
        postService.addLike(postId, user)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping('/{postId}/likes')
    @Operation(summary = "Get likes", description = "Get all likes for post by post id")
    PostLikesDto getAllLikes(@PathVariable String postId) {
        postService.getLikes(postId)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping('/{postId}/likes')
    @Operation(summary = "Delete like", description = "Delete like from post by post id")
    PostDto deleteLike(Authentication authentication,
                       @PathVariable String postId) {
        User user = (User) authentication.getPrincipal()
        postService.deleteLike(postId, user)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping('/{postId}/comments')
    @Operation(summary = "Add comment", description = "Add comment to post by post id")
    CommentDto addComment(Authentication authentication,
                          @RequestBody @Valid CommentRequestDto requestDto,
                          @PathVariable String postId) {
        User user = (User) authentication.getPrincipal()
        postService.addComment(postId, user, requestDto)
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping('/{postId}/comments')
    @Operation(summary = "Get comments", description = "Get comments to post by post id")
    List<CommentDto> getComments(@PathVariable String postId, Pageable page) {
        postService.getComments(postId, page)
    }
}
