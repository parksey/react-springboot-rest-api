package com.example.gonggam.owner.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("OwnerRequest, 사업자 요청 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OwnerRequestTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 사업자_생성_요청_성공_테스트() {
        // Given
        String ownerNo = "1234567890";
        String phone = "01012345678";
        String email = "psy@naver.com";

        OwnerRequest ownerRequest =OwnerRequest.builder()
                .ownerNo(ownerNo)
                .phone(phone)
                .email(email)
                .build();

        // When
        Set<ConstraintViolation<OwnerRequest>> validates = validator.validate(ownerRequest);

        // Then
        assertThat(validates).isNotNull();
        assertAll(
                () -> assertThat(ownerRequest.getOwnerNo()).isEqualTo(ownerNo),
                () -> assertThat(ownerRequest.getPhone()).isEqualTo(phone),
                () -> assertThat(ownerRequest.getEmail()).isEqualTo(email)
        );
    }

    @Test
    void 사업자_생성_요청_실패_테스트() {
        // Given
        OwnerRequest ownerRequest =OwnerRequest.builder().build();

        // When
        Set<ConstraintViolation<OwnerRequest>> validates = validator.validate(ownerRequest);

        // Then
        assertThat(validates).isNotNull();
        assertThat(validates).hasSize(3);
    }
}
