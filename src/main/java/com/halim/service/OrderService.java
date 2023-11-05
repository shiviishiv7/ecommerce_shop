package com.halim.service;

import com.halim.dao.OrderRepository;
import com.halim.dto.OrdersDto;
import com.halim.dto.PaidDto;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.Orders;
import com.mongodb.client.result.UpdateResult;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CartService cartService;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private RazorpayClient razorpayClient;

	public  void updateOrder(String orderId) throws RecordNotFound {

		Orders byIsActiveAndId = orderRepository.findByIsActiveAndId(true, orderId);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Address ID Does not exists "+orderId);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(orderId));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("status","PAYMENT_FAILED");

		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);
		System.out.println("category item deleted"+orderId+"\t"+updateResult.getMatchedCount());


	}

	public ResponseEntity<Orders> saveOrdersToDB(Orders orderRequest) {
		Orders save = orderRepository.save(orderRequest);
		return ResponseEntity.ok(save);
	}


//	public OrderAndSellerDetailResponse verifyOrder(String orderId) {
//		OrderAndSellerDetailResponse orderDtl = null;
//		return orderDtl;
//	}


	public List<Orders> getAllOrderByUserId(String userid) {
//		Orders orders = new Orders();
//		orders.setUserId(userid);
//		Example<Orders> example = Example.of(orders);
		List<Orders> allOrder = orderRepository.findByIsActiveAndUserIdAndStatus(true,userid,"PAYMENT_RECEIVED");
		return allOrder;
	}

	public Orders getOrderById(String id) throws Exception {
		Orders orders = orderRepository.findByIsActiveAndId(true,id);
		if(orders==null){
			throw new RecordNotFound("Address Not found By Id "+id);
		}else return orders;

	}

	public Orders addOrder(@RequestBody OrdersDto orderRequest) throws RazorpayException {

		Orders orders = this.modelMapper.map(orderRequest, Orders.class);
//
//		Orders topByOrderByIdDesc = orderRepository.findTopByOrderByIdDesc();
//
//		if(topByOrderByIdDesc==null){
//			map.setId(100);
//		}else map.setId(100+topByOrderByIdDesc.getId());

		JSONObject options = new JSONObject();
		options.put("amount", orders.getAmount()*100);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		Order order = razorpayClient.Orders.create(options);
		System.out.println(order);
		orders.setId(order.get("id"));
//		Orders map = new Orders(max+100,orderRequest.getAmount(),orderRequest.getOrderState(),orderRequest.getUserId(),
//				orderRequest.getAddressId(),orderRequest.getSellerAmount(),orderRequest.getOrderProductCountMapList());
		Orders save = orderRepository.save(orders);
		return save;
	}

	public boolean deleteOrderById(String id) throws RecordNotFound {
		Orders byIsActiveAndId = orderRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Order ID Does not exists "+id);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("isActive",false);
		update.set("deleted",true);

		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);
		System.out.println("category item deleted"+id+"\t"+updateResult.getMatchedCount());
		return true;
	}

	public boolean updatePaymentorder(PaidDto paidDto) throws RecordNotFound {
		String id = paidDto.getOrderId();
		Orders byIsActiveAndId = orderRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("Order ID Does not exists "+id);
		}


		//update cart because order is placed
		byIsActiveAndId.getCartItemList().forEach(integer ->
		{
			try {
				cartService.deleteCartItem(integer);
			} catch (RecordNotFound e) {
				throw new RuntimeException(e);
			}
		});


		Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("razorpaySignature",paidDto.getRazorpaySignature());
		update.set("paymentId",paidDto.getPaymentId());
		update.set("status","PAYMENT_RECEIVED");

		UpdateResult updateResult = mongoTemplate.upsert(query,update,Orders.class);



		return true;



	}
}
