package com.halim.web.Controller;


import com.halim.dto.CategoryDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Category;
import com.halim.service.CategoryService;
import com.halim.web.response.CategoryResponse;
import com.halim.web.response.CategorytListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {



    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable("id") Integer id){
        Category allCategoryById = categoryService.findCategoryById(id);
        return addStatusCodeAndMessageCategory(allCategoryById,"success");
    }

    @GetMapping("/")
    public ResponseEntity<CategorytListResponse> findAllCategory(){
        List<Category> allCategory = categoryService.findAllCategory();
        return addStatusCodeAndMessageCategoryList(allCategory,"success");
    }

    private ResponseEntity<CategorytListResponse> addStatusCodeAndMessageCategoryList(List<Category> allCategory, String message) {
        CategorytListResponse productResponse = new CategorytListResponse();
        productResponse.setData(allCategory);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CategorytListResponse>(productResponse,HttpStatus.OK);
    }

    private ResponseEntity<CategoryResponse> addStatusCodeAndMessageCategory(Category category, String message) {

        CategoryResponse productResponse = new CategoryResponse();
        productResponse.setData(category);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CategoryResponse>(productResponse,HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryDto categoryRequest) throws RecordNotFound {
       Category category1= categoryService.addCategory(categoryRequest.getName());
        return addStatusCodeAndMessageCategory(category1,"Category add Successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody Category category) throws RecordNotFound {
       Category category1=  categoryService.updateCategory(category);
       return addStatusCodeAndMessageCategory(category,"Category updated Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> deleteCategoryById(@PathVariable("id") Integer id) throws RecordNotFound {
        this.categoryService.deleteCategoryById(id);
        return this.addStatusCodeAndMessageCategory(null, "Category deleted Successfully");
    }






}

