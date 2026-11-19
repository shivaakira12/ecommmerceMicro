package com.shiva.order_service.dtos;

import com.shiva.order_service.entity.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
