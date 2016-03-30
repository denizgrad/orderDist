package com.du.order.dist.service;

import javax.annotation.Resource;

import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.repository.OrderRepository;

public class OrderService implements IOrderService{
	@Resource
	OrderRepository repo;
	
	@Override
	public void create(Order order) {
		order.setDeliveryStore(ServiceProvider.getCurrentUserName());
		repo.save(order);
	}
	
	@Override
	public void update(Order order) {
		Order dbOrder = repo.getByRemoteId(order.getRemoteId());
		//copy properties TODO deniz
		repo.save(dbOrder);
	}

	
}
