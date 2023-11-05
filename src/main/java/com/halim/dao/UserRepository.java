package com.halim.dao;

import com.halim.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User,Integer> {
  User findByEmail(String email);
  User findByNumber(String number);

  // Find MAX User
    User findTopByOrderByIdDesc();
 }

//
//public interface UserRepository {
//
//	User getUserByUserName(String username);
//
//	void save(User user);
//
//	void makeSeller(MakeSellerRequest makeSellerRequest);
//
//	String getEmailByNumber(String username);
//
//	User getuserByNumber(String username);
//
//	User getUser(String userId);
//
//}
