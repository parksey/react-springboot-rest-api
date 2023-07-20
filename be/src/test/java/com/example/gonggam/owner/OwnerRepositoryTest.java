package com.example.gonggam.owner;

import com.example.gonggam.global.testconfig.RepositoryAnnotation;
import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.repository.OwnerRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("OwnerRepository 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class OwnerRepositoryTest extends RepositoryAnnotation {

    @Autowired
    private OwnerRepository ownerRepository;

    private static Owner owner;

    @BeforeAll
    static void init() {
        // Given
        String ownerNo = "1234567890";
        String email = "parksey@naver.com";
        String phone = "01012345678";

        owner = Owner.builder()
                .ownerNo(ownerNo)
                .email(email)
                .phone(phone)
                .createAt(LocalDateTime.now())
                .build();
    }

    @Test
    void 사업자_생성_성공_테스트() {
        // When
        Owner savedOwner = ownerRepository.save(owner);

        // Then
        assertThat(savedOwner.getOwnerId()).isNotNull();
        assertAll(
                () -> assertThat(savedOwner.getOwnerNo()).isEqualTo(owner.getOwnerNo()),
                () -> assertThat(savedOwner.getPhone()).isEqualTo(owner.getPhone()),
                () -> assertThat(savedOwner.getEmail()).isEqualTo(owner.getEmail())
        );
    }

    @Test
    void existsByOwnerNo_존재하면_예외발생_테스트() {
        // Given
        Owner savedOwnser = ownerRepository.save(owner);

        // When
        Owner newOwner = Owner.builder()
                .ownerNo(owner.getOwnerNo())
                .email(owner.getEmail())
                .createAt(owner.getCreateAt())
                .phone(owner.getPhone())
                .build();

        assertThatThrownBy(()->ownerRepository.save(newOwner))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
