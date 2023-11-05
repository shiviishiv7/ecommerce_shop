package com.halim.dao;

import com.halim.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrandRepository extends MongoRepository<Brand,Integer> {
    List<Brand> findByIsActive(boolean active);

    Brand findByIsActiveAndId(boolean active, Integer integer);

    Brand findByIsActiveAndName(boolean active, String name);

    Brand findTopByOrderByIdDesc();
}