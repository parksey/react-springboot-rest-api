package com.example.gonggam.owner.controller;

import com.example.gonggam.owner.dto.OwnerRemoveRequest;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/{ownerNo}")
    public ResponseEntity<OwnerResponse> findOwnerInfo(@PathVariable String ownerNo) {
        OwnerResponse ownerResponse = ownerService.findOwnerInfo(ownerNo);
        return new ResponseEntity<>(ownerResponse, HttpStatus.OK);
    }

    @PostMapping("/owners")
    public ResponseEntity<OwnerResponse> createOwner(@RequestBody OwnerUpdateRequest ownerRequest) {
        OwnerResponse ownerResponse = ownerService.createOperator(ownerRequest);
        return new ResponseEntity<>(ownerResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/owners")
    public ResponseEntity<Void> deleteOwner(@RequestBody OwnerRemoveRequest ownerRequest) {
        ownerService.deleteOwner(ownerRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
