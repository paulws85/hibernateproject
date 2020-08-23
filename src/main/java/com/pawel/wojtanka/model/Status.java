package com.pawel.wojtanka.model;

public enum Status {

    IN_RENT("In rent"),
    RETURNED("Returned");

    private final String status;

    Status(String status) {
        this.status = status;
    }

}