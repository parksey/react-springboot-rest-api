package com.example.gonggam.owner;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.dto.OwnerRemoveRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.owner.service.OwnerMapper;
import com.example.gonggam.owner.service.OwnerService;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("OwnerService 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private SharedSpaceRepository sharedSpaceRepository;

    @Spy
    private OwnerMapper ownerMapper;

    private Owner owner;

    @BeforeEach
    void init() {
        // Given
        final String ownerNo = "1234567890";
        final String email = "psy@naver.com";
        final String phone = "01012345678";
        final LocalDateTime createAt = LocalDateTime.now();

        owner = Owner.builder()
                .ownerNo(ownerNo)
                .email(email)
                .phone(phone)
                .createAt(createAt)
                .build();
    }

    @Nested
    @DisplayName("사업자 생성 테스트")
    public class Create {
        @Test
        void 사업자_생성_테스트() {
            // Given
            Owner expectOwner = Owner.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(owner.getEmail())
                    .phone(owner.getPhone())
                    .createAt(owner.getCreateAt())
                    .build();

            OwnerUpdateRequest ownerRequest = OwnerUpdateRequest.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(owner.getEmail())
                    .phone(owner.getPhone())
                    .build();

            OwnerResponse expectResponse = OwnerResponse.builder()
                    .email(owner.getEmail())
                    .phone(owner.getPhone())
                    .ownerNo(owner.getOwnerNo())
                    .build();

            given(ownerRepository.existsByOwnerNo(owner.getOwnerNo())).willReturn(false);
            given(ownerMapper.toEntity(ownerRequest)).willReturn(owner);
            given(ownerRepository.save(owner)).willReturn(expectOwner);
            given(ownerMapper.toResponse(expectOwner)).willReturn(expectResponse);

            // When
            final OwnerResponse savedOwner = ownerService.createOperator(ownerRequest);

            // Then
            assertThat(savedOwner).isNotNull();
            assertAll(
                    () -> assertThat(savedOwner.getOwnerNo()).isEqualTo(expectOwner.getOwnerNo()),
                    () -> assertThat(savedOwner.getEmail()).isEqualTo(expectOwner.getEmail()),
                    () -> assertThat(savedOwner.getPhone()).isEqualTo(expectOwner.getPhone())
            );

            verify(ownerMapper).toEntity(ownerRequest);
            verify(ownerRepository).existsByOwnerNo(owner.getOwnerNo());
            verify(ownerRepository).save(owner);
            verify(ownerMapper).toResponse(expectOwner);
        }

        @Test
        void 사업자_번호_중복으로_생성_실패_테스트() {
            // Given
            OwnerUpdateRequest ownerRequest = OwnerUpdateRequest.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(owner.getEmail())
                    .phone(owner.getPhone())
                    .build();

            given(ownerRepository.existsByOwnerNo(ownerRequest.getOwnerNo())).willReturn(true);

            // When + Then
            assertThatThrownBy(()->ownerService.createOperator(ownerRequest))
                    .isInstanceOf(OwnerException.class)
                    .hasMessage(CustomValidationStatus.EXIST_OWNER.getMessage());
        }
    }

    @Nested
    @DisplayName("사업자 생성 테스트")
    public class Update {
        @Test
        void 사업자_정보_업데이트_성공_테스트() {
            // Given
            String newEmail = "new@naver.com";
            String newPhone = "01099999999";

            OwnerUpdateRequest ownerRequest = OwnerUpdateRequest.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(newEmail)
                    .phone(newPhone)
                    .build();

            Owner updateOwner = Owner.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(newEmail)
                    .phone(newPhone)
                    .build();

            OwnerResponse expectResponse = OwnerResponse.builder()
                    .ownerNo(owner.getOwnerNo())
                    .email(newEmail)
                    .phone(newPhone)
                    .build();

            given(ownerRepository.findByOwnerNo(ownerRequest.getOwnerNo())).willReturn(Optional.of(owner));

            // When
            owner.update(updateOwner);
            ownerService.updateOperator(ownerRequest);

            // When + then
            verify(ownerRepository).findByOwnerNo(ownerRequest.getOwnerNo());

            assertAll(
                    () ->  assertThat(owner.getEmail()).isEqualTo(newEmail),
                    () -> assertThat(owner.getPhone()).isEqualTo(newPhone)
            );
        }
    }

    @Nested
    @DisplayName("사업자 삭제 테스트")
    public class Remove {
        @Test
        void 사업자_삭제_성공_테스트() {
            // Given
            OwnerRemoveRequest ownerRemoveRequest = new OwnerRemoveRequest(owner.getOwnerNo());

            given(ownerRepository.findByOwnerNo(owner.getOwnerNo())).willReturn(Optional.of(owner));
            given(sharedSpaceRepository.findAllByOwnerNo(ownerRemoveRequest.getOwnerNo())).willReturn(List.of());

            // When
            ownerService.deleteOwner(ownerRemoveRequest);

            // Then
            verify(ownerRepository).delete(owner);
        }

        @Test
        void 해당_사업자가_존재하지_않을때_예외_테스트() {
            // Given
            OwnerRemoveRequest ownerRemoveRequest = new OwnerRemoveRequest(owner.getOwnerNo());

            // When + Then
            assertThatThrownBy(()->ownerService.deleteOwner(ownerRemoveRequest))
                    .isInstanceOf(OwnerException.class)
                    .hasMessage(CustomValidationStatus.NO_OWNER.getMessage());
        }

        @Test
        void 사업자의_공유공간이_남아있을때_예외_테스트() {
            // Given
            OwnerRemoveRequest ownerRemoveRequest = new OwnerRemoveRequest(owner.getOwnerNo());
            SharedSpace sharedSpace = SharedSpace.builder()
                    .location("서울특별시")
                    .amount(29000)
                    .title("title")
                    .capacity(7)
                    .startAt(LocalDateTime.now())
                    .endAt(LocalDateTime.now().plusDays(7))
                    .build();


            given(ownerRepository.findByOwnerNo(owner.getOwnerNo())).willReturn(Optional.of(owner));
            given(sharedSpaceRepository.findAllByOwnerNo(ownerRemoveRequest.getOwnerNo())).willReturn(List.of(sharedSpace));


            // When + Then
            assertThatThrownBy(()->ownerService.deleteOwner(ownerRemoveRequest))
                    .isInstanceOf(OwnerException.class)
                    .hasMessage(CustomValidationStatus.NO_SHAED_SPACE.getMessage());
        }
    }
}
