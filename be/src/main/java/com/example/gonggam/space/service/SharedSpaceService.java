package com.example.gonggam.space.service;

import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.owner.repository.OwnerRepository;
import com.example.gonggam.space.domain.SharedSpace;
import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceCreateResponse;
import com.example.gonggam.space.dto.SpaceSummary;
import com.example.gonggam.space.repository.SharedSpaceRepository;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SharedSpaceService {

    private final SharedSpaceRepository sharedSpaceRepository;
    private final OwnerRepository ownerRepository;

    private final SpaceMapper spaceMapper;


    public SharedSpaceService(SharedSpaceRepository sharedSpaceRepository, OwnerRepository ownerRepository, SpaceMapper spaceMapper) {
        this.sharedSpaceRepository = sharedSpaceRepository;
        this.ownerRepository = ownerRepository;
        this.spaceMapper = spaceMapper;
    }

    @Transactional
    public SpaceCreateResponse createSpace(SpaceCreateRequest spaceCreateRequest) {
        boolean isExistsOwner = ownerRepository.existsById(spaceCreateRequest.getOwnerId());

        if (!isExistsOwner) {
            throw new OwnerException(CustomValidationStatus.NO_OWNER);
        }

        SharedSpace sharedSpace = spaceMapper.toEntity(spaceCreateRequest);
        SharedSpace savedSharedSpace = sharedSpaceRepository.save(sharedSpace);
        return spaceMapper.toResponse(savedSharedSpace);
    }

    public List<SpaceSummary> getSpaces(String ownerNo) {
        List<SharedSpace> owners =  sharedSpaceRepository.findAllByOwnerNo(ownerNo);
        return spaceMapper.toResponse(owners);
    }
}
