package com.halim.dao;


import com.halim.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,Integer> {

    List<Product> findByIsActiveAndCategoryId(boolean isActive,Integer categoryId);
    //find max
    Product findTopByOrderByIdDesc();
    List<Product> findByIsActive(boolean isActive);
    Product findByIsActiveAndId(boolean isActive,Integer id);
}
