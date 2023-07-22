package com.example.gonggam.owner.dto;

import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OwnerRemoveRequest {

    @NotBlank(message = ValidationStatus.Global.NO_DATA)
    private String ownerNo;

    public OwnerRemoveRequest(final String ownerNo) {
        this.ownerNo = ownerNo;
    }
}
