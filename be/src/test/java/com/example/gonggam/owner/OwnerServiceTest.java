package com.example.gonggam.owner;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.owner.service.OwnerService;
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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

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
        String ownerNo = "1234567890";
        String email = "psy@naver.com";
        String phone = "01012345678";
        LocalDateTime createAt = LocalDateTime.now();

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

        OwnerRequestDto ownerRequest = OwnerRequestDto.builder()
                .ownerNo(ownerNo)
                .email(email)
                .phone(phone)
                .createAt(createAt)
                .build();

        given(ownerRequest.toEntity(ownerRequest)).willReturn(owner);
        given(ownerRepository.existsByOwnerNo(ownerNo)).willReturn(false);
        given(ownerRepository.save(owner)).willReturn(expectOwner);

        // When
        final Owner savedOwner = ownerService.createOperator(owner);

        // Then
        assertThat(savedOwner).isNotNull();
        assertAll(
                () -> assertThat(savedOwner.getOwnerNo()).isEqualTo(expectOwner.getOwnerNo()),
                () -> assertThat(savedOwner.getEmail()).isEqualTo(expectOwner.getEmail()),
                () -> assertThat(savedOwner.getPhone()).isEqualTo(expectOwner.getPhone()),
                () -> assertThat(savedOwner.getCreateAt()).isEqualTo(expectOwner.getCreateAt())
        );
    }
}
