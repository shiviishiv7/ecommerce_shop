package com.halim.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingOrderDto {
    private String name;
    private String sku;
    private Integer units;
    private String selling_price;
    private String discount;
    private String tax;
    private Integer hsn;

}
