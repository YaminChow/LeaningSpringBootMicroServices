package com.programmingtechie.inventory.service.service;

import com.programmingtechie.inventory.service.dto.InventoryResponse;
import com.programmingtechie.inventory.service.model.Inventory;
import com.programmingtechie.inventory.service.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Transactional
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        //Configure for timeout error
//        log.info("Start Waiting");
//        Thread.sleep(10000);
//        log.info("End waiting");


//        List<Inventory> inventory = inventoryRepository.findBySkuCodeIn(skuCode)
//                .orElseThrow(()-> new RuntimeException("Cannot find Product by sku code"+ skuCode));
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .inStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
