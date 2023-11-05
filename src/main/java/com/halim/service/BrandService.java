package com.halim.service;


import com.halim.dao.BrandRepository;
import com.halim.dto.BrandDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Brand;
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
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MongoTemplate mongoTemplate;


    public boolean isexists(Integer id) throws RecordNotFound {
        boolean result = brandRepository.existsById(id);
        if(result)return true;
        throw new RecordNotFound("Brand not found by "+id);
    }

    public Brand findBrandById(Integer id) throws RecordNotFound {
        isexists(id);
        Brand byIsActiveAndId = brandRepository.findByIsActiveAndId(true, id);
        return byIsActiveAndId;
    }

    public List<Brand> findAllBrand() {
        return 	brandRepository.findByIsActive(true);
    }

    public Brand addBrand(BrandDto brandDto) throws RecordNotFound {
        Brand byIsActiveAndName = brandRepository.findByIsActiveAndName(true,brandDto.getName());
        if(byIsActiveAndName!=null){
            throw new RecordNotFound("Brand already exists");
        }
        Brand brand = new Brand();
        brand.setDescription(brandDto.getDescription());
        brand.setName(brandDto.getName());
        Brand topByOrderByIdDesc = brandRepository.findTopByOrderByIdDesc();
        if(topByOrderByIdDesc==null){
            brand.setId(100);
            return brandRepository.save(brand);
        }else {
            brand.setId(100+topByOrderByIdDesc.getId());
            return brandRepository.save(brand);
        }
    }

    public Brand updateBrand(Brand brand) throws RecordNotFound {
        isexists(brand.getId());
        Brand byIsActiveAndName = brandRepository.findByIsActiveAndName(true,brand.getName());
        if(byIsActiveAndName!=null){
            throw new RecordNotFound("Brand already exists");
        }
        return brandRepository.save(brand);
    }

    public boolean deleteBrandById(Integer id) throws RecordNotFound {
        isexists(id);
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
        Update update = new Update();
        update.set("isActive",false);
        update.set("deleted",true);

        UpdateResult updateResult = mongoTemplate.upsert(query,update,Brand.class);
        System.out.println("Brand item deleted"+id+"\t"+updateResult.getMatchedCount());
        return true;

    }

    public Brand findBrandByName(String name) {
        Brand byIsActiveAndId = brandRepository.findByIsActiveAndName(true, name);
        return byIsActiveAndId;
    }
}

