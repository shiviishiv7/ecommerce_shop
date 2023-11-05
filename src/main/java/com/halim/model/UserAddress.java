package com.halim.model;


import com.halim.configmodel.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserAddress extends BaseObject {

//    @Max(va)
//    @Min(value=100)
//    Integer price;

    @NotBlank(message = "Last is mandatory")
    private Integer id;
    @NotBlank(message = "User Id is mandatory")
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
    @Length(min = 3,max = 30,message = "state must be length of 3 and 30")
    @NotBlank(message = "state is mandatory")
    private String state;
    @NotBlank(message = "townorcity is mandatory")
    @Length(min = 3,max = 30,message = "CityName must be length of 3 and 30")
    private   String townorcity;
    @Digits(message="Zipcode length must be 6", fraction = 0, integer = 6)
    @NotBlank(message = "Zipcode is mandatory")
    private   String postcodezip;
    @NotBlank(message = "Country is mandatory")
    private   String country;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    private   String email;
    @Digits(message="Mobile  Number should contain 12 digits. first two digit country code and next 10 is mobile number", fraction = 0, integer = 12)
    @NotBlank(message = "Mobile number cannot be empty")
    private    String phoneno;


}
