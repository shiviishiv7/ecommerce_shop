package com.halim.web.Controller;


import com.halim.dto.OrderStatusChange;
import com.halim.dto.OrdersDto;
import com.halim.dto.PaidDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Orders;
import com.halim.service.OrderService;
import com.halim.web.response.OrderListResponse;
import com.halim.web.response.OrderResponse;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrdersController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<OrderListResponse> getAllOrderByUserId(@PathVariable("userId") String userid){
        List<Orders> ordersList =  orderService.getAllOrderByUserId(userid);
        return addStatusCodeAndMessageOrderList(ordersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String id) throws Exception {
        Orders orders = orderService.getOrderById(id);
        return addStatusCodeAndMessageOrder(orders,"success");
    }

    @PostMapping("/success")
    public ResponseEntity<OrderResponse> orderPaid( @Valid @RequestBody PaidDto paidDto) throws RecordNotFound {
        orderService.updatePaymentorder(paidDto);
        return addStatusCodeAndMessageOrder(null,"Update Payment Successfull");
    }


    @PostMapping("/add")
    public ResponseEntity<OrderResponse> addOrder(@Valid @RequestBody OrdersDto orderRequest) throws RazorpayException {
             Orders orders= orderService.addOrder(orderRequest);
             return addStatusCodeAndMessageOrder(orders,"order successfully created");

    }
    private ResponseEntity<OrderListResponse> addStatusCodeAndMessageOrderList(List<Orders> ordersList) {

        OrderListResponse productResponse = new OrderListResponse();
        productResponse.setData(ordersList);
        productResponse.setMessage("success");
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<OrderListResponse>(productResponse,HttpStatus.OK);
    }
    private ResponseEntity<OrderResponse> addStatusCodeAndMessageOrder(Orders orders, String message) {

        OrderResponse productResponse = new OrderResponse();
        productResponse.setData(orders);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<OrderResponse>(productResponse,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @RequestBody OrderStatusChange orderRequest) throws RecordNotFound {
        orderService.updateOrder(orderRequest.getOrderId());
        return addStatusCodeAndMessageOrder(null,"Successfully updated Order");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrderById(@PathVariable("id") String id) throws RecordNotFound {
         orderService.deleteOrderById(id);
        return addStatusCodeAndMessageOrder(null,"Successfully deleted order");
    }




}

