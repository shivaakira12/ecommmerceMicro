package com.shiva.product_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String productName;
    private String productDescription;
    private String productPrice;

}
