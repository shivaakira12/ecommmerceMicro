package com.shiva.inventory_service.controller;


import com.shiva.inventory_service.dtos.InventoryResponse;
import com.shiva.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCode) {
        log.info("Checking inventory for SKU code: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

}
