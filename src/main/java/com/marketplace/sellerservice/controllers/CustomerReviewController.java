package com.marketplace.sellerservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.CustomerReview;
import com.marketplace.sellerservice.services.CustomerReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/product/{productId}/review")
public class CustomerReviewController {
    @Autowired
    CustomerReviewService customerReviewService;

    @PostMapping
    public CustomerReview saveReview(@PathVariable String productId, @RequestBody CustomerReview review) throws JsonProcessingException {
        return customerReviewService.save(productId,review);
    }
}
