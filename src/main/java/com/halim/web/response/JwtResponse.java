package com.halim.web.response;

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
public class JwtResponse {
    String token;
    private Integer userId;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private UserState role = UserState.ROLE_USER;
    private String number;

}