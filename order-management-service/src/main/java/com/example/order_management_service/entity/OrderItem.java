package com.example.order_management_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    private String productName;
    private Long productPrice;
    private Long productQuantity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    @JsonBackReference
    private PurchaseOrder order;
}
