package com.shiva.order_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDto {

    private String skuCode;
    private String price;
    private Integer quantity;
}
