package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.dto.LoginRequest;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerSecurity customerSecurity;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomerSecurity customerSecurity) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customerSecurity = customerSecurity;
    }

    public void register(CustomerCreateRequest customerCreateRequest) {
        boolean existsEmail = customerRepository.existsByEmail(customerCreateRequest.getEmail());

        if (existsEmail) {
            throw new CustomerException(CustomValidationStatus.EXIST_USER);
        }

        CustomerCreateRequest hashedRequest = customerSecurity.hashCreateRequest(customerCreateRequest);
        Customer customer = customerMapper.toEntity(hashedRequest);
        customerRepository.save(customer);
    }

    public boolean login(LoginRequest loginRequest) {
        Customer customer = customerRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomerException(CustomValidationStatus.CHECK_AGAIN));

        return customerSecurity.isSamePassword(customer, loginRequest);
    }
}
