package com.example.crudhw.student.api;

import com.example.crudhw.common.response.code.SuccessCode;
import com.example.crudhw.common.template.ApiResTemplate;
import com.example.crudhw.student.api.dto.request.StudentSaveRequestDto;
import com.example.crudhw.student.api.dto.request.StudentUpdateRequestDto;
import com.example.crudhw.student.api.dto.response.RegionStudentStatsResponseDto;
import com.example.crudhw.student.api.dto.response.StudentInfoResponseDto;
import com.example.crudhw.student.application.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Tag(name = "학생 API", description = "학생 관리 api")
public class StudentController {

    private final StudentService studentService;

    // 학생 저장
    @PostMapping("")
    @Operation(summary = "학생 회원가입", description = "학생 회원가입 설명란입니다")
    public ApiResTemplate<Void> studentSave(@RequestBody @Valid StudentSaveRequestDto studentSaveRequestDto) {
        studentService.studentSave(studentSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_SAVE_SUCCESS);
    }

    // 학생 전체 조회
    @GetMapping("/all")
    @Operation(summary = "학생 전체 조회", description = "학생 전체조회")
    public ApiResTemplate<Page<StudentInfoResponseDto>> studentFindAll(
            @ParameterObject
            @PageableDefault(
                    size = 10,
                    sort = "id",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        Page<StudentInfoResponseDto> students = studentService.studentFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS,students);
    }

    // 학생 id를 통해 학생 조회
    @GetMapping("/{studentId}")
    @Operation(summary = "학생 1명 조회", description = "학생 id로 학생조회")
    public ApiResTemplate<StudentInfoResponseDto> studentFindOne(@PathVariable("studentId")Long studentId) {
        StudentInfoResponseDto studentInfoResponseDto = studentService.studentFindOne(studentId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, studentInfoResponseDto);
    }

    // 학생 id를 통한 학생 수정
    @PatchMapping("/{studentId}")
    @Operation(summary = "학생 업데이트", description = "학생 업데이트")
    public ApiResTemplate<Void> studentUpdate(
            @PathVariable("studentId") Long studentId,
            @RequestBody StudentUpdateRequestDto studentUpdateRequestDto) {
        studentService.studentUpdate(studentId, studentUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_UPDATE_SUCCESS);
    }

    // 학생 id를 통한 학생 삭제
    @DeleteMapping("/{studentId}")
    @Operation(summary = "학생 삭제", description = "학생 삭제")
    public ApiResTemplate<Void> studentDelete(
            @PathVariable("studentId") Long studentId) {
        studentService.studentDelete(studentId);
        return ApiResTemplate.successWithNoContent(SuccessCode.STUDENT_DELETE_SUCCESS);
    }

    //추가
    @GetMapping("/{id}/region-stats")
    public ResponseEntity<RegionStudentStatsResponseDto> getRegionStudentStats(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getRegionStudentStats(id));
    }
}
