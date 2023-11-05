package com.halim.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingUserAddress {
    
   private boolean shipping_is_billing;
private String                        shipping_customer_name;
    private String                         shipping_last_name;
    private String     shipping_address;
    private String      shipping_address_2;
    private String      shipping_city;
    private String      shipping_pincode;
    private String      shipping_country;
    private String      shipping_state;
    private String      shipping_email;
    private String      shipping_phone;
}
