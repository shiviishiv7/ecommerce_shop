package com.halim.web.Controller;

import com.halim.model.Category;
import com.halim.service.CategoryService;
import com.halim.web.response.CategoryResponse;
import com.halim.web.response.CategorytListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/category")
public class CategorySearchController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<CategorytListResponse> findAllCategory(){
        List<Category> allCategory = categoryService.findAllCategory();
        return addStatusCodeAndMessageCategoryList(allCategory,"success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findCategoryById(@PathVariable("id") Integer id){
        Category allCategoryById = categoryService.findCategoryById(id);
        return addStatusCodeAndMessageCategory(allCategoryById,"success");
    }

    private ResponseEntity<CategoryResponse> addStatusCodeAndMessageCategory(Category category, String message) {

        CategoryResponse productResponse = new CategoryResponse();
        productResponse.setData(category);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CategoryResponse>(productResponse,HttpStatus.OK);
    }

    private ResponseEntity<CategorytListResponse> addStatusCodeAndMessageCategoryList(List<Category> allCategory, String message) {
        CategorytListResponse productResponse = new CategorytListResponse();
        productResponse.setData(allCategory);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CategorytListResponse>(productResponse,HttpStatus.OK);
    }

}
