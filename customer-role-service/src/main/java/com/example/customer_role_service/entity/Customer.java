package com.example.customer_role_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

//    @NotBlank(message = "Customer name is required")
    private String customerName;

//    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String customerPassword;
    private boolean active;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    private String role;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
