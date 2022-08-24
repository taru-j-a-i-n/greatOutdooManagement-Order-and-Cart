package com.greatoutdoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatoutdoor.entity.Cart;
import com.greatoutdoor.exception.CartItemException;
import com.greatoutdoor.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService service;

	public CartController(CartService service) {
		super();
		this.service = service;
	}
	@PostMapping("/add")
	public Cart addToCart(@RequestBody Cart cart) {
		return service.addToCart(cart);
		
	}
	@GetMapping("/viewall")
	public List<Cart> findAllItemOfCart() {
		return service.findAllItemOfCart();
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Cart> findFromCartList
	(@PathVariable("id") Long cid) throws CartItemException {
		return new ResponseEntity<Cart>(service.findFromCartList(cid), HttpStatus.OK);
		// status -- 200
	}
	@PutMapping("/update/{cartId}")
	public ResponseEntity<Cart> 
	updateCartItem(@RequestBody Cart cart,@PathVariable("cartId")
	Long cartId ) throws CartItemException {
		return new 
				ResponseEntity<Cart>
		(service.updateCart(cart, cartId), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAnItemFromCart(@PathVariable("id") Long cid) throws CartItemException {
		service.deleteAnItemFromCart(cid);
		return new ResponseEntity<String>
		("Cart record deleted successfully!", HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll")
	public void deleteAllItemFromCart() {
		service.deleteAllItemFromCart();
	}
}
