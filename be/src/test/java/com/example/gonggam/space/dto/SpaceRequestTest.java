package com.example.gonggam.space.dto;

import com.example.gonggam.util.ErrorMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Space 요청 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SpaceRequestTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void SpaceCreation_요청_생성_성공_테스트() {
        // Given
        String email = "abc@naver.com";
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        LocalDate startAt = LocalDate.now();
        LocalDate endAt = LocalDate.now().plusDays(2);

        // When
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .capacity(capacity)
                .email(email)
                .location(location)
                .title(title)
                .startAt(startAt)
                .endAt(endAt)
                .build();

        // Then
        assertAll(
                () -> assertThat(spaceCreateRequest.getEmail()).isEqualTo(email),
                () -> assertThat(spaceCreateRequest.getTitle()).isEqualTo(title),
                () -> assertThat(spaceCreateRequest.getCapacity()).isEqualTo(capacity),
                () -> assertThat(spaceCreateRequest.getLocation()).isEqualTo(location),
                () -> assertThat(spaceCreateRequest.getStartAt()).isEqualTo(startAt),
                () -> assertThat(spaceCreateRequest.getEndAt()).isEqualTo(endAt)
        );
    }

    @Test
    void SpaceCreation_입력값이_없을때_예외_발생테스트() {
        // Given
        String email = "abc@naver.com";
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        LocalDate startAt = LocalDate.now();
        LocalDate endAt = LocalDate.now().plusDays(2);

        // When
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder().build();

        Set<ConstraintViolation<SpaceCreateRequest>> validates = validator.validate(spaceCreateRequest);


        assertThat(validates).isNotEmpty();
        validates.forEach(
                validate -> assertThat(validate.getMessageTemplate()).isEqualTo(ErrorMessage.Space.NOT_BLANK)
        );
    }
}
