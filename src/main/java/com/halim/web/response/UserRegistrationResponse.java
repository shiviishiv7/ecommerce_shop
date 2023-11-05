package com.halim.web.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationResponse {

    private String message;
    private Integer statusCode;
    private long timeStamp;

}
