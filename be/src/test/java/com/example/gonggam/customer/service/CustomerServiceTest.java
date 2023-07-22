package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.dto.LoginRequest;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("CustomerService 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private CustomerSecurity customerSecurity;

    @Spy
    private CustomerMapper customerMapper;

    private Customer customer;

    @BeforeEach
    void init() {
        customer = Customer.builder()
                .email("psy@naver.com")
                .password("qwer1234")
                .phone("01012345678")
                .name("psy")
                .build();
    }

    @Nested
    @DisplayName("유저 생성 테스트")
    class Create {

        @Test
        void 유저_생성_성공_테스트() {
            // Given
            CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                    .email(customer.getEmail())
                    .password(customer.getPassword())
                    .name(customer.getName())
                    .phone(customer.getPhone())
                    .build();

            given(customerRepository.existsByEmail(customerCreateRequest.getEmail())).willReturn(false);
            given(customerSecurity.hashCreateRequest(customerCreateRequest)).willReturn(customerCreateRequest);
            given(customerMapper.toEntity(customerCreateRequest)).willReturn(customer);

            // When
            customerService.register(customerCreateRequest);

            // Then
            verify(customerSecurity).hashCreateRequest(customerCreateRequest);
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

            // When + THen
            assertThatThrownBy(()->customerService.register(customerCreateRequest))
                    .isInstanceOf(CustomerException.class)
                    .hasMessage(CustomValidationStatus.EXIST_USER.getMessage());
        }
    }

    @Nested
    @DisplayName("유저_로그인_테스트")
    class Login {

        @Test
        void 로그인_성공_테스트() {
            // Given
            LoginRequest loginRequest = new LoginRequest(customer.getEmail(), customer.getPassword());

            Customer hashedCustomer = Customer.builder()
                    .password(customer.getPassword()+ UtilsCode.Global.SECRET_KEY)
                    .build();

            given(customerRepository.findByEmail(loginRequest.getEmail())).willReturn(Optional.of(hashedCustomer));

            // When
            boolean loginSuccess = customerService.login(loginRequest);

            // Then
            assertThat(loginSuccess).isTrue();

            verify(customerSecurity).isSamePassword(hashedCustomer, loginRequest);
        }

        @Test
        void 회원정보가_없어_로그인_실패_테스트() {
            // Given
            LoginRequest loginRequest = new LoginRequest(customer.getEmail(), customer.getPassword());

            Customer hashedCustomer = Customer.builder()
                    .password(customer.getPassword())
                    .build();

            given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.empty());

            // When + Then
            assertThatThrownBy(()->customerService.login(loginRequest))
                    .isInstanceOf(CustomerException.class)
                    .hasMessage(CustomValidationStatus.CHECK_AGAIN.getMessage());

        }

        @Test
        void 비밀번호_다를때_로그인_실패_테스트() {
            // Given
            LoginRequest loginRequest = new LoginRequest(customer.getEmail(), customer.getPassword());

            Customer hashedCustomer = Customer.builder()
                    .password(customer.getPassword())
                    .build();

            given(customerRepository.findByEmail(customer.getEmail())).willReturn(Optional.of(hashedCustomer));

            // When
            boolean loginSuccess = customerService.login(loginRequest);

            // Then
            assertThat(loginSuccess).isFalse();

            verify(customerSecurity).isSamePassword(hashedCustomer, loginRequest);
        }

    }
}