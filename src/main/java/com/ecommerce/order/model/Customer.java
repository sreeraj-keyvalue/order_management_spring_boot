package com.ecommerce.order.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer extends BaseEntity {

  private String name;

  private String phone;

  private String email;

  private String street;

  private String city;

  private String state;

  private String profilePic;

  private Integer countryCode;

}
