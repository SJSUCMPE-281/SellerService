package com.marketplace.sellerservice.services;

import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public Seller save(Seller seller){
        return sellerRepository.save(seller);
    }

    public Seller getShopById(String id){
        return sellerRepository.findById(id).get();
    }
}
