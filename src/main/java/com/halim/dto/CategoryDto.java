package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    private Integer id;
    @Length(min = 3,message = "category Length should be greater than 3")
    @NotBlank(message = "Category Name is mandatory")
    private String name;



}
