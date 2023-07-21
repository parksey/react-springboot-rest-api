package com.example.gonggam.space.controller;

import com.example.gonggam.global.testconfig.ControllerAnnotation;
import com.example.gonggam.space.dto.SpaceCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("SharedSpace 통합 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class SharedSpaceControllerTest extends ControllerAnnotation {

    @Test
    void 공유공간_생성_테스트() throws Exception {
        // Given
        long ownerId = 1L;
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        long amont = 29000L;
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .ownerId(ownerId)
                .title(title)
                .location(location)
                .capacity(capacity)
                .amount(amont)
                .startAt(startAt)
                .endAt(endAt)
                .build();

        // When
        ResultActions spaceResult = mockMvc.perform(
                post(getUrl("/spaces"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spaceCreateRequest)));

        // Then
        spaceResult.andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("title").value(title))
                .andExpect(jsonPath("location").value(location))
                .andExpect(jsonPath("capacity").value(capacity))
                .andExpect(jsonPath("amount").value(amont));
    }
}