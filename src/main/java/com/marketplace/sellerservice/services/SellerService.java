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
            Seller existingSeller = sellerRepository.findById(seller.getSellerId()).get();
            boolean isSaveNeeded = false;
            if((existingSeller.getMediaList() == null || existingSeller.getMediaList().size() == 0) && seller.getMediaList() != null) {
                existingSeller.setMediaList(seller.getMediaList());
                isSaveNeeded = true;
            }
            if(existingSeller.getShopDescription() == null && seller.getShopDescription() != null) {
                existingSeller.setShopDescription(seller.getShopDescription());
                isSaveNeeded = true;
            }
            if(existingSeller.getShopName() == null && seller.getShopName() != null) {
                existingSeller.setShopName(seller.getShopName());
                isSaveNeeded = true;
            }
            if(isSaveNeeded) {
                existingSeller = sellerRepository.save(existingSeller);
                publisherClient.publishUserRegistrationEvent(existingSeller);
            }
            return existingSeller;
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
