package com.greatoutdoor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatoutdoor.entity.Order;
import com.greatoutdoor.exception.OrderException;
import com.greatoutdoor.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo repo;

	@Override
	// add order
	public Order placeOrder(Order order) {
		return repo.save(order);
	}

	@Override
	public List<Order> findAllOrders() {
		return repo.findAll();
	}

	@Override
	public Order findOrderById(long oId) throws OrderException {
		return repo.findById(oId).orElseThrow(() -> new OrderException("There is no such order id exist"));
	}

	@Override
	public String cancelAllOrders() {
		repo.deleteAll();
		return "all orders deleted successfuly";
	}

	@Override
	public String cancelOrderById(long oId) throws OrderException {
		if (repo.existsById(oId)) {
			repo.deleteById(oId);
			return "delete succesful";
		} else {
			throw new OrderException("Order Id is Not found");
		}
	}

	@Override
	public Order updateDate(Long oId, Order od) throws OrderException {
		return repo.save(od);
	}

}
