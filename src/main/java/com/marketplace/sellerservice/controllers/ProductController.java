package com.marketplace.sellerservice.controllers;

import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller/{sellerId}/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product save(@PathVariable String sellerId, @RequestBody Product product){
        return productService.save(product,sellerId);
    }

    @GetMapping("/{productId}")
    public Product get(@PathVariable String sellerId, @PathVariable String productId){
        return productService.getProductById(productId);
    }
}
