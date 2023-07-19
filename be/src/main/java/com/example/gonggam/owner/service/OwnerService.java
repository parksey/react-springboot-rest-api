package com.example.gonggam.owner.service;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.dto.OwnerRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public OwnerResponse createOperator(OwnerRequest ownerRequest) {
        boolean alreadyOwnerNoExist = ownerRepository.existsByOwnerNo(ownerRequest.getOwnerNo());

        if (alreadyOwnerNoExist) {
            throw new OwnerException(CustomValidationStatus.EXIST_OWNER);
        }

        Owner owner = ownerMapper.toEntity(ownerRequest);
        Owner savedOwner = ownerRepository.save(owner);

        return ownerMapper.toResponse(savedOwner);
    }

    public void updateOperator(OwnerRequest ownerRequest) {
        Owner existsOwner = ownerRepository.findByOwnerNo(ownerRequest.getOwnerNo())
                .orElseThrow(()->new OwnerException(CustomValidationStatus.NO_OWNER));

        Owner owner = ownerMapper.toEntity(ownerRequest);
        existsOwner.update(owner);
        ownerRepository.save(existsOwner);
    }
}
