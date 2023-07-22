package com.example.gonggam.customer.dto;

import com.example.gonggam.util.UtilsCode;
import com.example.gonggam.util.exception.ValidationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Email(regexp = UtilsCode.Global.EMAIL_PATTERN, message = ValidationStatus.Global.NOT_EMAIL)
    @NotNull
    private final String email;

    @NotBlank(message = ValidationStatus.Global.NOT_BLANK)
    private final String password;

    public LoginRequest(final String email,
                        final String password) {
        this.email = email;
        this.password = password;
    }
}
