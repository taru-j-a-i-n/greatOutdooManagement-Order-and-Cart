package com.greatoutdoor.service;

import java.util.List;

import com.greatoutdoor.entity.Cart;
import com.greatoutdoor.exception.CartItemException;

public interface CartService {
	Cart addToCart(Cart cart);

	public Cart findFromCartList(Long s) throws CartItemException;

	public List<Cart> findAllItemOfCart();

	public String deleteAnItemFromCart(Long c) throws CartItemException;

	public void deleteAllItemFromCart();

	public Cart updateCart(Cart d, Long c) throws CartItemException;

}
