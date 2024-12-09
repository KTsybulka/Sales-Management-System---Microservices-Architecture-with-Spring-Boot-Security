package com.example.order_management_service.service;

import com.example.order_management_service.entity.PurchaseOrder;
import com.example.order_management_service.entity.OrderItem;
import com.example.order_management_service.entity.Product;
import com.example.order_management_service.repository.OrderItemRepository;
import com.example.order_management_service.repository.PurchaseOrderRepository;
import com.example.order_management_service.serviceClient.ProductServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

//
//    public OrderItem addOrderItem(String productName, Long requestedQuantity) {
//        Product product = productServiceClient.getProductDetail(productName);
//
//        if(Objects.isNull(product)){
//            log.error("Product not found for product name: {}", productName);
//            return null;
//        }
//
//        return  orderItemRepository.save(populateOrderItem(product, requestedQuantity));
//    }

    @Transactional
    public PurchaseOrder createOrder(Map<String, Long> productRequests) {
        // productRequests = { "productName1": quantity1, "productName2": quantity2 }

        PurchaseOrder order = new PurchaseOrder(); // Create new order

        List<OrderItem> items = productRequests.entrySet().stream()
                .map(entry -> {
                    String productName = entry.getKey();
                    Long requestedQuantity = entry.getValue();

                    // Fetch product details
                    Product product = productServiceClient.getProductDetail(productName);

                    if (product != null && product.getProductQuantity() >= requestedQuantity) {
                        // Associate the item with the order
                        return OrderItem.builder()
                                .productName(product.getProductName())
                                .productPrice(product.getProductPrice())
                                .productQuantity(requestedQuantity)
                                .purchaseOrder(order) // Associate with the order
                                .build();
                    } else {
                        log.error("Product '{}' is not available or insufficient stock. Requested: {}, Available: {}",
                                productName, requestedQuantity, product != null ? product.getProductQuantity() : 0);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Remove invalid/null items
                .toList();

        if (items.isEmpty()) {
            throw new RuntimeException("No valid products available to create the order.");
        }

        order.setOrderItems(items); // Associate items with the order
        return orderRepository.save(order); // Save the order along with items
    }

//
//
//    public OrderItem populateOrderItem(Product product, Long requestedQuantity) {
//        return OrderItem.builder()
//                .productName(product.getProductName())
//                .productPrice(product.getProductPrice())
//                .productQuantity(requestedQuantity)
//                .build();
//    }
//
////    @Transactional
//    public PurchaseOrder createOrder(Map<String, Long> productRequests) {
//        // productRequests = { "productName1": quantity1, "productName2": quantity2 }
//
//        List<OrderItem> items = productRequests.entrySet().stream()
//                .map(entry -> {
//                    String productName = entry.getKey();
//                    Long requestedQuantity = entry.getValue();
//
//                    // Fetch product details
//                    Product product = productServiceClient.getProductDetail(productName);
//
//                    if (product != null && product.getProductQuantity() >= requestedQuantity) {
//                        return populateOrderItem(product, requestedQuantity);
//                    } else {
//                        log.error("Product '{}' is not available or insufficient stock. Requested: {}, Available: {}",
//                                productName, requestedQuantity, product != null ? product.getProductQuantity() : 0);
//                        return null;
//                    }
//                })
//                .filter(Objects::nonNull) // Remove invalid/null items
//                .toList();
//
//        if (items.isEmpty()) {
//            throw new RuntimeException("No valid products available to create the order.");
//        }
//
//        // Create the Order
//        PurchaseOrder order = new PurchaseOrder();
//        order.setOrderItems(items);
//
//        return orderRepository.save(order);
//    }

    public List<PurchaseOrder> getAllTOrders(){
        List<PurchaseOrder> orders = orderRepository.findAll();
        log.info("Fetched {} orders from the database.", orders.size());
        return orders;
    }

    public PurchaseOrder getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public List<OrderItem> getOrderItems(Long orderId) {
        return orderItemRepository.findByPurchaseOrderOrderId(orderId);
    }
}
