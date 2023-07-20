package com.example.gonggam.customer.controller;


import com.example.gonggam.customer.dto.CustomerCreateRequest;
import com.example.gonggam.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/owners")
    public ResponseEntity<Void> createOwner(@RequestBody CustomerCreateRequest customerCreateRequest) {
        customerService.regist(customerCreateRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
