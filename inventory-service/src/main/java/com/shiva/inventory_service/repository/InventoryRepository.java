package com.shiva.inventory_service.repository;

import com.shiva.inventory_service.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    Optional<Object> findBySkuCodeIn(Collection<String> skuCodes);
}
