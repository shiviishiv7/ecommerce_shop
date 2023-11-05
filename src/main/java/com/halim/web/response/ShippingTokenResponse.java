package com.halim.web.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingTokenResponse {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private Integer company_id;
    private String created_at;
    private String token;
}
