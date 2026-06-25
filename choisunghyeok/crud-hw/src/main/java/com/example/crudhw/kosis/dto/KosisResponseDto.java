package com.example.crudhw.kosis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON에 있지만 DTO에는 없는 필드는 무시, 꼭 필요한 것만
public class KosisResponseDto {

    @JsonProperty("C1") // 분류값ID
    private String regionCode;

    @JsonProperty("C1_NM") // 분류값 명
    private String regionName;

    @JsonProperty("ITM_NM") // 항목명
    private String itemName;

    @JsonProperty("DT") // 학생 수
    private String studentCount;

    @JsonProperty("PRD_DE") // 수록시점
    private String year;

    @JsonProperty("UNIT_NM") // 단위명
    private String unit;
}
