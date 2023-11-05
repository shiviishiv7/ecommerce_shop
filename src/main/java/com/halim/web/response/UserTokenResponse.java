package com.halim.web.response;


import com.halim.dto.UserDtoWithOutPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserTokenResponse {
    private String message;
    private Integer statusCode;
    private long timeStamp;
    private UserDtoWithOutPassword data;
}
