package com.example.order_management_service.repository;

import com.example.order_management_service.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    public List<OrderItem> findByProductName(String productName);
}
