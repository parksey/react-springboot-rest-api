package com.example.gccoffee.order.dto;

import com.example.gccoffee.order.domain.OrderItem;

import java.util.List;

public record CreateOrderRequest(
        String email,
        String address,
        String postcode,
        List<OrderItem> orderItems
) {

}
