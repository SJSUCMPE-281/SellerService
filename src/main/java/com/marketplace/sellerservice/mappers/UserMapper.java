package com.marketplace.sellerservice.mappers;

import com.marketplace.sellerservice.models.Seller;
import com.marketplace.sellerservice.models.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(Seller seller){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(seller.getSellerId());
        userDTO.setFirstName(seller.getFirstName());
        userDTO.setLastName(seller.getLastName());
        userDTO.setEmail(seller.getEmail());
        userDTO.setPhoneNumber(seller.getPhoneNumber());
        userDTO.setRole("Seller");
        userDTO.setCreatedAt(seller.getCreatedAt());
        userDTO.setUpdatedAt(seller.getUpdatedAt());
        return userDTO;
    }
}
