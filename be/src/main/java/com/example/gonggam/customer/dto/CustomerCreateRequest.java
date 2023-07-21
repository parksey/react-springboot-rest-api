package com.example.gonggam.customer.dto;

import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CustomerCreateRequest {

    @Email(regexp = UtilsCode.Global.EMAIL_PATTERN, message = ValidationStatus.Global.NOT_EMAIL)
    @NotNull
    private String email;

    @NotBlank(message = ValidationStatus.Global.NOT_BLANK)
    private String password;

    String name;

    @Pattern(regexp = UtilsCode.Global.PHONE_PATTERN, message = ValidationStatus.Global.NOT_PHONE_PATTERN)
    @NotNull
    String phone;

    @Builder
    public CustomerCreateRequest(final String email,
                                 final String password,
                                 final String phone,
                                 final String name) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }
}
