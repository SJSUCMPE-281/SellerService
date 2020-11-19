package com.marketplace.sellerservice.repositories;

import com.marketplace.sellerservice.models.CustomerReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReviewRepository extends CrudRepository<CustomerReview, String> {
    Iterable<CustomerReview> findAllByProduct_ProductId(String productId);
}
