package com.example.crudhw.student.api.dto.request;

import com.example.crudhw.student.domain.Major;
import jakarta.validation.constraints.*;

public record StudentSaveRequestDto(
        @NotBlank(message = "이름을 필수로 입력해야 합니다.")
        String name,

        @Pattern(regexp = "^[0-9]{9}$", message = "학번은  9자리 숫자여야 합니다.")
        String studentNumber,

        @NotNull(message = "전공을 필수로 입력해야 합니다")
        Major major

) {
}
