package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.dto.LoginRequest;
import com.example.gonggam.util.UtilsCode;
import org.springframework.stereotype.Component;

@Component
public class CustomerSecurity {

    public CustomerCreateRequest hashCreateRequest(CustomerCreateRequest customerCreateRequest) {
        return CustomerCreateRequest.builder()
                .email(customerCreateRequest.getEmail())
                .phone(customerCreateRequest.getPhone())
                .name(customerCreateRequest.getName())
                .password(hashPassword(customerCreateRequest.getPassword()))
                .build();
    }

    public boolean isSamePassword(Customer customer, LoginRequest loginRequest) {
        return customer.getPassword().equals(hashPassword(loginRequest.getPassword()));
    }

    private String hashPassword(String password) {
        return password + UtilsCode.Global.SECRET_KEY;
    }
}
