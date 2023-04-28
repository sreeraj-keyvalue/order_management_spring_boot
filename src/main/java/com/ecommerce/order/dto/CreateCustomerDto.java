package com.ecommerce.order.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerDto {

  @NotEmpty
  @NotNull
  @Size(min = 2, message = "Name must have at least 2 characters")
  private String name;

  @NotEmpty
  @NotNull
  private String phone;

  @NotEmpty
  @NotNull
  @Email
  private String email;

  @NotNull
  @NotEmpty
  @Size(min = 2, message = "Street must have at least 2 characters")
  private String street;

  @NotNull
  @NotEmpty
  @Size(min = 2, message = "City must have at least 2 characters")
  private String city;

  @NotNull
  @NotEmpty
  @Size(min = 2, message = "State must have at least 2 characters")
  private String state;

  private String profilePic;

}
