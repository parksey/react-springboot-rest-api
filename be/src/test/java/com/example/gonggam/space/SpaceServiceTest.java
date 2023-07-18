package com.example.gonggam.space;

import com.example.gonggam.space.domain.SharedSpace;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("SpaceService 테스트")
@ExtendWith(MockitoExtension.class)
public class SpaceServiceTest {

    @InjectMocks
    private SpaceService spaceService;

    @Mock
    private SpaceRepository spaceRepository;

    @Spy
    private SpaceMapper spaceMapper;

    @Test
    void 장소_생성_성공_테스트() {
        // Given
        Email email = new Email("abc@naver.com");
        String title = "타이틀";
        String location = "위치";
        int capacity = 8;
        LocalDate startAt = LocalDate.now();
        LocalDate endAt = LocalDate.now().plusDays(2);

        SpaceCreateReqeust spaceCreateReqeust = new SpaceCreateRequest();
        SharedSpace sharedSpace = spaceMapper.toEntity(spaceCreateReqeust);
        SpaceCreateResponse expectResponse = spaceMapper.toResponse(sharedSpace);

        given(spaceRepository.existsByEmail(email)).willReturn(false);
        given(spaceRepository.save(sharedSpace)).willReturn(saveSpace);

        // When
        final SpaceCreateResponse spaceCreateResponse =  spaceService.createSpace(spaceCreateReqeust);

        // Then
        assertThat(expectResponse).isEqualTo(spaceCreateResponse);

        verify(spaceMapper).toEntity(spaceCreateReqeust);
        verify(spaceRepository).existsByEmail(email);
        verify(spaceRepository).save(sharedSpace);
        verify(spaceMapper).toResponse(savedSpace);
    }
}

