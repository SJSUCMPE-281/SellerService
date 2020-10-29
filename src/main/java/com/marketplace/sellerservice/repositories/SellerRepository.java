package com.marketplace.sellerservice.repositories;

import com.marketplace.sellerservice.models.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, String> {
}
