package com.halim.web.Controller;


import com.halim.dto.BrandDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Brand;
import com.halim.service.BrandService;
import com.halim.web.response.BrandListResponse;
import com.halim.web.response.BrandResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {



    @Autowired
    private BrandService brandService;

//
//    @GetMapping("/{id}")
//    public ResponseEntity<BrandResponse> findBrandById(@PathVariable("id") Integer id) throws RecordNotFound {
//        Brand allBrandById = brandService.findBrandById(id);
//        return addStatusCodeAndMessageBrand(allBrandById,"success");
//    }
    @GetMapping("/name/{name}")
    public ResponseEntity<BrandResponse> findBrandByName(@PathVariable("name") String name){
        Brand allBrandById = brandService.findBrandByName(name);
        return addStatusCodeAndMessageBrand(allBrandById,"success");
    }
//    @GetMapping("/")
//    public ResponseEntity<BrandListResponse> findAllBrand(){
//        List<Brand> allBrand = brandService.findAllBrand();
//        return addStatusCodeAndMessageBrandList(allBrand,"success");
//    }

    private ResponseEntity<BrandListResponse> addStatusCodeAndMessageBrandList(List<Brand> allBrand, String message) {
        BrandListResponse productResponse = new BrandListResponse();
        productResponse.setData(allBrand);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<BrandListResponse>(productResponse,HttpStatus.OK);
    }

    private ResponseEntity<BrandResponse> addStatusCodeAndMessageBrand(Brand Brand, String message) {

        BrandResponse productResponse = new BrandResponse();
        productResponse.setData(Brand);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<BrandResponse>(productResponse,HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<BrandResponse> addBrand(@Valid @RequestBody BrandDto brandRequest) throws RecordNotFound {
        Brand brand1= brandService.addBrand(brandRequest);
        return addStatusCodeAndMessageBrand(brand1,"Brand add Successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<BrandResponse> updateBrand(@Valid @RequestBody Brand Brand) throws RecordNotFound {
        Brand brand1=  brandService.updateBrand(Brand);
        return addStatusCodeAndMessageBrand(brand1,"Brand updated Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BrandResponse> deleteBrandById(@PathVariable("id") Integer id) throws RecordNotFound {
        brandService.deleteBrandById(id);
        return this.addStatusCodeAndMessageBrand(null, "Brand deleted Successfully");
    }
    
}


