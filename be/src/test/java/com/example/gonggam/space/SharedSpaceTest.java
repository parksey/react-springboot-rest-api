package com.example.gonggam.space;

import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.exception.SharedSpaceException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Space Entity 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SharedSpaceTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 공유공간_생성_테스트() {
        // Given
        String title = "title";
        String description = "description";
        String location = "서울특별시 강남구 테헤란로 420";
        int capacity = 8;
        long amount = 29000L;
        LocalDateTime startAt = LocalDateTime.now().plusDays(7);
        LocalDateTime endAt = LocalDateTime.now().plusDays(28);
        Long ownerId = 1L;

        // When
        SharedSpace space = new SharedSpace(title, description, location, capacity, amount, startAt, endAt, ownerId);

        // Then
        assertThat(space).isNotNull();
    }

    @Test
    void 공유공간_시작시간이_끝나는시간보다_느릴때_에러발생_테스트() {
        // Given
        String title = "title";
        String description = "description";
        String location = "서울특별시 강남구 테헤란로 420";
        int capacity = 8;
        LocalDateTime startAt = LocalDateTime.now().plusDays(28);
        LocalDateTime endAt = LocalDateTime.now().plusDays(7);
        Long ownerId = 1l;

        // When + Then
        assertThatThrownBy(()-> SharedSpace.builder()
                .title(title)
                .description(description)
                .location(location)
                .capacity(capacity)
                .startAt(startAt)
                .endAt(endAt)
                .ownerId(ownerId)
                .build())
                .isInstanceOf(SharedSpaceException.class)
                .hasMessage("시작일자와 종료일자가 잘못되었습니다.");
    }

    @Test
    void 공유공간_총원_0이하이면_에러발생_테스트() {
        // Given
        String title = "title";
        String description = "description";
        String location = "서울특별시 강남구 테헤란로 420";
        int capacity = -1;
        LocalDateTime startAt = LocalDateTime.now().plusDays(7);
        LocalDateTime endAt = LocalDateTime.now().plusDays(28);
        Long ownerId = 1l;

        SharedSpace sharedSpace = SharedSpace.builder()
                .title(title)
                .description(description)
                .location(location)
                .capacity(capacity)
                .startAt(startAt)
                .endAt(endAt)
                .ownerId(ownerId)
                .build();

        // When
        Set<ConstraintViolation<SharedSpace>> spaceViolations = validator.validate(sharedSpace);

        // Then
        assertThat(spaceViolations).isNotEmpty();
    }
}
