package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toEntity(CustomerCreateRequest customerCreateRequest) {
        return Customer.builder()
                .email(customerCreateRequest.getEmail())
                .password(customerCreateRequest.getPassword())
                .name(customerCreateRequest.getName())
                .phone(customerCreateRequest.getPhone())
                .build();
    }
}
