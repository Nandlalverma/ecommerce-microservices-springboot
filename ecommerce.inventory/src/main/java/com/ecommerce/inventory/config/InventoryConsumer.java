package com.ecommerce.inventory.config;

import com.ecommerce.inventory.dto.OrderEvent;
import com.ecommerce.inventory.dto.OrderItemEvent;
import com.ecommerce.inventory.entity.Inventory;
import com.ecommerce.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryConsumer {

    @Autowired
    private InventoryRepository inventoryRepository;

    public void consume(OrderEvent order){

        for(OrderItemEvent item : order.getItems()){
            Inventory inventory = inventoryRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("No inventory"));
            if(inventory.getStock() < item.getQuantity()){
                throw new RuntimeException("out of stock");
            }
            inventory.setStock(inventory.getStock() - item.getQuantity());
            inventoryRepository.save(inventory);
        }
    }
}
