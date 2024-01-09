package com.programmingtechie.inventory.service;

import com.programmingtechie.inventory.service.model.Inventory;
import com.programmingtechie.inventory.service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args ->{
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone 15");
			inventory.setQuantity(10);
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone14");
			inventory1.setQuantity(100);
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone15-Pro");
			inventory2.setQuantity(50);
			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("iphone15-ProMax");
			inventory3.setQuantity(0);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
		};

	}

}
