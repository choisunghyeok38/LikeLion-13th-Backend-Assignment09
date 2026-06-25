package com.example.crudhw.student.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StudentUpdateRequestDto (

        @NotBlank(message = "이름은 필수로 입력해야 합니다.")
        String name,

        @NotBlank(message = "학번은 필수로 입력해야 합니다.")
        String studentNumber
) {
}