package com.example.order_management_service.restController;

import com.example.order_management_service.entity.PurchaseOrder;
import com.example.order_management_service.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService orderService;


    @GetMapping
    public String test() {
        System.out.println("Hello");
        return "Ura";
    }

    @PostMapping("/triggerOrder")
    public ResponseEntity<PurchaseOrder> addDataOrder(@RequestBody Map<String, Long> productRequests) {
        if (productRequests == null || productRequests.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if input is invalid
        }

        // Corrected ResponseEntity constructor
        return new ResponseEntity<>(orderService.createOrder(productRequests), HttpStatus.OK);
    }


//    @Operation(summary = "Get all sales transactions", description = "Fetch all transactions from the database.")
    @GetMapping("/orders")
    public ResponseEntity<List<PurchaseOrder>> getOrders(){
        return ResponseEntity.ok(orderService.getAllTOrders());
    }

}
