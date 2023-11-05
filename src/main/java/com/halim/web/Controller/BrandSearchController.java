package com.halim.web.Controller;


import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Brand;
import com.halim.service.BrandService;
import com.halim.web.response.BrandListResponse;
import com.halim.web.response.BrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/brand")
public class BrandSearchController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<BrandListResponse> findAllBrand(){
        List<Brand> allBrand = brandService.findAllBrand();
        return addStatusCodeAndMessageBrandList(allBrand,"success");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> findBrandById(@PathVariable("id") Integer id) throws RecordNotFound {
        Brand allBrandById = brandService.findBrandById(id);
        return addStatusCodeAndMessageBrand(allBrandById,"success");
    }

    private ResponseEntity<BrandResponse> addStatusCodeAndMessageBrand(Brand Brand, String message) {

        BrandResponse productResponse = new BrandResponse();
        productResponse.setData(Brand);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<BrandResponse>(productResponse,HttpStatus.OK);
    }

    private ResponseEntity<BrandListResponse> addStatusCodeAndMessageBrandList(List<Brand> allBrand, String message) {
        BrandListResponse productResponse = new BrandListResponse();
        productResponse.setData(allBrand);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<BrandListResponse>(productResponse,HttpStatus.OK);
    }

}
