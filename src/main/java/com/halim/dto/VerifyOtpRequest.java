package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyOtpRequest {

    //we are appending + at the time of twillio calling tell frontend not to add + from frontend
    @Length(min = 12,max = 12,message = "number length should be in size  of 12")
    @NotBlank(message = "Mobile number cannot be empty")
    private String number;

    @Length(min = 6,max = 6,message = "number length should be in size  of 6")
    @NotBlank(message = "OTP number cannot be empty")
    private String otp;

}