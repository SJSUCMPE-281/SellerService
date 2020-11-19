package com.marketplace.sellerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.CustomerReview;
import com.marketplace.sellerservice.models.EventType;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.repositories.CustomerReviewRepository;
import com.marketplace.sellerservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CustomerReviewService {
    @Autowired
    CustomerReviewRepository customerReviewRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PublisherClient publisherClient;

    @Transactional
    public CustomerReview save(String productId, CustomerReview review) throws JsonProcessingException {
        review.setReviewId(UUID.randomUUID().toString());
        Product product = productRepository.findById(productId).get();
        Double total = review.getRating()+(product.getRating()*product.getReviewCount());
        product.setReviewCount(product.getReviewCount()+1);
        product.setRating(total/product.getReviewCount());
        review.setProduct(productRepository.save(product));
        publisherClient.publishProductCreationEvent(product, EventType.ENTITY_UPDATE);
        return customerReviewRepository.save(review);
    }

    public Iterable<CustomerReview> get(String productId){
        return customerReviewRepository.findAllByProduct_ProductId(productId);
    }
}
