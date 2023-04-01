package com.microservice.inventoryservice.controller;

import com.microservice.inventoryservice.dto.InventoryRequest;
import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode,@RequestParam List<Integer> quantity){
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping("/add-inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveInventory(@RequestBody InventoryRequest inventoryRequest){
        inventoryService.saveInventory(inventoryRequest);
    }
}
