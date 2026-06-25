package com.example.crudhw.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter // getter 메소드 자동 생성 lombok 어노테이션
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 파라미터로 받는 생성자 자동 생성 어노테이션
public enum ErrorCode {

    /**
     * 404 NOT FOUND (찾을 수 없음)
     */
    STUDENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 학생이 없습니다. studentId = "),
    ASSIGNMENT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 과제가 없습니다. assignmentId = "),
    KOSIS_REGION_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 지역 통계 정보가 없습니다. regionCode = "),

    /**
     * 400 BAD REQUEST
     */
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다 - "),

    /**
     * 500 INTERNAL SERVER ERROR (내부 서버 에러)
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;    // HTTP 상태 코드를 스프링에서 쉽게 작성하기 위한 enum값들의 모임
    private final String message;           // 에러 메세지


    public int getHttpStatusCode() {        // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}
