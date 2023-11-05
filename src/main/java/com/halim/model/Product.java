package com.halim.model;


import com.halim.configmodel.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product extends BaseObject {
    @Min(value = 1,message = "Product ID  should be greater equal than 100")
    //  @Length(min = 1,max = 30,message = "Quantity  length should be in the range of 1 to 20")
    @NotNull(message = "Quantity is mandatory")

    private Integer id;
    @NotBlank(message = "Product Name is mandatory")
    @Length(min = 3,max = 30,message = "Product name length should be in the range of 3 to 30")
    private String name;
    @Length(min = 3,max = 30,message = "Description  length should be in the range of 3 to 30")
    @NotBlank(message = "Description is mandatory")
    private String description;

    @Min(value = 1)
    //  @Length(min = 1,max = 30,message = "Quantity  length should be in the range of 1 to 20")
    @NotNull(message = "Quantity is mandatory")
    private int quantity;
    //    @Length(min = 3,max = 30,message = "Description  length should be in the range of 3 to 30")
    @NotNull(message = "Brand mandatory")
    private Integer brandId;

    //    @Length(min = 100,message = "minimum price must be greater than 100")
    @Min(value = 1,message = "price must be greater than 100")
    @NotNull(message = "Price mandatory")
    private Double price;
    @Min(value = 1,message = "sellerDiscount must be greater than 100")
    @NotNull(message = "Discount is mandatory")
    private int discount;
    @Min(value = 1,message = "sellerDiscount must be greater than 100")
    @NotNull(message = "Seller Discount mandatory")
    private int sellerDiscount;
    @Min(value = 1,message = "Image URL Must have atleast 4 URL")
    @NotBlank(message = "Image URL is  mandatory")
    private List<String> imageList;


    @NotBlank(message = "Category ID is mandatory")
    private Integer categoryId;

}

