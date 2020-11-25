package com.marketplace.sellerservice.repositories;

import com.marketplace.sellerservice.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,String> {

    Iterable<Product> findAllBySellerIdAndActiveFlagEquals(String id,boolean isActive);
}
