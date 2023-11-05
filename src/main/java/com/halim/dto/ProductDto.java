package com.halim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    @NotBlank(message = "Product Name is mandatory")
    @Length(min = 3,max = 30,message = "Product name length should be in the range of 3 to 30")
    private String name;
    @Length(min = 3,max = 30,message = "Description  length should be in the range of 3 to 30")
    @NotNull(message = "Description is mandatory")
    private String description;

    @Min(value = 1)
  //  @Length(min = 1,max = 30,message = "Quantity  length should be in the range of 1 to 20")
    @NotNull(message = "Quantity is mandatory")
    private int quantity;
//    @Length(min = 3,max = 30,message = "Description  length should be in the range of 3 to 30")
    @Min(value = 100,message = "brandId Must be greater eqaul to 100")
    @NotNull(message = "Brand must be integer and it is  mandatory")
    private Integer brandId;

//    @Length(min = 100,message = "minimum price must be greater than 100")
    @Min(value = 1,message = "price must be greater than 1")
    @NotNull(message = "Price mandatory")
    private Double price;
    @Min(value = 1,message = "sellerDiscount must be greater than 1")
    @NotNull(message = "Discount is mandatory")
    private int discount;
    @Min(value = 1,message = "sellerDiscount must be greater than 1")
    @NotNull(message = "Seller Discount mandatory")
    private int sellerDiscount;
   // @Min(value = 1,message = "Image URL Must have atleast 4 URL")
  //  @NotBlank(message = "Image URL is  mandatory")
    private List<String> imageList;


    @NotNull(message = "Category ID is mandatory")
//    @NotNull("message": "age: positive number value is required")
    @Min(value = 100,message = "categoryId Must be greater eqaul to 100")
    private Integer categoryId;

}

