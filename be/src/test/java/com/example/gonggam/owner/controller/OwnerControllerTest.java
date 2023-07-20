package com.example.gonggam.owner.controller;

import com.example.gonggam.global.testconfig.ControllerAnnotation;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Owner 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class OwnerControllerTest extends ControllerAnnotation {

    @Test
    void 사업자_생성_테스트() throws Exception {
        // Given
        String phone = "01012345678";
        String ownerNo = "1234567890";
        String email = "psy@naver.com";

        OwnerUpdateRequest ownerRequest = OwnerUpdateRequest.builder()
                .phone(phone)
                .ownerNo(ownerNo)
                .email(email)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(
                post(getUrl("/owners"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ownerRequest)));

        // Then
        resultActions.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("phone").value(phone))
                .andExpect(jsonPath("ownerNo").value(ownerNo));
    }

    @Test
    void 사업자_개인정보_조회() throws Exception {
        // Given
        String ownerNo = "1234567890";

        // When
        ResultActions resultActions = mockMvc.perform(
                get(getUrl(String.format("/owners{}",ownerNo))));

        // Then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("ownerNo").value(ownerNo));
    }
}