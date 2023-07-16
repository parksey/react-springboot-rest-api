package com.example.gccoffee.product.dto;

import com.example.gccoffee.product.domain.Category;

public record CreateProductRequest (
        String productName,
        Category category,
        long price,
        String description
){
}
