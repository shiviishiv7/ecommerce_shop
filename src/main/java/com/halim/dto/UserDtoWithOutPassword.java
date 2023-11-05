package com.halim.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halim.util.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoWithOutPassword {
    private String token;
    private Integer id;
    private String image;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private UserState role = UserState.ROLE_USER;
    private String number;
}
