package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.global.testconfig.RepositoryAnnotation;
import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("CustomerRepostiroy 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class CustomerRepositoryTest extends RepositoryAnnotation {

    @Autowired
    private CustomerRepository customerRepository;

    private static Customer customer;

    @BeforeAll
    static void init() {
        // Given
        customer = Customer.builder()
                .email("parkseyeonTest@naver.com")
                .password("qwer1234" + UtilsCode.Global.SECRET_KEY)
                .phone("01011223344")
                .name("박")
                .build();
    }

    @Test
    void 회원_생성_성공_테스트() {
        // When
        Customer savedCustomer = customerRepository.save(customer);

        // Then
        assertThat(savedCustomer.getCustomerId()).isNotNull();
        assertAll(
                () -> assertThat(savedCustomer.getName()).isEqualTo(customer.getName()),
                () -> assertThat(savedCustomer.getPassword()).isEqualTo(customer.getPassword()),
                () -> assertThat(savedCustomer.getPhone()).isEqualTo(customer.getPhone()),
                () -> assertThat(savedCustomer.getEmail()).isEqualTo(customer.getEmail())
        );
    }

    @Test
    void 회원_생성_실패_테스트() {
        // Given
        customerRepository.save(customer);

        // When
        Customer newCustomer = Customer.builder()
                .email("parkseyeonTest@naver.com")
                .password("qwer1234" + UtilsCode.Global.SECRET_KEY)
                .phone("01011223344")
                .name("박")
                .build();

        // When
        assertThatThrownBy(() -> customerRepository.save(newCustomer))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
