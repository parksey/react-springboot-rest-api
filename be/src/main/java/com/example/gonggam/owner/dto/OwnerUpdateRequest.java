package com.example.gonggam.owner.dto;

import com.example.gonggam.util.exception.ValidationStatus;
import com.example.gonggam.util.UtilsCode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OwnerUpdateRequest {

    @NotBlank(message = ValidationStatus.Global.NO_DATA)
    private String ownerNo;

    @Pattern(regexp = UtilsCode.Global.PHONE_PATTERN, message = ValidationStatus.Global.NOT_PHONE_PATTERN)
    @NotNull
    private String phone;

    @Email(regexp = UtilsCode.Global.EMAIL_PATTERN,message = ValidationStatus.Global.NOT_EMAIL)
    @NotNull
    private String email;

    @Builder
    public OwnerUpdateRequest(final String ownerNo, final String phone, final String email) {
        this.ownerNo = ownerNo;
        this.phone = phone;
        this.email = email;
    }
}
