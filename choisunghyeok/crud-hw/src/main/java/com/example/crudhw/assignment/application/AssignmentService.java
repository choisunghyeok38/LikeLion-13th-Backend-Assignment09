package com.example.crudhw.assignment.application;

import com.example.crudhw.assignment.api.dto.request.AssignmentSaveRequestDto;
import com.example.crudhw.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.example.crudhw.assignment.api.dto.response.AssignmentInfoResponseDto;
import com.example.crudhw.assignment.api.dto.response.AssignmentListResponseDto;
import com.example.crudhw.assignment.domain.Assignment;
import com.example.crudhw.assignment.domain.repository.AssignmentRepository;
import com.example.crudhw.common.exception.BusinessException;
import com.example.crudhw.common.response.code.ErrorCode;
import com.example.crudhw.student.domain.Student;
import com.example.crudhw.student.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssignmentService {
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;

    @Transactional
    public void assignmentSave(AssignmentSaveRequestDto assignmentSaveRequestDto) {
        Student student = studentRepository.findById(assignmentSaveRequestDto.studentId())
                .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND_EXCEPTION, ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()));

        Assignment assignment = Assignment.builder()
                .title(assignmentSaveRequestDto.title())
                .contents(assignmentSaveRequestDto.contents())
                .student (student)
                .build();

        assignmentRepository.save(assignment);
    }

    public List<AssignmentInfoResponseDto> assignmentFindStudent(Long studentId) {
            Student student = studentRepository.findById(studentId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.STUDENT_NOT_FOUND_EXCEPTION, ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()));

            List<Assignment> assignments = assignmentRepository.findByStudent(student);
            return assignments.stream().map(AssignmentInfoResponseDto::from).toList();
        }

    @Transactional
    public void assignmentUpdate(Long assignmentId, AssignmentUpdateRequestDto assignmentUpdateRequestDto) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.ASSIGNMENT_NOT_FOUND_EXCEPTION,
                        ErrorCode.ASSIGNMENT_NOT_FOUND_EXCEPTION.getMessage()
                ));

        assignment.update(assignmentUpdateRequestDto);
    }

    @Transactional
    public void assignmentDelete(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.ASSIGNMENT_NOT_FOUND_EXCEPTION,
                        ErrorCode.ASSIGNMENT_NOT_FOUND_EXCEPTION.getMessage()
                ));
        assignmentRepository.delete(assignment);
    }
}
