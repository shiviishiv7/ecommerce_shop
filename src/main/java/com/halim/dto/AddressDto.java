package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public  class AddressDto  {

    private Integer id;
    @Min(value = 100,message = "User ID should be greater than 100 or equal to 100")
    @NotNull(message = "User Id is mandatory")
    private Integer userId;
    @NotBlank(message = "First is mandatory")
    @Length(min = 3,max = 30,message = "First Name must be length of 3 and 30")
    private String firstName;

    @Length(min = 3,max = 30,message = "Last Name must be length of 3 and 30")
    @NotBlank(message = "Last is mandatory")
    private String lastName;
    @Length(min = 3,max = 30,message = "streetAddress must be length of 3 and 30")
    @NotBlank(message = "street is mandatory")
    private String streetAddress;
    @Length(min = 3,max = 30,message = "landmark must be length of 3 and 30")
    @NotBlank(message = "landmark is mandatory")
    private String landmark;
    @Length(min = 2,max = 30,message = "state must be length of 2 and 30")
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "townorcity is mandatory")
    @Length(min = 3,max = 30,message = "CityName must be length of 3 and 30")
    private   String townorcity;
    @Length(min = 6,max = 6,message = "Zipcode must be length of 6")
    @NotBlank(message = "Zipcode is mandatory")
    private   String postcodezip;
    @Length(min = 3,max = 30,message = "Country must be length of 3 and 30")
    @NotBlank(message = "Country is mandatory")
    private   String country;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    private   String email;
    @Length(min = 12,max = 12,message = "Phone number Length should be greater than 12")
    @NotBlank(message = "phone number Name is mandatory")
    private    String phoneno;

}
