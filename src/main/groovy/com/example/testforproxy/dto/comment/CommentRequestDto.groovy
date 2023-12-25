package com.example.testforproxy.dto.comment

import jakarta.validation.constraints.NotEmpty
import lombok.Data

@Data
class CommentRequestDto {
    @NotEmpty
    String text
}
