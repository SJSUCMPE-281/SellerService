package com.marketplace.sellerservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    String productId;
    String productName;
    String productDescription;
    BigDecimal price;
    String sellerId;
    String shopName;
    String category;
    int reviewCount=0;
    Double rating=0.0;

    @OneToMany
    List<Media> mediaList = new ArrayList<>();

    @CreatedDate
    Date createdAt;

    @LastModifiedDate
    Date updatedAt;
}
