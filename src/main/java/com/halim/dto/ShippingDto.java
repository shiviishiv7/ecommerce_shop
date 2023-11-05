package com.halim.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingDto {
    private String   order_id;
    private String   order_date;
    private String   pickup_location;


    //custom website , amazone, ebay
    private String  channel_id;
    private String  comment;
//    private BillingUserAddress billingUserAddress;
private String billing_customer_name;
    private String billing_last_name;
    private String       billing_address;
    private String       billing_address_2;
    private String         billing_city;
    private String         billing_pincode;
    private String          billing_state;
    private String         billing_country;
    private String           billing_email;
    private String           billing_phone;
    private boolean shipping_is_billing= true;
//    private ShippingUserAddress shippingUserAddress;
    private String     shipping_customer_name;
    private String     shipping_last_name;
    private String     shipping_address;
    private String      shipping_address_2;
    private String      shipping_city;
    private String      shipping_pincode;
    private String      shipping_country;
    private String      shipping_state;
    private String      shipping_email;
    private String      shipping_phone;
    private List<ShippingOrderDto> order_items;
    private String payment_method;
    private Double shipping_charges;
    private Double giftwrap_charges;
    private Double transaction_charges;
    private Double          total_discount ;
    private Double      sub_total ;
    private Double      length ;
    private Double      breadth ;
    private Double       height ;
    private Double      weight ;
}
