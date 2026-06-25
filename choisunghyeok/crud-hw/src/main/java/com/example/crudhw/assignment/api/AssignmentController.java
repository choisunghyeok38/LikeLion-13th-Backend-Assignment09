package com.example.crudhw.assignment.api;

import com.example.crudhw.assignment.api.dto.request.AssignmentSaveRequestDto;
import com.example.crudhw.assignment.api.dto.request.AssignmentUpdateRequestDto;
import com.example.crudhw.assignment.api.dto.response.AssignmentInfoResponseDto;
import com.example.crudhw.assignment.api.dto.response.AssignmentListResponseDto;
import com.example.crudhw.assignment.application.AssignmentService;
import com.example.crudhw.common.response.code.SuccessCode;
import com.example.crudhw.common.template.ApiResTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assignment")
@Tag(name = "ASSIGNMENT API", description = "과제 관리 api")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping
    @Operation(summary = "과제 저장", description = "과제 저장")
    public ApiResTemplate<Void> assignmentSave(@RequestBody @Valid AssignmentSaveRequestDto assignmentSaveRequestDto) {
        assignmentService.assignmentSave(assignmentSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_SAVE_SUCCESS);
    }

    @GetMapping("/{studentId}")
    @Operation(summary = "과제 studentId로 조회", description = "과제 studentId로 조회")
    public ApiResTemplate<List<AssignmentInfoResponseDto>> myAssignmentFindAll(@PathVariable("studentId") Long studentId) {
        List<AssignmentInfoResponseDto> assignments = assignmentService.assignmentFindStudent(studentId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, assignments);
    }

    @PatchMapping("/{assignmentId}")
    @Operation(summary = "과제 Id로 수정", description = "과제 제목, 내용 수정")
    public ApiResTemplate<Void> assignment(@PathVariable("assignmentId") Long assignmentId, @RequestBody @Valid AssignmentUpdateRequestDto assignmentUpdateRequestDto) {
        assignmentService.assignmentUpdate(assignmentId, assignmentUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_UPDATE_SUCCESS);
    }

    @DeleteMapping("{assignmentId}")
    @Operation(summary = "과제 삭제", description = "과제 삭제")
    public ApiResTemplate<Void> assignmentDelete(@PathVariable("assignmentId") Long assignmentId) {
        assignmentService.assignmentDelete(assignmentId);
        return ApiResTemplate.successWithNoContent(SuccessCode.ASSIGNMENT_DELETE_SUCCESS);
    }
}
