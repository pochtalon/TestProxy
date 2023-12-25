package com.example.testforproxy.dto.post

import jakarta.validation.constraints.NotEmpty
import lombok.Data

@Data
class PostRequestDto {
    @NotEmpty
    String text
}
