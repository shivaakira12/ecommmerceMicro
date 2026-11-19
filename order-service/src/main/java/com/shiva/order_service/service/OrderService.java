package com.shiva.order_service.service;

import com.shiva.order_service.config.WebClientConfig;
import com.shiva.order_service.dtos.InventoryResponse;
import com.shiva.order_service.dtos.OrderLineItemsDto;
import com.shiva.order_service.dtos.OrderRequest;
import com.shiva.order_service.entity.Order;
import com.shiva.order_service.entity.OrderLineItems;
import com.shiva.order_service.exception.OutOfStockException;
import com.shiva.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {


    private final OrderRepository orderRepository;

    private final WebClientConfig webClientConfig;

    public OrderService(OrderRepository orderRepository, WebClientConfig webClientConfig) {
        this.orderRepository = orderRepository;
        this.webClientConfig = webClientConfig;
    }

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList =  orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skudCodes = new ArrayList<>();
        for(OrderLineItems orderLineItems : orderLineItemsList){
            log.info("Order Line Item: {}", orderLineItems.getSkuCode());
            skudCodes.add(orderLineItems.getSkuCode());
        }

        // call the inventory service and place order if product is in stock
        InventoryResponse[] inventoryResponses = webClientConfig.webClient().get()
                .uri("http://localhost:8083/api/inventory",uriBuilder -> uriBuilder.queryParam("sku-code", skudCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        if (inventoryResponses == null) {
            throw new IllegalStateException("Inventory service returned an empty response");
        }
        boolean allProductsInStock = Arrays.stream(inventoryResponses)
                .allMatch(response -> response != null && Boolean.TRUE.equals(response.getIsInStock()));
        if(allProductsInStock){
            orderRepository.save(order);
        }
        else{
            log.error("Product is out of stock, please try again later");
            throw new OutOfStockException("Product is out of stock, please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}
