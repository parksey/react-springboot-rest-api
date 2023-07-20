package com.example.gonggam.space;


import com.example.gonggam.global.testconfig.RepositoryAnnotation;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("SharedSpaceRepository 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class SharedSpaceRepositoryTest extends RepositoryAnnotation {

    @Autowired
    private SharedSpaceRepository sharedSpaceRepository;

    private static SharedSpace sharedSpace;

    @BeforeAll
    static void init() {
        // Given
        sharedSpace = SharedSpace.builder()
                .title("title")
                .amount(29000)
                .location("서울특별시")
                .capacity(7)
                .startAt(LocalDateTime.now())
                .endAt(LocalDateTime.now().plusDays(7))
                .ownerId(1L)
                .build();
    }

    @Test
    void 공유공간_생성_성공_테스트() {
         // When
        SharedSpace savedSharedSpace = sharedSpaceRepository.save(sharedSpace);

        // Then
        assertThat(savedSharedSpace.getSpaceId()).isNotNull();
        assertAll(
                () -> assertThat(savedSharedSpace.getTitle()).isEqualTo(sharedSpace.getTitle()),
                () -> assertThat(savedSharedSpace.getStartAt()).isEqualTo(sharedSpace.getStartAt()),
                () -> assertThat(savedSharedSpace.getEndAt()).isEqualTo(sharedSpace.getEndAt())
        );
    }
}
