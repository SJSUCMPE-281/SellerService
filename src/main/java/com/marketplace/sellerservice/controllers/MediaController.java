package com.marketplace.sellerservice.controllers;

import com.marketplace.sellerservice.models.Media;
import com.marketplace.sellerservice.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/media")
public class MediaController {

    private MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping
    public List<Media> save(@RequestPart(value = "images") MultipartFile[] images) {
        return mediaService.save(images);
    }
}
