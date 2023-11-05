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
public class PaidDto {
    @Length(min = 5,message = "Order ID length must greater that 5")
    @NotBlank(message = "Order ID is mandatory")
    private String orderId;
    @Length(min = 5,message = "Order ID length must greater that 5")
    @NotBlank(message = "Payment ID mandatory")
    private String paymentId;
    @Length(min = 5,message = "Order ID length must greater that 5")
    @NotBlank(message = "razorpaySignature is mandatory")
    private String razorpaySignature;
}
