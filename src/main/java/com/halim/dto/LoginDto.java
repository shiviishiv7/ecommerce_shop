package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class LoginDto {
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Length(min = 15, max = 30, message = "Password length should be in between 15 to 30")
    private String password;
}
