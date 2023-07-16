package com.example.gccoffee.order.domain;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {
    private final String email;

    public Email(String email) {
        Assert.notNull(email, "Address should not be null");
        Assert.isTrue(email.length() >= 4 && email.length() <= 40, "address length must be between 4 and 50 characters");
        Assert.isTrue(checkEmail(email), "Invalid email address");
        this.email = email;
    }

    private boolean checkEmail(String email) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", email);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Email email = (Email) obj;
        return Objects.equals(this.email, email.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
