package com.example.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.order.exception.InternalServerException;
import com.example.order.exception.NotFoundException;

@Configuration
@Document(collection = "orderservice")
public class OrderModel implements Serializable {

	private static final long serialVersionUID = -1702628681215626466L;

	@Id
	private String userid;
	private List<Order> listOfOrders;

	// this() will initialize ArrayList class member(here: listOfOrders)
	public OrderModel() {
		super();
		this.listOfOrders = new ArrayList<Order>();
	}

	public OrderModel(String userid) {
		this();
		this.userid = userid;
	}

	public OrderModel(String userid, List<Order> listOfOrders) {
		this();
		this.userid = userid;
		this.listOfOrders = listOfOrders;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(List<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}

	public void addToListOfOrders(Order order) {
		listOfOrders.add(order);
	}

	public void cancelOrder(String orderid) {
		Order findOrder = listOfOrders.stream().filter(o -> o.getOrderid().equals(orderid)).findFirst().orElse(null);

		if (findOrder == null) {
			throw new NotFoundException("Order not found");
		}
		else if(findOrder.getStatus().equals("Pending")) {
			findOrder.setOrdercancelled(true);
			findOrder.setStatus("Cancelled");
		}
		else
			throw new InternalServerException("Order can not be Cancelled!");
	}

	@Override
	public String toString() {
		return "OrderModel [userid=" + userid + ", listOfOrders=" + listOfOrders + "]";
	}
}
