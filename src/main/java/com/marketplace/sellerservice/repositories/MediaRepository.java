package com.marketplace.sellerservice.repositories;

import com.marketplace.sellerservice.models.Media;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<Media, String> {
}
