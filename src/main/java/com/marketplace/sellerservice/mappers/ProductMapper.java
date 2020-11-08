package com.marketplace.sellerservice.mappers;


import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.models.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSellerId(product.getSellerId());
        productDTO.setShopName(product.getShopName());
        productDTO.setCategory(product.getCategory());
        productDTO.setRating(product.getRating());
        productDTO.setReviewCount(product.getReviewCount());
        if(product.getMediaList().size() != 0) {
            productDTO.setImageUrl(product.getMediaList().get(0).getUrl());
        }
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setUpdatedAt(product.getUpdatedAt());
        return productDTO;
    }
}
