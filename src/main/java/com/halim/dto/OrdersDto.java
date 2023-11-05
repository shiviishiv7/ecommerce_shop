package com.halim.dto;

import com.halim.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto  {
    private String id;
    @NotNull(message = "amount cannot be empty")
    double amount;
    @Min(value = 100,message = "User Id must be greater than 99")
    @NotNull(message = "User ID is mandatory")
    Integer userId;
    @Min(value = 100,message = "Address ID must be greater than 99")
    @NotNull(message = "Address ID is mandatory")
    Integer addressId;
    @Min(value = 1)
    @NotNull(message = "Seller Amount is mandatory")
    double sellerAmount;
//    @Length(min = 1,max = 5,message = "number length should be in size  of 5")
//    @NotBlank(message = "CartItem list  is mandatory")
    List<Integer> cartItemList;
    @Enumerated(EnumType.ORDINAL)
    private OrderState status = OrderState.CREATED;
//    private String offerId;


}
