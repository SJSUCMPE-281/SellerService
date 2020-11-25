package com.marketplace.sellerservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.marketplace.sellerservice.models.EventType;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.repositories.ProductRepository;
import com.marketplace.sellerservice.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PublisherClient publisherClient;

    public Seller getSeller(String sellerId){
       return sellerRepository.findById(sellerId).get();
    }
    public Product save(Product product,String sellerId) throws JsonProcessingException {
        product.setSellerId(sellerId);
        product.setProductId(UUID.randomUUID().toString());
        product.setShopName(getSeller(sellerId).getShopName());

        Product newProduct = productRepository.save(product);
        publisherClient.publishProductCreationEvent(newProduct, EventType.ENTITY_CREATE);
        return newProduct;
    }
    public Product getProductById(String id){
        return productRepository.findById(id).get();
    }

    public void delete(String productId, String sellerId) throws JsonProcessingException {
        Product product = getProductById(productId);
        product.setActiveFlag(false);
        productRepository.save(product);
        publisherClient.publishProductCreationEvent(product, EventType.ENTITY_DELETE);

    }

    public Iterable<Product> getProducts(String id){
        return productRepository.findAllBySellerIdAndActiveFlagEquals(id,true);
    }

    public Product update(String sellerId, Product product) throws JsonProcessingException {
        Product oldProduct = getProductById(product.getProductId());
        if(product.getProductName()!= null){
            oldProduct.setProductName(product.getProductName());
        }
        if(product.getProductDescription() != null){
            oldProduct.setProductDescription(product.getProductDescription());
        }
        if(product.getPrice() != null){
            oldProduct.setPrice(product.getPrice());
        }
        if(product.getCategory() != null){
            oldProduct.setCategory(product.getCategory());
        }
        if(product.getMediaList() != null && product.getMediaList().size() != 0){
            oldProduct.setMediaList(product.getMediaList());
        }
        Product newProduct = productRepository.save(oldProduct);
        publisherClient.publishProductCreationEvent(newProduct, EventType.ENTITY_UPDATE);
        return newProduct;
    }
}
