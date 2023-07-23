package com.example.gonggam.owner.dto;

public class OwnerLoginRequest {

    private String ownerNo;

    public OwnerLoginRequest(final String ownerNo) {
        this.ownerNo = ownerNo;
    }

    protected OwnerLoginRequest() {
    }

    public String getOwnerNo() {
        return ownerNo;
    }
}
