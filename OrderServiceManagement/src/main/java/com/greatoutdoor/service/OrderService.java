package com.greatoutdoor.service;

import java.util.List;

import com.greatoutdoor.entity.Order;
import com.greatoutdoor.exception.OrderException;

public interface OrderService {
	Order placeOrder(Order order);
	public List<Order> findAllOrders();// all order placed

	public Order findOrderById(long oId) throws OrderException;

	public String cancelAllOrders() throws OrderException;

	public String cancelOrderById(long oId) throws OrderException, OrderException;

	public Order updateDate(Long  oId, Order od) throws OrderException;

}
