package com.marketplace.sellerservice.controllers;

import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping
    public Seller save(@RequestBody Seller seller){
        return sellerService.save(seller);
    }

    @GetMapping("/{sellerId}")
    public Seller getShop(@PathVariable String sellerId){
        return sellerService.getShopById(sellerId);
    }
}
