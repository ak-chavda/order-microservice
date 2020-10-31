package com.example.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.exception.PayloadException;
import com.example.order.model.Order;
import com.example.order.model.OrderModel;
import com.example.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
@RequestMapping("/api/order")
public class OrderController {

//	Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	private OrderService orderService;

//	@GetMapping("/")
//	public void home() {
//		System.out.println("home");
//	}

	@GetMapping("/")
	public List<OrderModel> listAllOrders() {
		return orderService.getAll();
	}

	@GetMapping("/{userId}")
	public OrderModel getOrderRest(@PathVariable("userId") String userid) {
		return orderService.getOrderModelByUserid(userid);
	}

//	@GetMapping("/{orderId}/{userId}")
//	public OrderModel getOrderRest(@PathVariable("orderId") String orderid, @PathVariable("userId") String userid) {
//		return orderService.getOrderModelByOrderidAndUserid(orderid, userid);
//	}

	// new order placed (adding new order)
	@PostMapping("/{userId}")
	public OrderModel addOrderRest(@PathVariable("userId") String userid, @RequestBody Order order) {
		if (order == null)
			throw new PayloadException("Payload missing");

		return orderService.addOrder(userid, order);
	}
	
	// new order placed (from cart service data fetching)
	@PostMapping("/{userId}/placeOrderFromCart")
	public OrderModel addOrderRest(@PathVariable("userId") String userid) throws JsonMappingException, JsonProcessingException {
		return orderService.addOrder(userid);
	}

	// cancel order
	@DeleteMapping("/{orderId}/{userId}")
	public OrderModel cancelOrder(@PathVariable("orderId") String orderid, @PathVariable("userId") String userid) {
		return orderService.cancelOrder(userid, orderid);
	}
}
