package com.marketplace.sellerservice.controllers;

import com.marketplace.sellerservice.models.Media;
import com.marketplace.sellerservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/seller/{sellerId}/product/{productId}/media")
public class MediaProductController {

    @Autowired
    MediaService mediaService;

    @PostMapping
    public Media save(@PathVariable String sellerId, @PathVariable String productId, @RequestPart(value = "image") MultipartFile image){
        return mediaService.saveProduct(image,productId);
    }
}
