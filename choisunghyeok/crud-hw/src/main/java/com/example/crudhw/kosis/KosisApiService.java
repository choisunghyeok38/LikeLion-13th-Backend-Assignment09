package com.example.crudhw.kosis;

import com.example.crudhw.common.exception.BusinessException;
import com.example.crudhw.common.response.code.ErrorCode;
import com.example.crudhw.kosis.dto.KosisResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class KosisApiService {

    private final RestClient kosisRestClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${kosis.api.key}")
    private String apikey;

    public KosisApiService(RestClient kosisRestClient) {
        this.kosisRestClient = kosisRestClient;
    }

    public KosisResponseDto getRegionStudentStats(String regionCode) {

        try {
            String json = kosisRestClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("method", "getList")
                            .queryParam("apiKey", apikey)
                            .queryParam("itmId", "T10")
                            .queryParam("objL1", "00 11 21 22 23 24 25 26 29 31 32 33 34 35 36 37 38 39")
                            .queryParam("format", "json")
                            .queryParam("jsonVD", "Y")
                            .queryParam("prdSe", "Y")
                            .queryParam("newEstPrdCnt", "3")
                            .queryParam("outputFields", "OBJ_ID NM ITM_ID ITM_NM UNIT_NM PRD_DE")
                            .queryParam("orgId", "101")
                            .queryParam("tblId", "DT_1YL8801")
                            .build())
                    .retrieve()
                    .body(String.class);

            List<KosisResponseDto> response =
                    objectMapper.readValue(
                            json,
                            new TypeReference<List<KosisResponseDto>>() {}
                    );

            System.out.println("response = " + response);

            response.forEach(dto ->
                    System.out.println(dto.getRegionCode() + " / " + dto.getRegionName()));

            return response.stream()
                    .filter(dto -> dto.getRegionCode().equals(regionCode))
                    .findFirst()
                    .orElseThrow(() -> new BusinessException(
                            ErrorCode.KOSIS_REGION_NOT_FOUND_EXCEPTION,
                            ErrorCode.KOSIS_REGION_NOT_FOUND_EXCEPTION.getMessage() + regionCode
                    ));

        } catch (Exception e) {
            throw new RuntimeException("KOSIS API 호출 실패", e);
        }
    }
}