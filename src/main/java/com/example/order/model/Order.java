package com.example.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.order.RandomStringGen;

@Configuration
@Document
public class Order {
	private String orderid;
	private String paymentid;
	private List<Items> items;
	private double totalPrice;
	private String status = new String("Pending");
	private String orderplaceddate = new Date().toString();
	private boolean ordercancelled;

	public Order() {
		items = new ArrayList<Items>();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public String getOrderplaceddate() {
		return orderplaceddate;
	}

	public void setOrderplaceddate(String orderplaceddate) {
		this.orderplaceddate = orderplaceddate;
	}

	public boolean isOrdercancelled() {
		return ordercancelled;
	}

	public void setOrdercancelled(boolean ordercancelled) {
		this.ordercancelled = ordercancelled;
	}

	public void setRandomOrderId() {
		setOrderid(RandomStringGen.generateRandomString());
	}


	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", paymentid=" + paymentid + ", items=" + items + ", totalPrice="
				+ totalPrice + ", status=" + status + ", orderplaceddate=" + orderplaceddate + ", ordercancelled="
				+ ordercancelled + "]";
	}


}
