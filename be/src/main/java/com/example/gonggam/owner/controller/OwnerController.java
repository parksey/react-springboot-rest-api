package com.example.gonggam.owner.controller;

import com.example.gonggam.owner.dto.OwnerRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping("/owners")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.createOperator(ownerRequest);
        return new ResponseEntity<>(ownerResponse, HttpStatus.CREATED);
    }
}
