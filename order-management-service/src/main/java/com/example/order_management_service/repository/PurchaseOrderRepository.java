package com.example.order_management_service.repository;

import com.example.order_management_service.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

//    public List<Order> findByProductName(String productName);
}
