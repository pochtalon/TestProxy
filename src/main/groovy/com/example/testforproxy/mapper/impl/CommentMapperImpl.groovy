package com.example.testforproxy.mapper.impl

import com.example.testforproxy.dto.comment.CommentDto
import com.example.testforproxy.mapper.CommentMapper
import com.example.testforproxy.mapper.UserMapper
import com.example.testforproxy.model.Comment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CommentMapperImpl implements CommentMapper {
    @Autowired UserMapper userMapper

    @Override
    CommentDto toDto(Comment comment) {
        def userDto = userMapper.toInfoDto(comment.user)
        [id: comment.id, text: comment.text, time: comment.time, user: userDto] as CommentDto
    }
}
