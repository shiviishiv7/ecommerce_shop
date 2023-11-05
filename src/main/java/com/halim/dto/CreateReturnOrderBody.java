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
public class CreateReturnOrderBody {

   private String order_id;
   private String order_date;
   private String channel_id;
   private String pickup_customer_name;
    private String         pickup_last_name;
    private String        company_name;
    private String        pickup_address;
    private String        pickup_address_2;
    private String        pickup_city;
    private String        pickup_state;
    private String        pickup_country;
    private String        pickup_pincode;
    private String        pickup_email;
    private String        pickup_phone;
    private String        pickup_isd_code;
    private String        shipping_customer_name;
    private String        shipping_last_name;
    private String        shipping_address;
    private String        shipping_address_2;
    private String        shipping_city;
    private String        shipping_country;
    private String        shipping_pincode;
    private String        shipping_state;
    private String        shipping_email;
    private String        shipping_isd_code;
    private Long        shipping_phone;
    private List<ShippingOrderDto> order_items;
   private String     payment_method;
    private String         total_discount;
    private Long        sub_total;
    private Long        length;
    private Long        breadth;
    private Long        height;
    private Long        weight;
}
