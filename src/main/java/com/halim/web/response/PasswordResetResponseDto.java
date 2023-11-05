package com.halim.web.response;

import com.halim.dao.OtpStatus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PasswordResetResponseDto {


    public PasswordResetResponseDto(OtpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	public PasswordResetResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	private OtpStatus status;
    private String message;
    
	public OtpStatus getStatus() {
		return status;
	}
	public void setStatus(OtpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
