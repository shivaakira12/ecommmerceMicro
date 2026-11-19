package com.shiva.product_service.controller;

import com.shiva.product_service.dtos.ProductRequest;
import com.shiva.product_service.dtos.ProductResponse;
import com.shiva.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductController {


    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        log.info("Product creation request received: {}", productRequest.getProductName());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        log.info("Request received to retrieve all products");
        return products;
    }
}
