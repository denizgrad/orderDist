package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;

public interface IOrderService {

	public void create(Order order);
	
	public void update(Order order);

}
