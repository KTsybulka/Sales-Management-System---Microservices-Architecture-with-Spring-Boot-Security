package com.example.order_management_service.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    private Long productId;
    private String productName;
    private Long productPrice;
    private Long productQuantity;



}
