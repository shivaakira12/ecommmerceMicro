package com.shiva.order_service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {
    @JsonProperty("isInStock")
    private Boolean isInStock;

    @JsonProperty("skuCode")
    private String skuCode;
}
