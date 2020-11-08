package com.marketplace.sellerservice.models;

import lombok.Data;

@Data
public class SNSEvent {
    EventType eventType;
    ProductDTO productDTO;
}
