package com.programmingtechie.inventory.service.controller;

import com.programmingtechie.inventory.service.model.Inventory;
import com.programmingtechie.inventory.service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventorys")
@RequiredArgsConstructor
public class InventoryController {

private final InventoryService inventoryService;

    //http://localhost:8082/api/inventorys?skuCode=iphone15&skuCode=iphone 15 Pro&skuCode=iphone 14
    @GetMapping
    public ResponseEntity<?> isInStock(@RequestParam List<String> skuCode) {
        skuCode.forEach(System.out::println);
        return new ResponseEntity<>( inventoryService.isInStock(skuCode),HttpStatus.OK);
    }
}