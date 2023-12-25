package com.example.testforproxy.dto.comment

import com.example.testforproxy.dto.user.UserInfoDto
import lombok.Data

import java.time.LocalDateTime

@Data
class CommentDto {
    String id
    String text
    LocalDateTime time
    UserInfoDto user
}
