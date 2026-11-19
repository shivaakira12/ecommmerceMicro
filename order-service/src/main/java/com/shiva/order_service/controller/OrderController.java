package com.shiva.order_service.controller;

import com.shiva.order_service.dtos.OrderRequest;
import com.shiva.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Placing order");
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
