package com.example.gonggam.customer.service.controller;

import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.dto.LoginRequest;
import com.example.gonggam.global.testconfig.ControllerAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Customer 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class CustomerControllerTest extends ControllerAnnotation {

    @Test
    void 회원_생성_테스트() throws Exception {
        // Given
        String name = "psy";
        String phone = "01012345678";
        String email = "psytest@naver.com";
        String password = "qwer1234";

        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(
                post(getUrl("/user/register"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateRequest)));

        // Then
        resultActions.andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void 로그인_테스트() throws Exception {
        // Given
        String name = "psy";
        String phone = "01012345678";
        String email = "psy@naver.com";
        String password = "qwer1234";

        LoginRequest loginRequest = new LoginRequest(email, password);

        // When
        ResultActions resultActions = mockMvc.perform(
                post(getUrl("/user/login"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)));

        // Then
        resultActions.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(cookie().exists("JSESSIONID"));
    }
}
