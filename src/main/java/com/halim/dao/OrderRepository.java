package com.halim.dao;

import com.halim.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Orders,String> {


    Orders findByIsActiveAndId(boolean isActive, String id);
    List<Orders> findByIsActive(boolean isActive);
    List<Orders> findByIsActiveAndUserIdAndStatus(boolean isActive,String userId,String status);

    //find max
    Orders findTopByOrderByIdDesc();

//	 void createOrder(OrderRequest orderRequest);
//
//   	 void updateOrderStatus(OrderStatusRequest orderstatusRequest);
//
//	 OrderAndSellerDetailResponse verifyOrder(String orderId);

    @Query("{userId :?0}")                                                  //SQL Equivalent : SELECT * FROM BOOK WHERE ID=?
    List<Orders> getAllOrderByUserId(Integer id);

}
