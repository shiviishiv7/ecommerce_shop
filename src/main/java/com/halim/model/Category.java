package com.halim.model;

import com.halim.configmodel.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Category extends BaseObject {
	private Integer id;
	private String name;

}
