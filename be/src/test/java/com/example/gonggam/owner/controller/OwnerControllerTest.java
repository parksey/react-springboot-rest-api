package com.example.gonggam.owner.controller;

import com.example.gonggam.GonggamApplication;
import com.example.gonggam.owner.dto.OwnerRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Owner 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest(classes = GonggamApplication.class)
@AutoConfigureMockMvc
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String API_VERSION = "/api/v1";

    @Test
    void 사업자_생성_테스트() throws Exception {
        // Given
        String phone = "01012345678";
        String ownerNo = "1234567890";
        String email = "psy@naver.com";

        OwnerRequest ownerRequest = OwnerRequest.builder()
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

    private String getUrl(String url) {
        return API_VERSION + url;
    }

}