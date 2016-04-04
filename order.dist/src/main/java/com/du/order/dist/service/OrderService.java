package com.du.order.dist.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.du.order.dist.Utility;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.du.order.dist.repository.OrderRepository;

@Component
@Transactional
public class OrderService implements IOrderService{
	@Resource
	OrderRepository repo;
	
	@Autowired
	ITransformer transformer;
	
	@Override
	public void create(Order order) {
		setChildrenParent(order);
		repo.save(order);
	}
	
	@Override
	public void update(Order order) throws Exception {
		Order dbOrder = repo.getByRemoteId(order.getRemoteId());
		Utility.copyPrimitiveProperties(order, dbOrder, false);
		repo.deleteChildrenByOid(dbOrder.getOid());
		dbOrder.setOrderDetailList(order.getOrderDetailList());
		setChildrenParent(dbOrder);
		repo.save(dbOrder);
	}

	private void setChildrenParent(Order order){
		if(!order.getOrderDetailList().isEmpty()){
			for (OrderDetail od : order.getOrderDetailList()) {
				od.setOrder(order);
			}
		}
	}
}
