package com.example.product_catalog_service.restController;

import com.example.product_catalog_service.entity.Product;
import com.example.product_catalog_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.info("Fetching all products");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

//    Add a new product
    @PostMapping
    public ResponseEntity<String> addNewProduct(@RequestBody Product product) {
        log.info("Adding new product: {} ", product);
        productService.addProduct(product);
        return new ResponseEntity<>("Product is added Successfully !!", HttpStatus.CREATED);
    }

//    Get a product by its name
    @GetMapping("/name/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        log.info("Fetching product by name: {} ", productName);
        Product product = productService.getProductByName(productName);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Get a product by its ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        log.info("Fetching product by ID: {} ", productId);
        Product product = productService.getProductById(productId);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update a product
    @PutMapping
    public ResponseEntity<String>  updateProduct(@RequestBody Product product){
        log.info("Updating product: {} ", product);
        productService.updateProduct(product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    //Delete a product
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        log.info("Deleting product by ID: {} ", productId);
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.NO_CONTENT);
    }
}
