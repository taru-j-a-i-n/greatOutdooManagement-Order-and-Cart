package com.greatoutdoor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="OrderInfo")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	private long productId;
	private String products;// details of product
	private long cartId; // reflects details of cart
	private int orderQuantity;
	private String dispatchStatus;
	private String dispatchDate;
	private String deliveryDate;

	public Order() {
		super();
	}

	public Order(long orderId, long productId, String products, long cartId, int orderQuantity, String dispatchStatus,
			String dispatchDate, String deliveryDate) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.products = products;
		this.cartId = cartId;
		this.orderQuantity = orderQuantity;
		this.dispatchStatus = dispatchStatus;
		this.dispatchDate = dispatchDate;
		this.deliveryDate = deliveryDate;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
}
