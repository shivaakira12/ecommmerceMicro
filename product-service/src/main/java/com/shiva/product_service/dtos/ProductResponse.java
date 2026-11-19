package com.shiva.product_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private String productDescription;
    private String productPrice;
}
