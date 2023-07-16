package com.example.gccoffee.order.domain;

public enum OrderStatus {
    ACCEPT,
    PAYMENT_CONFIRMED,
    READY_FOR_DELIVERY,
    SHIPPED,
    SETTLED,
    CANCELED;
}
