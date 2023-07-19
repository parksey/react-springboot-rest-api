package com.example.gonggam.owner;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.dto.OwnerRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.owner.service.OwnerMapper;
import com.example.gonggam.owner.service.OwnerService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("OwnerService 테스트")
@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

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

    @Test
    void 사업자_생성_테스트() {
        // Given
        Owner expectOwner = Owner.builder()
                .ownerNo(owner.getOwnerNo())
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .createAt(owner.getCreateAt())
                .build();

        OwnerRequest ownerRequest = OwnerRequest.builder()
                .ownerNo(owner.getOwnerNo())
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .build();

        OwnerResponse ownerResponse = OwnerResponse.builder()
                .email(owner.getEmail())
                .phone(owner.getPhone())
                .ownerNo(owner.getOwnerNo())
                .build();

        given(ownerRepository.existsByOwnerNo(owner.getOwnerNo())).willReturn(false);
        given(ownerMapper.toEntity(ownerRequest)).willReturn(owner);
        given(ownerRepository.save(owner)).willReturn(expectOwner);
        given(ownerMapper.toResponse(expectOwner)).willReturn(ownerResponse);

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
    void 사업자_번호_중복으로_실패_테스트() {
        // Given
        OwnerRequest ownerRequest = OwnerRequest.builder()
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
