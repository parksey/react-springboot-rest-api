package com.example.gonggam.space;

import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceInfoResponse;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import com.example.gonggam.space.service.SharedSpaceService;
import com.example.gonggam.space.service.SpaceMapper;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("SpaceService 테스트")
@ExtendWith(MockitoExtension.class)
public class SpaceServiceTest {

    @InjectMocks
    private SharedSpaceService spaceService;

    @Mock
    private SharedSpaceRepository spaceRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @Spy
    private SpaceMapper spaceMapper;

    private SharedSpace sharedSpace;

    @BeforeEach
    void init() {
        // Given
        long ownerId = 1L;
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        long amont = 29000L;
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

        sharedSpace = SharedSpace.builder()
                .ownerId(ownerId)
                .title(title)
                .location(location)
                .capacity(capacity)
                .amount(amont)
                .startAt(startAt)
                .endAt(endAt)
                .build();
    }


    @Test
    void 장소_생성_성공_테스트() {
        // Given
        String ownerNo = "123456798";
        long ownerId = sharedSpace.getOwnerId();
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .title(sharedSpace.getTitle())
                .location(sharedSpace.getLocation())
                .capacity(sharedSpace.getCapacity())
                .startAt(sharedSpace.getStartAt())
                .endAt(sharedSpace.getEndAt())
                .amount(sharedSpace.getAmount())
                .build();

        SharedSpace savedSharedSpace = SharedSpace.builder()
                .title(sharedSpace.getTitle())
                .location(sharedSpace.getLocation())
                .capacity(sharedSpace.getCapacity())
                .startAt(sharedSpace.getStartAt())
                .endAt(sharedSpace.getEndAt())
                .amount(sharedSpace.getAmount())
                .build();

        SpaceInfoResponse expectResponse = SpaceInfoResponse.builder()
                .title(sharedSpace.getTitle())
                .description(sharedSpace.getDescription())
                .location(sharedSpace.getLocation())
                .capacity(sharedSpace.getCapacity())
                .amount(sharedSpace.getAmount())
                .startAt(sharedSpace.getStartAt())
                .endAt(sharedSpace.getEndAt())
                .build();

        given(ownerRepository.findByOwnerNo(ownerNo)).willReturn(any());
        given(spaceMapper.toEntity(spaceCreateRequest)).willReturn(sharedSpace);
        given(spaceRepository.save(sharedSpace)).willReturn(savedSharedSpace);

        // When
        SpaceInfoResponse createResponse =  spaceService.createSpace(spaceCreateRequest, ownerNo);

        // Then
        assertThat(expectResponse).isNotNull();
        assertAll(
                () -> assertThat(expectResponse.getTitle()).isEqualTo(createResponse.getTitle()),
                () -> assertThat(expectResponse.getAmount()).isEqualTo(createResponse.getAmount()),
                () -> assertThat(expectResponse.getCapacity()).isEqualTo(createResponse.getCapacity()),
                () -> assertThat(expectResponse.getLocation()).isEqualTo(createResponse.getLocation()),
                () -> assertThat(expectResponse.getStartAt()).isEqualTo(createResponse.getStartAt()),
                () -> assertThat(expectResponse.getEndAt()).isEqualTo(createResponse.getEndAt())
        );

        verify(ownerRepository).existsById(ownerId);
        verify(spaceMapper).toEntity(spaceCreateRequest);
        verify(spaceRepository).save(sharedSpace);;
    }

    @Test
    void 장소_생성_동일한_이메일로_등록하여_실패_테스트() {
        // Given
        String ownerNo = "1234567890";
        long ownerId = sharedSpace.getOwnerId();
        SpaceCreateRequest spaceCreateRequest = SpaceCreateRequest.builder()
                .title(sharedSpace.getTitle())
                .location(sharedSpace.getLocation())
                .capacity(sharedSpace.getCapacity())
                .startAt(sharedSpace.getStartAt())
                .endAt(sharedSpace.getEndAt())
                .amount(sharedSpace.getAmount())
                .build();

        given(ownerRepository.existsById(ownerId)).willReturn(false);

        // When + Then
        assertThatThrownBy(()->spaceService.createSpace(spaceCreateRequest, ownerNo))
                .isInstanceOf(OwnerException.class)
                .hasMessage(CustomValidationStatus.NO_OWNER.getMessage());
    }
}

