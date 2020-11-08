package com.marketplace.sellerservice.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class CustomerReview {
    @Id
    String reviewId;
    String review;
    Double rating;
    String buyerId;
    String buyerName;
    @OneToOne
    Product product;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;
}
