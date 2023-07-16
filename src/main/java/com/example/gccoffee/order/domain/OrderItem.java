package com.example.gccoffee.order.domain;

import com.example.gccoffee.product.domain.Category;

import java.util.UUID;

public record OrderItem (
        UUID productId,
        Category category,
        long price,
        int quantity
){

}
