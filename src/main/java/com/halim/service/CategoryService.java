package com.halim.service;

import com.halim.dao.CategoryRepository;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Category;
import com.mongodb.client.result.UpdateResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MongoTemplate mongoTemplate;


	public Category findCategoryById(Integer id) {
		Category byIsActiveAndId = categoryRepository.findByIsActiveAndId(true, id);
		return byIsActiveAndId;
	}

	public List<Category> findAllCategory() {
		return 	categoryRepository.findByIsActive(true);
	}

	public Category addCategory(String name) throws RecordNotFound {
		Category byIsActiveAndName = categoryRepository.findByIsActiveAndName(true,name);
		if(byIsActiveAndName!=null){
			throw new RecordNotFound("Category already exists");
		}
		Category topByOrderByIdDesc = categoryRepository.findTopByOrderByIdDesc();
		if(topByOrderByIdDesc==null){
			Category category = new Category();
			category.setName(name);
			category.setId(100);
			return categoryRepository.save(category);

		}else {
			Category category = new Category();
			category.setName(name);
			category.setId(100+topByOrderByIdDesc.getId());
			return categoryRepository.save(category);

		}
	}

	public Category updateCategory(Category category) throws RecordNotFound {
		Category byIsActiveAndName = categoryRepository.findByIsActiveAndName(true,category.getName());
		if(byIsActiveAndName!=null){
			throw new RecordNotFound("Category already exists");
		}
		return categoryRepository.save(category);
	}

	public boolean deleteCategoryById(Integer id) throws RecordNotFound {
		Category byIsActiveAndId = categoryRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Category not exists by Id "+id);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("isActive",false);
		update.set("deleted",true);

		UpdateResult updateResult = mongoTemplate.upsert(query,update,Category.class);
		System.out.println("category item deleted"+id+"\t"+updateResult.getMatchedCount());
		return true;

	}

}

