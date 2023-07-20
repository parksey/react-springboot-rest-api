package com.example.gonggam.customer.service.controller;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.global.testconfig.ControllerAnnotation;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Owner 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class CustomerControllerTest extends ControllerAnnotation {

    @Test
    void 회원_생성_테스트() throws Exception {
        // Given
        String name = "psy";
        String phone = "01012345678";
        String email = "psy@naver.com";
        String password = "1234";

        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                .email(email)
                .password(password)
                .name(name)
                .phone(phone)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(
                post(getUrl("/users"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateRequest)));

        // Then
        resultActions.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("phone").value(phone))
                .andExpect(jsonPath("name").value(name));
    }
}
