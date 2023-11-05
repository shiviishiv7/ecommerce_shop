package com.halim.dao;

import com.halim.model.UserAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AddressRepository extends MongoRepository<UserAddress,Integer> {



    UserAddress findByIsActiveAndId(boolean isActive, Integer id);
    List<UserAddress> findByIsActive(boolean isActive);
    List<UserAddress> findByIsActiveAndUserId(boolean isActive,Integer userId);

    //find max
    UserAddress findTopByOrderByIdDesc();

//	void addAddress(AddressRequest address);
//
//	List<AddressRequest> getAddressByUser(Long userid);
//
//	void updateAddressById(AddressRequest address);
//
//	void deleteAddressById(Long id);

    @Query("{userid :?0,isActive:?1}")     //SQL Equivalent : SELECT * FROM BOOK WHERE ID=?
    List<UserAddress> getAllAddressByUserID(Integer id,boolean state);

//    List<UserAddress> findByIsActive(boolean state);


}
