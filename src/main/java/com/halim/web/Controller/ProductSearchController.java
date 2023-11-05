package com.halim.web.Controller;

import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Product;
import com.halim.service.ProductService;
import com.halim.web.response.ProductListResponse;
import com.halim.web.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<ProductListResponse> search(@RequestParam(name = "value", required = false) String value,
                                @RequestParam(name = "topseller", required = false) boolean topseller,
                                @RequestParam(name = "minprice", required = false) Integer minprice,
                                @RequestParam(name = "maxprice", required = false) Integer maxprice,
                                @RequestParam(name = "categoryId", required = false) Integer categoryId,
                                @RequestParam(name = "brandId", required = false) Integer brandId) {
        List<Product> products = productService.searchProduct(value, minprice, maxprice, topseller,categoryId,brandId);
        return addStatusCodeAndMessageProductList(products);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ProductListResponse> findAllProductByCategory(@PathVariable("categoryId") Integer id) {
        List<Product> products = productService.findAllProductByCategory(id);
        return addStatusCodeAndMessageProductList(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id) throws RecordNotFound {
        Product productById = productService.getProductById(id);
        return addStatusCodeAndMessageProduct(productById, "success");
    }
    private ResponseEntity<ProductResponse> addStatusCodeAndMessageProduct(Product productList, String message) {

        ProductResponse productResponse = new ProductResponse();
        productResponse.setData(productList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }



    private ResponseEntity<ProductListResponse> addStatusCodeAndMessageProductList(List<Product> productList) {

        ProductListResponse productResponse = new ProductListResponse();
        productResponse.setData(productList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ProductListResponse>(productResponse,HttpStatus.OK);
    }

}
