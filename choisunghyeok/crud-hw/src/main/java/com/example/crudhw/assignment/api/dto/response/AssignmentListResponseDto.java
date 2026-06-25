package com.example.crudhw.assignment.api.dto.response;

import com.example.crudhw.assignment.domain.Assignment;
import lombok.Builder;

import java.util.List;

@Builder
public record AssignmentListResponseDto(
        List<AssignmentInfoResponseDto> assignments
) {
    public static AssignmentListResponseDto from(List<AssignmentInfoResponseDto> assignments) {
        return AssignmentListResponseDto.builder()
                .assignments(assignments)
                .build();
    }
}
