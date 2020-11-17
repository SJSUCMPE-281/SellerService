package com.marketplace.sellerservice.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class UserDTO {
    @Id
    String userId;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String role;
    Date createdAt;
    Date updatedAt;
}
