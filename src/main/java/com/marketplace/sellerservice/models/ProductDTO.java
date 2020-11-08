package com.marketplace.sellerservice.models;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductDTO {
    @Id
    String productId;
    String productName;
    String productDescription;
    BigDecimal price;
    String sellerId;
    String shopName;
    String imageUrl;
    String category;
    int reviewCount;
    Double rating;
    Date createdAt;
    Date updatedAt;
}
