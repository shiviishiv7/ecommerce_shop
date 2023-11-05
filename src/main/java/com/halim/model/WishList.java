package com.halim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class WishList {

	@Id
	private String id;
	private String sessionToken; 
	private Set<WishListItem> items = new HashSet<WishListItem>();
	

}
