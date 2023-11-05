package com.halim.dao;

import com.halim.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartRepository extends MongoRepository<CartItem,Integer> {


    CartItem findByIsActiveAndId(boolean isActive,Integer id);
    CartItem findByIsActiveAndUserIdAndProductId(boolean isActive,Integer userId,Integer id);
    List<CartItem> findByIsActive(boolean isActive);
    List<CartItem> findByIsActiveAndUserId(boolean isActive,Integer userId);

    //find max
    CartItem findTopByOrderByIdDesc();

//	void addToCart(CartRequest cartRequest);
//
//	List<ProductAndQuantity> getCartItems(Long id);
//
//	void emptyCartById(Long userId);
//
//	void deleteCartByuserIdAndProductId(Long userId, Long productId);

    @Query("{userId :?0}")    //SQL Equivalent : SELECT * FROM BOOK WHERE ID=?
    List<CartItem> getAllCartItemByUserId(Integer id);


}
