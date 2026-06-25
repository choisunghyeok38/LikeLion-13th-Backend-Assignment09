package com.example.crudhw.assignment.api.dto.response;

import com.example.crudhw.assignment.domain.Assignment;
import lombok.Builder;

@Builder
public record AssignmentInfoResponseDto(
        String title,
        String contents,
        String writer
) {
    public static AssignmentInfoResponseDto from(Assignment assignment) {
        return AssignmentInfoResponseDto.builder()
                .title(assignment.getTitle())
                .contents(assignment.getContents())
                .writer(assignment.getStudent().getName())
                .build();
    }
}
