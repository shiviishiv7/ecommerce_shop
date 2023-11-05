package com.halim.request;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document

public class PasswordResetRequestDto {

	private String phoneNumber;// destination
	private String userName;
	private String oneTimePassword;

}
