package com.example.gonggam.owner.service;

import com.example.gonggam.owner.domain.Owner;
import com.example.gonggam.owner.dto.OwnerRemoveRequest;
import com.example.gonggam.owner.dto.OwnerResponse;
import com.example.gonggam.owner.dto.OwnerUpdateRequest;
import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final SharedSpaceRepository sharedSpaceRepository;
    private final OwnerMapper ownerMapper;

    public OwnerService(OwnerRepository ownerRepository, SharedSpaceRepository sharedSpaceRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.sharedSpaceRepository = sharedSpaceRepository;
        this.ownerMapper = ownerMapper;
    }

    public OwnerResponse createOperator(OwnerUpdateRequest ownerRequest) {
        boolean alreadyOwnerNoExist = ownerRepository.existsByOwnerNo(ownerRequest.getOwnerNo());

        if (alreadyOwnerNoExist) {
            throw new OwnerException(CustomValidationStatus.EXIST_OWNER);
        }

        Owner owner = ownerMapper.toEntity(ownerRequest);
        Owner savedOwner = ownerRepository.save(owner);

        return ownerMapper.toResponse(savedOwner);
    }

    public void updateOperator(OwnerUpdateRequest ownerRequest) {
        Owner existsOwner = ownerRepository.findByOwnerNo(ownerRequest.getOwnerNo())
                .orElseThrow(()->new OwnerException(CustomValidationStatus.NO_OWNER));

        Owner owner = ownerMapper.toEntity(ownerRequest);
        existsOwner.update(owner);
        ownerRepository.save(existsOwner);
    }

    public void removeOwner(OwnerRemoveRequest ownerRemoveRequest) {
        Owner existsOwner = ownerRepository.findByOwnerNo(ownerRemoveRequest.getOwnerNo())
                .orElseThrow(()->new OwnerException(CustomValidationStatus.NO_OWNER));

        List<SharedSpace> sharedSpaces = sharedSpaceRepository.findAllByOwnerNo(ownerRemoveRequest.getOwnerNo());

        if (!sharedSpaces.isEmpty()) {
            // 예약 있는지 추가 확인 필요
            throw new OwnerException(CustomValidationStatus.NO_SHAED_SPACE);
        }

        ownerRepository.delete(existsOwner);
    }
}
