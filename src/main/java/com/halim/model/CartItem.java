package com.halim.model;

import com.halim.configmodel.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CartItem extends BaseObject {

	@Min(value = 100,message = "CartItem Id must be greater or equal to 100")
	@NotNull(message = "CartItem Id  is mandatory")
	private Integer id;
	@Min(value = 100,message = "product Id must be greater or equal to 100")
	@NotNull(message = "Product Id  is mandatory")
	private Integer productId;
	@Min(value = 1,message = "quantity must be greater or equal to 1")
	@NotNull(message = "Miniumum quantity should be 1")
	private int quantity;
	@Min(value = 100,message = "User Id must be greater or equal to 100")
	@NotNull(message = "User ID is mandatory")
	private Integer userId;



}
