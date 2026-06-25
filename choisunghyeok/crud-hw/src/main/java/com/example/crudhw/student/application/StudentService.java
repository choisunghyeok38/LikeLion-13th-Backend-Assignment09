package com.example.crudhw.student.application;

import com.example.crudhw.common.exception.BusinessException;
import com.example.crudhw.common.response.code.ErrorCode;
import com.example.crudhw.kosis.KosisApiService;
import com.example.crudhw.kosis.dto.KosisResponseDto;
import com.example.crudhw.student.api.dto.request.StudentUpdateRequestDto;
import com.example.crudhw.student.api.dto.response.RegionStudentStatsResponseDto;
import com.example.crudhw.student.domain.Student;
import com.example.crudhw.student.domain.repository.StudentRepository;
import com.example.crudhw.student.api.dto.request.StudentSaveRequestDto;
import com.example.crudhw.student.api.dto.response.StudentInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;
    private final KosisApiService kosisApiService;

    @Transactional
    public void studentSave(StudentSaveRequestDto studentSaveRequestDto) {
        Student student = Student.builder()
                .name(studentSaveRequestDto.name())
                .studentNumber(studentSaveRequestDto.studentNumber())
                .major(studentSaveRequestDto.major())
                .build();
        studentRepository.save(student);
    }

    public StudentInfoResponseDto studentFindOne(Long studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(()->new BusinessException(
                        ErrorCode.STUDENT_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage() + studentId
                ));
        return StudentInfoResponseDto.from(student);
    }

    public Page<StudentInfoResponseDto> studentFindAll(Pageable pageable) {
        Page<Student> students = studentRepository.findAll(pageable);
        return students.map(StudentInfoResponseDto::from);
    }

    @Transactional
    public void studentUpdate(Long studentId,
                              StudentUpdateRequestDto studentUpdateRequestDto) {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.STUDENT_NOT_FOUND_EXCEPTION,
                                ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage() + studentId
                        ));

        student.update(studentUpdateRequestDto);
    }

    @Transactional
    public void studentDelete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new BusinessException(
                                ErrorCode.STUDENT_NOT_FOUND_EXCEPTION,
                                ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage() + studentId
                        ));

        studentRepository.delete(student);
    }

    //추가
    //학번에서 지역코드 추출하기, 학번 5~6번째 자리를 지역코드로 미리 설정
    public RegionStudentStatsResponseDto getRegionStudentStats(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.STUDENT_NOT_FOUND_EXCEPTION,
                        ErrorCode.STUDENT_NOT_FOUND_EXCEPTION.getMessage()+id
                ));

        String studentNumber = student.getStudentNumber();
        // 학번 5~6번째 자리 추출(index 4~5)
        String regionCode = studentNumber.substring(4,6);


        System.out.println("학번 = " + studentNumber);
        System.out.println("추출한 지역코드 = " + regionCode);

        KosisResponseDto kosisDto = kosisApiService.getRegionStudentStats(regionCode);

        return RegionStudentStatsResponseDto.of(student, kosisDto);
    }
}
