package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@DisplayName("CustomerService 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private CustomerMapper customerMapper;

    private Customer customer;

    @BeforeEach
    void init() {
        customer = Customer.builder()
                .email("psy@naver.com")
                .phone("01012345678")
                .name("psy")
                .build();
    }

    @Nested
    @DisplayName("유저 생성 테스트")
    class Create {

        @Test
        void 유저_생성_테스트() {
            // Given
            CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .name(customer.getName())
                    .phone(customer.getPhone())
                    .build();

            given(customerRepository.existsByEmail(customerCreateRequest.getEmail())).willReturn(false);
            given(customerMapper.toEntity(customerCreateRequest)).willReturn(customer);

            // When
            customerService.register(customerCreateRequest);

            // Then
            verify(customerRepository).save(customer);
        }

        @Test
        void 유저_생성_실패_테스트() {
            // Given
            CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .name(customer.getName())
                    .phone(customer.getPhone())
                    .build();

            given(customerRepository.existsByEmail(customerCreateRequest.getEmail())).willReturn(true);

            // When
            assertThatThrownBy(()->customerService.register(customerCreateRequest))
                    .isInstanceOf(CustomerException.class)
                    .hasMessage(CustomValidationStatus.EXIST_USER.getMessage());
        }
    }
}