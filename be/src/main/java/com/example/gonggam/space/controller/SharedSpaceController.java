package com.example.gonggam.space.controller;

import com.example.gonggam.space.dto.SpaceCreateRequest;
import com.example.gonggam.space.dto.SpaceInfoResponse;
import com.example.gonggam.space.dto.SpaceSummary;
import com.example.gonggam.space.exception.SharedSpaceException;
import com.example.gonggam.space.service.SharedSpaceService;
import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.CustomValidationStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public ResponseEntity<SpaceInfoResponse> creatSpace(@RequestBody SpaceCreateRequest spaceCreateRequest, HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            throw new SharedSpaceException(CustomValidationStatus.LOGIN_ERROR);
        }

        Long ownerId = Long.parseLong((String) session.getAttribute(UtilsCode.Global.EMAIL));
        SpaceInfoResponse infoResponse = sharedSpaceService.createSpace(spaceCreateRequest, ownerId);
        return new ResponseEntity<>(infoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/spaces")
    public ResponseEntity<List<SpaceSummary>> spacesAll() {
        List<SpaceSummary> spacesResponse = sharedSpaceService.getSpacesAll();
        return new ResponseEntity<>(spacesResponse, HttpStatus.OK);
    }

    @GetMapping("/spaces/{spaceId}")
    public ResponseEntity<SpaceInfoResponse> spaceInfo(@PathVariable long spaceId) {
        SpaceInfoResponse infoResponse = sharedSpaceService.spaceInfo(spaceId);
        return new ResponseEntity<>(infoResponse, HttpStatus.OK);
    }

    @GetMapping("/spaces/mySpace")
    public ResponseEntity<List<SpaceSummary>> mySpaces(HttpServletRequest servletRequest) {
        HttpSession session = servletRequest.getSession(false);
        if (session == null) {
            throw new SharedSpaceException(CustomValidationStatus.LOGIN_ERROR);
        }

        String ownerNo = (String) session.getAttribute(UtilsCode.Global.OWNER_NO);
        List<SpaceSummary> spacesResponse = sharedSpaceService.findMySpaces(ownerNo);

        return new ResponseEntity<>(spacesResponse, HttpStatus.OK);
    }
}
