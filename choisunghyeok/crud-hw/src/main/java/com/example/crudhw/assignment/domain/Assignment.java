package com.example.crudhw.assignment.domain;

import com.example.crudhw.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.example.crudhw.student.domain.Student;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    private String title;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @Builder
    private Assignment(String title, String contents, Student student) {
        this.title = title;
        this.contents = contents;
        this.student = student;
    }

    public void update(AssignmentUpdateRequestDto assignmentUpdateRequestDto) {
        this.title = assignmentUpdateRequestDto.title();
        this.contents = assignmentUpdateRequestDto.contents();
    }
}
