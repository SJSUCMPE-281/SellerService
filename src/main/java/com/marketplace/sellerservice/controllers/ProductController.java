package com.marketplace.sellerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/seller/{sellerId}/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public Product save(@PathVariable String sellerId, @RequestBody Product product) throws JsonProcessingException {
        return productService.save(product,sellerId);
    }

    @GetMapping("/{productId}")
    public Product get(@PathVariable String sellerId, @PathVariable String productId){
        return productService.getProductById(productId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String sellerId, @PathVariable String productId) throws JsonProcessingException {
        productService.delete(productId,sellerId);
    }

    @GetMapping
    public Iterable<Product> getAll(@PathVariable String sellerId){
        return productService.getProducts(sellerId);
    }

    @PutMapping
    public Product update(@PathVariable String sellerId, @RequestBody Product product) throws JsonProcessingException {
        return productService.update(sellerId,product);
    }
}
