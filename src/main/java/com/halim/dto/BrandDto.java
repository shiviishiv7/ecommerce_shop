package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Integer id;
    @Length(min = 3,message = "Category  name Length should be greater than 3")
    @NotBlank(message = " Name is mandatory")
    private String name;

    @Length(min = 3,message = "description Length should be greater than 3")
    @NotBlank(message = "description Name is mandatory")
    private String description;
}
