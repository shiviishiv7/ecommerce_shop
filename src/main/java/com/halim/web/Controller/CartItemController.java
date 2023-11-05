package com.halim.web.Controller;


import com.halim.dto.CartItemDto;
import com.halim.exceptionsHandler.RecordFound;
import com.halim.exceptionsHandler.RecordNotFound;
import com.halim.model.CartItem;
import com.halim.service.CartService;
import com.halim.web.response.CartItemListResponse;
import com.halim.web.response.CartItemResponse;
import com.halim.web.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-item")
public class CartItemController {

    @Autowired
    private CartService cartService;

    //not working
    @GetMapping("/user/{userid}")
    public ResponseEntity<CartItemListResponse> getAllCartItemByUserId(@PathVariable("userid") Integer userid){
        List<CartItemResponse> cartItemList = cartService.findByIsActiveAndUserId(userid);
        return addStatusCodeAndMessageCartItemList(cartItemList,"success");
    }

    private ResponseEntity<CartItemListResponse> addStatusCodeAndMessageCartItemList(List<CartItemResponse> cartItemList, String message) {
        CartItemListResponse productResponse = new CartItemListResponse();
        productResponse.setData(cartItemList);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CartItemListResponse>(productResponse,HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CartResponse> getCartItemById(@PathVariable("id") Integer id) throws RecordNotFound {
//        CartItemResponse cartItemById = cartService.getCartItemById(id);
//        return addStatusCodeAndMessageCartItemResonse(cartItemById,"success");
//    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addCartItem(@Valid @RequestBody CartItemDto cartRequest) throws RecordFound, RecordNotFound {
        CartItemResponse cartItem = cartService.addToCart(cartRequest);
        return addStatusCodeAndMessageCartItemResonse(cartItem,"Item add to Cart Successfully");
    }

    private ResponseEntity<CartResponse> addStatusCodeAndMessageCartItemResonse(CartItemResponse cartItem, String message) {
        CartResponse productResponse = new CartResponse();
        productResponse.setData(cartItem);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CartResponse>(productResponse,HttpStatus.OK);
    }

    private ResponseEntity<CartResponse> addStatusCodeAndMessageCartItem(CartItem cartItem, String message) {
        CartResponse<CartItem> productResponse = new CartResponse();
        productResponse.setData(cartItem);
        productResponse.setMessage(message);
        productResponse.setTimeStamp(System.currentTimeMillis());
        productResponse.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<CartResponse>(productResponse,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<CartResponse> updateCartItem(@Valid @RequestBody CartItem cartItem) throws RecordNotFound {
         cartService.updateCartItem(cartItem);
        return addStatusCodeAndMessageCartItem(null,"Item update to Cart Successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CartResponse> deleteCartItemById(@PathVariable("id") Integer id) throws RecordNotFound {
         cartService.deleteCartItem(id);
        return   addStatusCodeAndMessageCartItem(null,"Item Delete to Cart Successfully");
    }

}

