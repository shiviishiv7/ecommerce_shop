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
public class OtpDto {
    @Length(min = 12,max = 12,message = "number length should be in size  of 12")
    @NotBlank(message = "Mobile number cannot be empty")
    private String number;
}
