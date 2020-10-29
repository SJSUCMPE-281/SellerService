package com.marketplace.sellerservice.services;

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

    public Seller getSeller(String sellerId){
       return sellerRepository.findById(sellerId).get();
    }
    public Product save(Product product,String sellerId){
        product.setSellerId(sellerId);
        product.setProductId(UUID.randomUUID().toString());
        product.setShopName(getSeller(sellerId).getShopName());
        return productRepository.save(product);
    }
    public Product getProductById(String id){
        return productRepository.findById(id).get();
    }
}
