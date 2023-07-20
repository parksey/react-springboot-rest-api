package com.example.gonggam.space.controller;

import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceCreateResponse;
import com.example.gonggam.space.dto.SpaceSummary;
import com.example.gonggam.space.service.SharedSpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SharedSpaceController {

    private final SharedSpaceService sharedSpaceService;

    public SharedSpaceController(SharedSpaceService sharedSpaceService) {
        this.sharedSpaceService = sharedSpaceService;
    }

    @PostMapping("/spaces")
    public ResponseEntity<SpaceCreateResponse> creatSpace(@RequestBody SpaceCreateRequest spaceCreateRequest) {
        SpaceCreateResponse createResponse = sharedSpaceService.createSpace(spaceCreateRequest);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/spaces/{ownerNo}")
    public ResponseEntity<List<SpaceSummary>> getSpacesInfos(@PathVariable String ownerNo) {
        List<SpaceSummary> spacesResponse = sharedSpaceService.getSpaces(ownerNo);

        return new ResponseEntity<>(spacesResponse, HttpStatus.OK);
    }
}
