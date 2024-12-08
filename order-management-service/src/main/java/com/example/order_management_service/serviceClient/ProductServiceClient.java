package com.example.order_management_service.serviceClient;

import com.example.order_management_service.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class ProductServiceClient {

    @Value("${productCatalogAdaptor.urlTemplate}")
    private String urlTemplate;

    private final RestTemplate restTemplate;

    public ProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Product getProductDetail(String productName){
        try{
            log.info("Fetching product details for product name: {}", productName);
            return restTemplate.getForObject(urlTemplate, Product.class, productName);
        }catch (Exception e){
            log.error("Error fetching product details for product name: {}", productName, e);
            return null;
        }
    }
}
