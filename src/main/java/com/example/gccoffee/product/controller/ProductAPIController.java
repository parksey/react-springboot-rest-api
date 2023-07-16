package com.example.gccoffee.product.controller;

import com.example.gccoffee.product.domain.Category;
import com.example.gccoffee.product.domain.Product;
import com.example.gccoffee.product.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1")
@RestController
public class ProductAPIController {

    private final ProductService productService;

    public ProductAPIController(ProductService productService) {
        this.productService =productService;
    }

    @GetMapping("/products")
    public List<Product> products(@RequestParam Optional<Category> category) {
        return category.map(productService::getProductsByCategory)
                .orElse(productService.getAllProducts());
    }
}
