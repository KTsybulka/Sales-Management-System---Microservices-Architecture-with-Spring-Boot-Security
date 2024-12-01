package com.example.product_catalog_service.service;

import com.example.product_catalog_service.entity.Product;
import com.example.product_catalog_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Add a new product to the database
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    // Get a product by its name
    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    // Get a product by its ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    //  Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Update a product
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
