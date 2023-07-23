package com.example.gonggam.space.dto;

import com.example.gonggam.util.exception.ValidationStatus;
import com.example.gonggam.util.UtilsCode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        long ownerId = 1L;
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        long amount = 39000;
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

        // When
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .capacity(capacity)
                .location(location)
                .title(title)
                .amount(amount)
                .startAt(startAt)
                .endAt(endAt)
                .build();

        // Then
        assertAll(
                () -> assertThat(spaceCreateRequest.getTitle()).isEqualTo(title),
                () -> assertThat(spaceCreateRequest.getCapacity()).isEqualTo(capacity),
                () -> assertThat(spaceCreateRequest.getLocation()).isEqualTo(location),
                () -> assertThat(spaceCreateRequest.getAmount()).isEqualTo(amount),
                () -> assertThat(spaceCreateRequest.getStartAt()).isEqualTo(startAt),
                () -> assertThat(spaceCreateRequest.getEndAt()).isEqualTo(endAt)
        );
    }

    @Test
    void SpaceCreation_입력값이_없을때_예외_발생테스트() {
        // When
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .build();

        Set<ConstraintViolation<SpaceCreateRequest>> validates = validator.validate(spaceCreateRequest);

        // Then
        assertThat(validates).isNotEmpty();
        validates.forEach( validate -> {
                assertThat(validate.getMessageTemplate()).containsAnyOf(ValidationStatus.Global.NO_DATA, ValidationStatus.Global.NOT_BLANK, ValidationStatus.Space.UNDER_MIN_CAPACIRY);
            }
        );
    }

    @Test
    void SpaceCreation_타임_스탬프_형식_변형_테스트() {
        // Given
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime next = today.plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(UtilsCode.Space.DATE_FORMAT);

        // When
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .startAt(today)
                .endAt(next)
                .build();

        Set<ConstraintViolation<SpaceCreateRequest>> validates = validator.validate(spaceCreateRequest);

        // Then
        assertThat(validates).isNotNull();
        assertDoesNotThrow(()->spaceCreateRequest.getStartAt().format(formatter));
        assertDoesNotThrow(()->spaceCreateRequest.getEndAt().format(formatter));
    }
}
