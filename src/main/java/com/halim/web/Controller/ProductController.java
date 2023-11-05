package com.halim.web.Controller;


import com.halim.dto.ProductDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Product;
import com.halim.service.ProductService;
import com.halim.web.response.ProductListResponse;
import com.halim.web.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    //depreciated
//    @GetMapping("/")
//    public ResponseEntity<ProductListResponse> getAllProduct() {
//        List<Product> productList = productService.getAllProduct();
//        return addStatusCodeAndMessageProductList(productList);
//    }

    private ResponseEntity<ProductListResponse> addStatusCodeAndMessageProductList(List<Product> productList) {

        ProductListResponse productResponse = new ProductListResponse();
        productResponse.setData(productList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductListResponse>(productResponse,HttpStatus.OK);
    }
    private ResponseEntity<ProductResponse> addStatusCodeAndMessageProduct(Product productList, String message) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setData(productList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }

    //depreciated
//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<ProductListResponse> findAllProductByCategory(@PathVariable("categoryId") Integer id) {
//        List<Product> products = productService.findAllProductByCategory(id);
//        return addStatusCodeAndMessageProductList(products);
//    }

//    @GetMapping("/{userid}")
//    public ResponseEntity<ProductResponse> getAllProductByUserId(@PathVariable("userid") Integer userid){
//
//           List<Product> productList =      productService.getAllProductByUserId(userid);
//        return addStatusCodeAndMessage(productList);
//    }


    //depreciated
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id) throws RecordNotFound {
//        Product productById = productService.getProductById(id);
//        return addStatusCodeAndMessageProduct(productById, "success");
//    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductDto productRequest){
        Product product = productService.addProduct(productRequest);
        return addStatusCodeAndMessageProduct(product,"Product added successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody Product product){
        Product product1 = productService.updateProduct(product);
        return addStatusCodeAndMessageProduct(product1, "Product updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable("id") Integer id) throws RecordNotFound {
        boolean productBoolean = productService.deleteProduct(id);
        return addStatusCodeAndMessageProduct(null,"Product deleted Successfully");
    }





}
