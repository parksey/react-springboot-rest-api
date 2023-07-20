package com.example.gonggam.customer.service;

import com.example.gonggam.customer.domain.Customer;
import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.customer.repository.CustomerRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void register(CustomerCreateRequest customerCreateRequest) {
        boolean existsEmail = customerRepository.existsByEmail(customerCreateRequest.getEmail());

        if (existsEmail) {
            throw new CustomerException(CustomValidationStatus.EXIST_USER);
        }

        Customer customer = customerMapper.toEntity(customerCreateRequest);
         customerRepository.save(customer);
    }
}
