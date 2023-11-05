package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterOtp {

    @Length(min = 6,max = 6,message = "OTP length should be in size  of 6")
    @NotBlank(message = "OTP number cannot be empty")
    private String otp;
    @NotBlank(message = "First Name mandatory")
    @Length(min = 3,max = 30,message = "First name length should be in range of 3 to 30")
    private String firstName;
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is  mandatory")
    private String email;
    @Length(min = 6,max = 30,message = "password length should be in range of 6 to 30")
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Length(min = 12,max = 12,message = "number length should be in range of 12 to 12")
    @NotBlank(message = "Mobile number cannot be empty")
    private String number;

}
