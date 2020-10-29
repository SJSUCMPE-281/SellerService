package com.marketplace.sellerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SellerserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellerserviceApplication.class, args);
    }

}
