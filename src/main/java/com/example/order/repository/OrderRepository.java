package com.example.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order.model.OrderModel;

@Repository
public interface OrderRepository extends MongoRepository<OrderModel, String> {

	// OrderModel findByOrderidAndUserid(String orderid, String userid);

	// OrderModel findByorderid(String orderid);

	OrderModel findByuserid(String userid);

}
