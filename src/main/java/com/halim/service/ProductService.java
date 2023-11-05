package com.halim.service;

import com.halim.dao.ProductRepository;
import com.halim.dto.ProductDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Product;
import com.mongodb.client.result.UpdateResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Integer id) throws RecordNotFound {

        Product byIsActiveAndId = productRepository.findByIsActiveAndId(true, id);
        if(byIsActiveAndId ==null){
            throw new RecordNotFound("product not found By "+id);
        }else return byIsActiveAndId;

    }
    public void updateProductById(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProduct() {	return productRepository.findByIsActive(true);}

    public Product addProduct(ProductDto productRequest) {
        Product userList = productRepository.findTopByOrderByIdDesc();
        Product product = this.modelMapper.map(productRequest, Product.class);
        if(userList!=null)
        product.setId(userList.getId()+100);
        else product.setId(100);
        Product save = productRepository.save(product);
        return save;
    }

    public Product updateProduct(Product product) {

        Product save = productRepository.save(product);
        return save;
    }

    public boolean deleteProduct(Integer id) throws RecordNotFound {
        Product byIsActiveAndId = productRepository.findByIsActiveAndId(true, id);
        if(byIsActiveAndId==null){
            throw new RecordNotFound("Product not exists by Id "+id);
        }
        //		productRepository.deleteById(id);
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
        Update update = new Update();
        update.set("isActive",false);
        update.set("deleted",true);

        UpdateResult updateResult = mongoTemplate.upsert(query,update,Product.class);
        System.out.println(updateResult.getMatchedCount());
        Map<String ,String> map = new HashMap<>();
        map.put("message","Item Deleted");
        return true;
    }

    public List<Product> searchProduct(String value, Integer minprice, Integer maxprice, boolean topseller, Integer categoryId, Integer brandId) {

        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        if (value != null && !value.isBlank()) {

            criteriaList.add(Criteria.where("name").regex(value));
            criteriaList.add(Criteria.where("description").regex(value));
        }

        if(minprice!=null && maxprice!=null &&minprice>0 &&maxprice<10000){
            criteriaList.add(Criteria.where("price").lte(maxprice).gte(minprice));

        }else if (maxprice != null && maxprice<10000) {
            criteriaList.add(Criteria.where("price").lte(maxprice));
        }else if (minprice != null && minprice>0) {
            criteriaList.add(Criteria.where("price").gte(minprice));
        }

        if (categoryId != null && categoryId>0) {
            criteriaList.add(Criteria.where("categoryId").is(categoryId));
        }
        if (brandId != null && brandId>0) {
            criteriaList.add(Criteria.where("brandId").is(brandId));
        }

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().orOperator(criteriaList.toArray(new Criteria[0])));
        }
        List<Product> productList = mongoTemplate.find(query, Product.class);
        return productList;

    }

    public List<Product> findAllProductByCategory(Integer id) {
        return productRepository.findByIsActiveAndCategoryId(true,id);
    }
}

