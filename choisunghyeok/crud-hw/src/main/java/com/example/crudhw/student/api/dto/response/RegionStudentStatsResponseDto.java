package com.example.crudhw.student.api.dto.response;

import com.example.crudhw.kosis.dto.KosisResponseDto;
import com.example.crudhw.student.domain.Student;

public record RegionStudentStatsResponseDto(
        String studentName,
        String studentNumber,
        String regionCode,
        String regionName,
        String studentCount,
        String unit,
        String year
) {
    public static RegionStudentStatsResponseDto of(Student student, KosisResponseDto kosisDto) {
        return new RegionStudentStatsResponseDto(
                student.getName(),
                student.getStudentNumber(),
                kosisDto.getRegionCode(),
                kosisDto.getRegionName(),
                kosisDto.getStudentCount(),
                kosisDto.getUnit(),
                kosisDto.getYear()
        );
    }
}
