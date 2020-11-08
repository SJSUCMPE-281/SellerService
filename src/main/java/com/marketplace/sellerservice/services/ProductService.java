package com.marketplace.sellerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.EventType;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.repositories.ProductRepository;
import com.marketplace.sellerservice.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PublisherClient publisherClient;

    public Seller getSeller(String sellerId){
       return sellerRepository.findById(sellerId).get();
    }
    public Product save(Product product,String sellerId) throws JsonProcessingException {
        product.setSellerId(sellerId);
        product.setProductId(UUID.randomUUID().toString());
        product.setShopName(getSeller(sellerId).getShopName());
        Product newProduct = productRepository.save(product);
        publisherClient.publishSNSMessage(newProduct, EventType.ENTITY_CREATE);
        return newProduct;
    }
    public Product getProductById(String id){
        return productRepository.findById(id).get();
    }

    public void delete(String productId, String sellerId) throws JsonProcessingException {
        Product product = getProductById(productId);
        publisherClient.publishSNSMessage(product, EventType.ENTITY_DELETE);
        productRepository.deleteById(productId);

    }
}
