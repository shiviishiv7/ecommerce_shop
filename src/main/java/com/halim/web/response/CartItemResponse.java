package com.halim.web.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.halim.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemResponse  {
    private Integer id;
    private Product product ;
    private Integer productId;
    private int quantity;
    private Integer userId;

}

