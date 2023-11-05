package com.halim.web.response;

import com.halim.model.Orders;
import com.halim.model.ProductAndCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderAndSellerDetailResponse extends Orders {
	private String name;
	private String email;
	private String number;
	private boolean is_seller;
	


	List<ProductAndCount> products;
}
