package com.example.testforproxy.mapper

import com.example.testforproxy.dto.comment.CommentDto
import com.example.testforproxy.model.Comment

interface CommentMapper {

    CommentDto toDto(Comment comment)
}