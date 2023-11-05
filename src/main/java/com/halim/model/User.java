package com.halim.model;


import com.halim.configmodel.BaseObject;
import com.halim.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class User extends BaseObject {
	private Integer id;
	private String image;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@Enumerated(EnumType.ORDINAL)
	private UserState role = UserState.ROLE_USER;
	private String number;

}



//public class User {
//
//	private int id;
//	private String image;
//	private String name;
//	private String email;
//	private String password;
//	private String role;
//	private String number;
//	private boolean is_admin;
//
//	public boolean isIs_admin() {
//		return is_admin;
//	}
//
//	public void setIs_admin(boolean is_admin) {
//		this.is_admin = is_admin;
//	}
//
//	public User() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getNumber() {
//		return number;
//	}
//
//	public void setNumber(String number) {
//		this.number = number;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}

//}
