package com.halim.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.halim.model.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BrandResponse {
    private String message;
    private Integer statusCode;
    private long timeStamp;
    private Brand data;
}