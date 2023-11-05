package com.halim.dto;

import com.halim.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Integer id;
    @URL(message = "Profile url is not valid")
    @NotBlank(message = "User profile image cannot be empty")
    private String image;

    @NotBlank(message = "User last name cannot empty")
    @Length(min = 3,max = 30,message = "lastname length should be in range of 3 to 30")
    private String lastName;


    @Enumerated(EnumType.ORDINAL)
    private UserState role = UserState.ROLE_USER;

    @Length(min = 6,max = 6,message = "OTP length should be in range of 6 to 6")
    @NotBlank(message = "OTP is  mandatory")
    private String otp;
    @NotBlank(message = "First Name mandatory")
    @Length(min = 3,max = 30,message = "firstname length should be in range of 3 to 30")
    private String firstName;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is  mandatory")
    private String email;
    @Length(min = 3,max = 30,message = "password length should be in range of 3 to 30")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Length(min = 12,max = 12,message = "number length should be in size  of 12")
    @NotBlank(message = "Password cannot be blank")
    private String number;
}