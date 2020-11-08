package com.marketplace.sellerservice.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketplace.sellerservice.mappers.ProductMapper;
import com.marketplace.sellerservice.models.EventType;
import com.marketplace.sellerservice.models.Product;
import com.marketplace.sellerservice.models.ProductDTO;
import com.marketplace.sellerservice.models.SNSEvent;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
public class PublisherClient {
    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Value("${amazon.sns.topic}")
    private String snsTopicARN;

    private AmazonSNS amazonSNS;

    @Autowired
    ProductMapper productMapper;

    @PostConstruct
    private void init(){
        amazonSNS = AmazonSNSClientBuilder.standard().withRegion(awsRegion).build();
    }
    public void publishSNSMessage(Product product, EventType eventType) throws JsonProcessingException {
        ProductDTO productDTO = productMapper.toDTO(product);
        SNSEvent snsEvent = new SNSEvent();
        snsEvent.setEventType(eventType);
        snsEvent.setProductDTO(productDTO);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonPayload = objectMapper.writeValueAsString(snsEvent);
        amazonSNS.publish(snsTopicARN,jsonPayload);
    }
}
