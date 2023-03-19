package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryRequest;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }

    public void saveInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = Inventory.builder()
                .quantity(inventoryRequest.getQuantity())
                .skuCode(inventoryRequest.getSkuCode()).build();
        inventoryRepository.save(inventory);

        log.info("Inventory add {}",inventory.getId());
    }
}
