package com.example.gonggam.owner;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.util.ErrorMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Owner 테스트 : 업주")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OwnerTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 사업자_생성_성공_테스트() {
        // Given
        String ownerNo = "1234567890";
        String phone = "01012345678";
        String email = "psy@naver.com";
        LocalDateTime createAt = LocalDateTime.now();

        Owner owner = Owner.builder()
                .ownerNo(ownerNo)
                .phone(phone)
                .email(email)
                .createAt(createAt)
                .build();

        // When
        Set<ConstraintViolation<Owner>> validates = validator.validate(owner);

        // Then
        assertThat(validates).isNotNull();
        assertAll(
                () -> assertThat(owner.getOwnerNo()).isEqualTo(ownerNo),
                () -> assertThat(owner.getEmail()).isEqualTo(email),
                () -> assertThat(owner.getPhone()).isEqualTo(phone),
                () -> assertThat(owner.getCreateAt()).isEqualTo(createAt)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "psy",
            "psy@",
            "@naver.com",
            "psy@naver",
            "psy@na.c",
            "psy@nav.c.c"
    })
    void 이메일형식이_맞지않아_사업자_생성_실패_테스트(String email) {
        // Given
        String ownerNo = "1234567890";
        String phone = "01012345678";
        LocalDateTime createAt = LocalDateTime.now();

        Owner owner = Owner.builder()
                .ownerNo(ownerNo)
                .phone(phone)
                .email(email)
                .createAt(createAt)
                .build();

        // When
        Set<ConstraintViolation<Owner>> validates = validator.validate(owner);

        // Then
        assertThat(validates).isNotNull();
        validates.forEach(
                validate -> assertThat(validate.getMessage()).isEqualTo(ErrorMessage.Global.NOT_EMAIL)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "010",
            "123456789",
            "123456789012"
    })
    void 핸드폰형식이_맞지않아_사업자_생성_실패_테스트(String phone) {
        // Given
        String ownerNo = "1234567890";
        String email = "psy@naver.com";
        LocalDateTime createAt = LocalDateTime.now();

        Owner owner = Owner.builder()
                .ownerNo(ownerNo)
                .email(email)
                .phone(phone)
                .createAt(createAt)
                .build();

        // When
        Set<ConstraintViolation<Owner>> validates = validator.validate(owner);

        // Then
        assertThat(validates).isNotNull();
        validates.forEach(
                validate -> assertThat(validate.getMessage()).isEqualTo(ErrorMessage.Global.NOT_PHONE_PATTERN)
        );
    }
}
