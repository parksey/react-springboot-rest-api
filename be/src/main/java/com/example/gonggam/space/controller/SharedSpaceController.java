package com.example.gonggam.space.controller;

import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceInfoResponse;
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
    public ResponseEntity<SpaceInfoResponse> creatSpace(@RequestBody SpaceCreateRequest spaceCreateRequest) {
        SpaceInfoResponse infoResponse = sharedSpaceService.createSpace(spaceCreateRequest);
        return new ResponseEntity<>(infoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/spaces")
    public ResponseEntity<List<SpaceSummary>> spacesAll() {
        List<SpaceSummary> spacesResponse = sharedSpaceService.getSpacesAll();
        return new ResponseEntity<>(spacesResponse, HttpStatus.OK);
    }

    @GetMapping("/spaces/{ownerNo}")
    public ResponseEntity<List<SpaceSummary>> getSpacesInfos(@PathVariable String ownerNo) {
        List<SpaceSummary> spacesResponse = sharedSpaceService.getSpaces(ownerNo);

        return new ResponseEntity<>(spacesResponse, HttpStatus.OK);
    }

    @GetMapping("/spaces/{spaceId}")
    public ResponseEntity<SpaceInfoResponse> spaceInfo(@PathVariable long spaceId) {
        SpaceInfoResponse infoResponse = sharedSpaceService.spaceInfo(spaceId);
        return new ResponseEntity<>(infoResponse, HttpStatus.OK);
    }
}
