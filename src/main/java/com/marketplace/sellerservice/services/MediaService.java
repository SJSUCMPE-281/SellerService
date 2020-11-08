package com.marketplace.sellerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.EventType;
import com.marketplace.sellerservice.models.Media;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.repositories.MediaRepository;
import com.marketplace.sellerservice.repositories.ProductRepository;
import com.marketplace.sellerservice.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class MediaService {

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AmazonClient amazonClient;

    @Autowired
    PublisherClient publisherClient;

    @Transactional
    public Media saveSeller(MultipartFile image, String id){
        String url = amazonClient.uploadImage(image);
        Seller seller = sellerRepository.findById(id).get();
        Media media = new Media();
        media.setUrl(url);
        media.setMediaId(UUID.randomUUID().toString());
        seller.getMediaList().add(media);
        Media newMedia = mediaRepository.save(media);
        sellerRepository.save(seller);
        return newMedia;
    }

    @Transactional
    public Media saveProduct(MultipartFile image, String id) throws JsonProcessingException {
        String url = amazonClient.uploadImage(image);
        Media media = new Media();
        Product product = productRepository.findById(id).get();
        media.setUrl(url);
        media.setMediaId(UUID.randomUUID().toString());
        product.getMediaList().add(media);
        Media newMedia = mediaRepository.save(media);
        publisherClient.publishSNSMessage(productRepository.save(product), EventType.ENTITY_UPDATE);
        return newMedia;
    }
}
