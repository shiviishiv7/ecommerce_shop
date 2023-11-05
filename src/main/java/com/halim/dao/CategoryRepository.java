package com.halim.dao;

import com.halim.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category,Integer> {

	Category findByIsActiveAndName(boolean isActive,String name);

	Category findTopByOrderByIdDesc();
	List<Category> findByIsActive(boolean isActive);
	Category findByIsActiveAndId(boolean isActive,Integer id);

//	Category getCategoryById(Long id);
//
//	void saveCategory(Category category);
//
//	void updateCategoryById(Category category);
//
//	void deleteCategory(Long id);

}
