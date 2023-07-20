package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Customer테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerTest {

    private static Validator validator;

    @BeforeAll
    static void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void 회원_생성_성공_테스트() {
        // Given
        String name = "psy";
        String phone = "01012345678";
        String email = "psy@naver.com";

        Customer customer = Customer.builder()
                .email(email)
                .phone(phone)
                .name(name)
                .password("1234")
                .build();

        // When
        Set<ConstraintViolation<Customer>> validates = validator.validate(customer);

        // Then
        assertThat(validates).isNotNull();
        assertAll(
                () -> assertThat(customer.getName()).isEqualTo(name),
                () -> assertThat(customer.getEmail()).isEqualTo(email),
                () -> assertThat(customer.getPhone()).isEqualTo(phone)
        );
    }
}
