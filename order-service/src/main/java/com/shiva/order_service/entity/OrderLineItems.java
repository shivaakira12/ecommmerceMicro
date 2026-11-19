package com.shiva.order_service.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private String price;
    private Integer quantity;
}
