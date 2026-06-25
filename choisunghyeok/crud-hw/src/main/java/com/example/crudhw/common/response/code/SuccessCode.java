package com.example.crudhw.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성 어노테이션
public enum SuccessCode {

    /**
     * 200 OK (성공)
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),
    STUDENT_UPDATE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 수정되었습니다."),
    ASSIGNMENT_UPDATE_SUCCESS(HttpStatus.OK, "과제가 성공적으로 수정되었습니다."),
    STUDENT_DELETE_SUCCESS(HttpStatus.OK, "학생이 성공적으로 삭제되었습니다."),
    ASSIGNMENT_DELETE_SUCCESS(HttpStatus.OK, "과제가 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED (생성 성공)
     */
    STUDENT_SAVE_SUCCESS(HttpStatus.CREATED, "학생이 성공적으로 생성되었습니다."),
    ASSIGNMENT_SAVE_SUCCESS(HttpStatus.CREATED, "과제가 성공적으로 생성되었습니다.");

    private final HttpStatus httpStatus;   // HTTP 상태 코드를 스프링에서 쉽게 작성하기 위한 enum값들의 모임
    private final String message;          // 응답 메세지

    public int getHttpStatusCode() {       // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}

