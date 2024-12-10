package com.example.order_management_service.scheduler;


import com.example.order_management_service.entity.OrderItem;
import com.example.order_management_service.entity.Product;
import com.example.order_management_service.entity.PurchaseOrder;
import com.example.order_management_service.service.PurchaseOrderService;
import com.example.order_management_service.serviceClient.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class PurchaseOrderScheduler {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    ProductServiceClient productServiceClient;

    @Scheduled(cron = "0 */2 * * * *")
    public void updateDataBase(){
        log.info("Job started: Updating orders with the latest product prices");

        try {
            // Fetch all existing orders
            List<PurchaseOrder> orders = purchaseOrderService.getAllTOrders();

            for (PurchaseOrder order : orders) {
                log.info("Processing order ID: {}", order.getOrderId());

                for (OrderItem item : order.getOrderItems()) {
                    // Fetch the latest product details from the remote service
                    Product product = productServiceClient.getProductDetail(item.getProductName());

                    if (product != null) {
                        // Update the price in the order item
                        item.setProductPrice(product.getProductPrice());
                        log.info("Updated product '{}' in order ID {} with new price: {}",
                                item.getProductName(), order.getOrderId(), product.getProductPrice());
                    } else {
                        log.warn("Product '{}' not found in remote service. Skipping update for this item.", item.getProductName());
                    }
                }

                // Save the updated order with updated item prices
                purchaseOrderService.saveOrder(order); // Add saveOrder() in your PurchaseOrderService
            }

            log.info("All orders updated with the latest product prices");

        } catch (Exception e) {
            log.error("Error updating orders with actual prices: {}", e.getMessage(), e);
        }

        log.info("Job finished");

    }
}
