package com.dobrev.kafka.productmicroservice.controller;

import com.dobrev.kafka.productmicroservice.dto.ProductDto;
import com.dobrev.kafka.productmicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public String createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }
}