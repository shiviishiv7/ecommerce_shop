package com.halim.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.halim.configmodel.BaseObject;
import com.halim.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Orders extends BaseObject {

	private String id;
	 double amount;

	 Integer userId;
	 Integer addressId;
	 double sellerAmount;
    List<Integer> cartItemList;
	@Enumerated(EnumType.ORDINAL)
	private OrderState status = OrderState.CREATED;
	private String offerId;
	private String paymentId;

	private String razorpaySignature;

}
