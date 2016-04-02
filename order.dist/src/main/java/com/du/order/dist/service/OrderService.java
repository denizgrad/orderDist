package com.du.order.dist.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.repository.OrderRepository;

@Component
@Transactional
public class OrderService implements IOrderService{
	@Resource
	OrderRepository repo;
	
	@Override
	public void create(Order order) {
		repo.save(order);
	}
	
	@Override
	public void update(Order order) {
		Order dbOrder = repo.getByRemoteId(order.getRemoteId());
		//copy properties TODO deniz
		repo.save(dbOrder);
	}

	
}
