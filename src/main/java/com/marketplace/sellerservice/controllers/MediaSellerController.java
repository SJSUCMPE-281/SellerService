package com.marketplace.sellerservice.controllers;

import com.marketplace.sellerservice.models.Media;
import com.marketplace.sellerservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/seller/{sellerId}/media")
public class MediaSellerController {

    @Autowired
    MediaService mediaService;

    @PostMapping
    public Media save(@PathVariable String sellerId, @RequestPart(value = "image") MultipartFile image){
        return mediaService.saveSeller(image,sellerId);
    }
}
