package com.marketplace.sellerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PublisherClient publisherClient;

    public Seller save(Seller seller) throws JsonProcessingException {
        try {
            return sellerRepository.findById(seller.getSellerId()).get();
        } catch(Exception e) {
            Seller newSeller = sellerRepository.save(seller);
            publisherClient.publishUserRegistrationEvent(newSeller);
            return newSeller;
        }
    }

    public Seller getShopById(String id){
        return sellerRepository.findById(id).get();
    }
}
