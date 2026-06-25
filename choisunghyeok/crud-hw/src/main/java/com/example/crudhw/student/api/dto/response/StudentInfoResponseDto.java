package com.example.crudhw.student.api.dto.response;

import com.example.crudhw.student.domain.Major;
import com.example.crudhw.student.domain.Student;
import lombok.Builder;

@Builder
public record StudentInfoResponseDto(
        String name,
        String studentNumber,
        Major major
) {
    public static StudentInfoResponseDto from(Student student) {
        return StudentInfoResponseDto.builder()
                .name(student.getName())
                .studentNumber(student.getStudentNumber())
                .major(student.getMajor())
                .build();
    }
}