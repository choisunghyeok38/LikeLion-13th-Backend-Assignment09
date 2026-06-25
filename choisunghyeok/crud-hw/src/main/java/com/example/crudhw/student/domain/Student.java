package com.example.crudhw.student.domain;

import com.example.crudhw.assignment.domain.Assignment;
import com.example.crudhw.student.api.dto.request.StudentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String name;

    private String studentNumber;
    /* 왜 학번이 String
        1. 숫자로 계산할 일 없음
        2. 앞자리 0 문제. ex) 01234 int 저장 -> 1234 => 데이터 깨짐
    */

    @Enumerated(EnumType.STRING)
    @Column (length = 20)
    private Major major; // 정해진 선택지이므로 String 아니고 EnumType

    @OneToMany(mappedBy = "student",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();

    @Builder
    private Student(String name, String studentNumber, Major major) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.major = major;
    }

    public void update(StudentUpdateRequestDto studentUpdateRequestDto) {
        this.name = studentUpdateRequestDto.name();
        this.studentNumber = studentUpdateRequestDto.studentNumber();
    }
}
