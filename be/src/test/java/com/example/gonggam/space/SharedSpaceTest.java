package com.example.gonggam.space;

import com.example.gonggam.space.domain.SharedSpace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Space Entity 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SharedSpaceTest {

    @Test
    void 공유공간_생성_테스트() {
        // Given
        String title = "title";
        String description = "description";
        String location = "서울특별시 강남구 테헤란로 420";
        int capacity = 8;
        LocalDateTime startAt = LocalDateTime.now().plusDays(7);
        LocalDateTime endAt = LocalDateTime.now().plusDays(28);

        // When
        SharedSpace space = new SharedSpace(title, description, location, capacity, startAt, endAt);

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

        // When + Then
        assertThatThrownBy(()-> new SharedSpace(title, description, location, capacity, startAt, endAt))
                .isInstanceOf(SpaceException.class)
                .hasMessage("시작일자와 종료일자가 잘못되었습니다.");
    }

    @Test
    void 공유공간_총원_0이하이면_에러발생_테스트() {
        // Given
        String title = "title";
        String description = "description";
        String location = "서울특별시 강남구 테헤란로 420";
        int capacity = -1;
        LocalDateTime startAt = LocalDateTime.now().plusDays(28);
        LocalDateTime endAt = LocalDateTime.now().plusDays(7);

        // When + Then
        assertThatThrownBy(()-> new SharedSpace(title, description, location, capacity, startAt, endAt))
                .isInstanceOf(SpaceException.class)
                .hasMessage("총원이 잘못되었습니다.");
    }
}
