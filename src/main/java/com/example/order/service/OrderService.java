package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.order.RandomStringGen;
import com.example.order.Strings;
import com.example.order.exception.NotFoundException;
import com.example.order.model.Items;
import com.example.order.model.Order;
import com.example.order.model.OrderModel;
import com.example.order.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	OrderRepository orderRepository;

	// temporary CartModel object for check if null
	OrderModel tOrderModel;

	public List<OrderModel> getAll() {
		return orderRepository.findAll();
	}
	
	public OrderModel getOrderModelByUserid(String userid) {
		tOrderModel = orderRepository.findByuserid(userid);
		if (tOrderModel != null)
			return tOrderModel;
		throw new NotFoundException("Order not found!");
	}

//	public OrderModel getOrderModelByOrderidAndUserid(String orderid, String userid) {
//		if (orderRepository.findByuserid(userid) != null)
//			return tOrderModel;
//		throw new NotFoundException("Order not found!");
//	}

	public OrderModel addOrder(String userid, Order order) {
		tOrderModel = orderRepository.findByuserid(userid);

		if (tOrderModel == null)
			tOrderModel = new OrderModel(userid);
//			throw new NotFoundException("User not found");

		order.setRandomOrderId();
		tOrderModel.addToListOfOrders(order);

		return orderRepository.save(tOrderModel);
	}
	
	public OrderModel addOrder(String userid) throws JsonMappingException, JsonProcessingException {
		tOrderModel = orderRepository.findByuserid(userid);

		if (tOrderModel == null)
			tOrderModel = new OrderModel(userid);

		String fetchCartData = new RestTemplate().getForObject(Strings.GET_CART_DATA, String.class);
		
		System.out.println(fetchCartData);
		
		ObjectMapper mapper =  new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(fetchCartData);
		
		//	logic for parse json array of object to list of java objects : 
		//	List<MyClass> myObjects = mapper.readValue(jsonInput, mapper.getTypeFactory().constructCollectionType(List.class, MyClass.class));
		List<Items> cartItemList = mapper.readValue(jsonNode.get("items").toString(), mapper.getTypeFactory().constructCollectionType(List.class, Items.class));
		
		System.out.println(cartItemList);
		
		// create order object for place
		Order order = new Order();
		order.setPaymentid(RandomStringGen.generateRandomString() + RandomStringGen.generateRandomString());
		order.setTotalPrice(jsonNode.get("totalPrice").asDouble());
		order.setRandomOrderId();
		order.setItems(cartItemList);
		
		tOrderModel.addToListOfOrders(order);
		
		cleanCart();
		
		
		System.out.println("going to save into db");
		return orderRepository.save(tOrderModel);
	}

	//	remove all the items of cart after placed the order
	private static void cleanCart() {
		new RestTemplate().delete(Strings.CLEAN_CART);
	}
	
	public OrderModel cancelOrder(String userid, String orderid) {
		tOrderModel = getOrderModelByUserid(userid);
		tOrderModel.cancelOrder(orderid);
		return orderRepository.save(tOrderModel);
	}
}
