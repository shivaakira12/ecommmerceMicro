package com.shiva.product_service.service;

import com.shiva.product_service.dtos.ProductRequest;
import com.shiva.product_service.dtos.ProductResponse;
import com.shiva.product_service.entity.Product;
import com.shiva.product_service.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
//
//    public ProductService(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    public void createProduct(ProductRequest productRequest) {
       Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productDescription(productRequest.getProductDescription())
                .productPrice(productRequest.getProductPrice())
                .build();
        productRepository.save(product);
        log.info("Product created successfully: {}", product.getProductName());
    }

    public List<ProductResponse> getAllProducts() {
       List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .build();
    }
}
