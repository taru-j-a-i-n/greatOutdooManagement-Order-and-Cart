package com.greatoutdoor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.greatoutdoor.common.Cart;
import com.greatoutdoor.common.RequestResponse;
import com.greatoutdoor.entity.Order;
import com.greatoutdoor.exception.OrderException;
import com.greatoutdoor.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService service;
	@Autowired
	private RestTemplate restTemplate;

	public OrderController(OrderService service) {
		super();
		this.service = service;
	}

	@PostMapping("/add")
	public RequestResponse placeOrder(@RequestBody RequestResponse rr) {
		Cart c = new Cart();// constructor calling
		c = restTemplate.postForObject("http://localhost:8888/cart/add", rr.getCartId(), Cart.class);
		Order o = new Order();// constructor of 1st service
		o.setCartId(c.getCartId());// linking done
		o.setDeliveryDate(rr.getDeliveryDate());
		o.setDispatchDate(rr.getDispatchDate());
		o.setDispatchStatus(rr.getDispatchStatus());
		o.setOrderQuantity(rr.getOrderQuantity());
		o.setProductId(rr.getProductId());
		o.setProducts(rr.getProducts());
		o = service.placeOrder(o);
		return new RequestResponse(o.getOrderId(), o.getProductId(), o.getProducts(), c, o.getOrderQuantity(),
				o.getDispatchStatus(), o.getDispatchDate(), o.getDeliveryDate());
	}

	// find all orders
	@GetMapping("/findall")
	public List<Order> findAllOrders() {
		return service.findAllOrders();
	}

	// find by id
	@GetMapping("findAllOrders/{orderId}")
	public RequestResponse findOrderById(@PathVariable("orderId") long orderId) throws OrderException {
		Cart cart = new Cart();
		Order o = service.findOrderById(orderId);
		cart = restTemplate.getForObject("http://localhost:8888/cart/view/" + o.getCartId(), Cart.class);
		return new RequestResponse(o.getOrderId(), o.getProductId(), o.getProducts(), cart, o.getOrderQuantity(),
				o.getDispatchStatus(), o.getDispatchDate(), o.getDeliveryDate());
	}

	// Delete All Orders
	@DeleteMapping("/deleteAllOrders")
	public String cancelAllOrders() throws OrderException {
		return service.cancelAllOrders();
	}

	// Delete Order By Using Order Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAnItemFromCart(@PathVariable("id") Long oId) throws OrderException {
		service.cancelOrderById(oId);
		return new ResponseEntity<String>("Order deleted successfully!", HttpStatus.OK);
	}

	// updating the date
	@PutMapping("update/{id}")
	public RequestResponse updateOrders(@PathVariable("id") Long id, @RequestBody RequestResponse rr)
			throws OrderException {
		Cart c = new Cart();
		Order o = service.findOrderById(id);
		restTemplate.put("http://localhost:8888/cart/update/" + o.getCartId(), rr.getCartId(), Cart.class);
		c = restTemplate.getForObject("http://localhost:8888/cart/view/" + o.getCartId(), Cart.class);
		Order od = new Order();
		od.setDeliveryDate(rr.getDeliveryDate());
		od.setDispatchDate(rr.getDispatchDate());
		od.setDispatchStatus(rr.getDispatchStatus());
		od.setOrderQuantity(rr.getOrderQuantity());
		o = service.updateDate(id, od);
		return new RequestResponse(id, o.getProductId(), o.getProducts(), c, o.getOrderQuantity(),
				o.getDispatchStatus(), o.getDispatchDate(), o.getDeliveryDate());
	}

}
