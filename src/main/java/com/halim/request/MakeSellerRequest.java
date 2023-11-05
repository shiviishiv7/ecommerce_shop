package com.halim.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MakeSellerRequest {
	String username;
	Boolean isSeller;
	

}
