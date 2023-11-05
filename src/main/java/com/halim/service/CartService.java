package com.halim.service;

import com.halim.dao.CartRepository;
import com.halim.dao.ProductRepository;
import com.halim.dto.CartItemDto;
import com.halim.exceptionsHandler.RecordFound;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.CartItem;
import com.halim.model.Product;
import com.halim.web.response.CartItemResponse;
import com.mongodb.client.result.UpdateResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

	@Autowired
    private CartRepository cartRepository;


	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MongoTemplate mongoTemplate;

	public CartItemResponse addToCart(CartItemDto cartRequest) throws RecordFound, RecordNotFound {
		CartItem cartItem = cartRepository.findByIsActiveAndUserIdAndProductId(true, cartRequest.getUserId(), cartRequest.getProductId());
		if(cartItem!=null){
			throw new RecordFound("Product already exists in the cart ");
		}
		CartItem map = this.modelMapper.map(cartRequest, CartItem.class);
		CartItem maxCart = cartRepository.findTopByOrderByIdDesc();
		if(maxCart==null)
			map.setId(100);
		else map.setId(maxCart.getId()+100);
		CartItem save = cartRepository.save(map);
		CartItemResponse cartItemById = getCartItemById(save.getId());
		return cartItemById;
	}

	public List<CartItemResponse> findByIsActiveAndUserId(Integer userid) {
		List<CartItem> all = cartRepository.findByIsActiveAndUserId(true,userid);
		List<CartItemResponse> list = new ArrayList<>();
		all.forEach(cartItem -> {
			Product product = productRepository.findByIsActiveAndId(true, cartItem.getProductId());
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setUserId(cartItem.getUserId());
			cartItemResponse.setQuantity(cartItem.getQuantity());
			cartItemResponse.setProduct(product);
			cartItemResponse.setId(cartItem.getId());
			cartItemResponse.setProductId(cartItem.getProductId());
			list.add(cartItemResponse);

		});
		return list;
	}



	public boolean deleteCartItem(Integer id) throws RecordNotFound {
		CartItem byIsActiveAndId = cartRepository.findByIsActiveAndId(true, id);
		if(byIsActiveAndId==null){
			throw new RecordNotFound("CartItem ID Does not exists "+id);
		}
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("isActive",false);
		update.set("deleted",true);

		UpdateResult updateResult = mongoTemplate.upsert(query,update,CartItem.class);
//		System.out.println("category item deleted"+id+"\t"+updateResult.getMatchedCount());
		return true;
	}

	public CartItemResponse getCartItemById(Integer id) throws RecordNotFound {
		CartItem cartItem = cartRepository.findByIsActiveAndId(true, id);
		Product product = productRepository.findByIsActiveAndId(true, cartItem.getProductId());


		if(cartItem ==null){
			throw new RecordNotFound("Product not found By "+id);
		}
		CartItemResponse cartItemResponse = new CartItemResponse();
		cartItemResponse.setProduct(product);
		cartItemResponse.setUserId(id);
		cartItemResponse.setQuantity(cartItem.getQuantity());
		cartItemResponse.setId(cartItem.getId());
		cartItemResponse.setProductId(cartItem.getProductId());
		return cartItemResponse;
	}

	public boolean updateCartItem(CartItem cartItem) throws RecordNotFound {
		CartItem cart = cartRepository.findByIsActiveAndUserIdAndProductId(true, cartItem.getUserId(), cartItem.getProductId());
		if(cart==null){
			throw new RecordNotFound("CaryItem not exits in the user cart by ID "+cartItem.getId());
		}

		Query query = new Query().addCriteria(Criteria.where("id").is(cartItem.getId()));
//		Update updateDefination = new Update().set("isActive",false);
		Update update = new Update();
		update.set("quantity",cartItem.getQuantity());

		UpdateResult updateResult = mongoTemplate.upsert(query,update,CartItem.class);
		return true;
	}
}
