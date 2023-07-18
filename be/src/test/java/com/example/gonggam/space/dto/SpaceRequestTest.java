package com.example.gonggam.space.dto;

import com.example.gonggam.util.ErrorMessage;
import com.example.gonggam.util.UtilsCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Space 요청 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SpaceRequestTest {

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
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

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
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .build();

        Set<ConstraintViolation<SpaceCreateRequest>> validates = validator.validate(spaceCreateRequest);


        assertThat(validates).isNotEmpty();
        validates.forEach( validate -> {
                if (validate.getPropertyPath().toString().equals("capacity")) {
                    assertThat(validate.getMessageTemplate()).isEqualTo(String.format(ErrorMessage.Space.UNDER_MIN_CAPACIRY, UtilsCode.Space.MIN_SPACE_CAPACITY));
                    return;
                }
                assertThat(validate.getMessageTemplate()).containsAnyOf(ErrorMessage.Space.NO_DATA, ErrorMessage.Space.NOT_BLANK);
            }
        );
    }
}
