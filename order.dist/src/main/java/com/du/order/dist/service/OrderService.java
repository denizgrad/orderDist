package com.du.order.dist.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.du.order.dist.Utility;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.du.order.dist.repository.OrderDetailRepository;
import com.du.order.dist.repository.OrderRepository;

@Component
@Transactional
public class OrderService implements IOrderService {
	@Resource
	OrderRepository repo;

	@Resource
	OrderDetailRepository repoDetail;

	@Autowired
	ITransformer transformer;

	@Override
	public void create(Order order) {
		setChildrenParent(order);
		order.setCreated(new Date());
		repo.save(order);
	}

	@Override
	public void update(Order order) throws Exception {
		Order dbOrder = repo.getByRemoteId(order.getRemoteId());
		Utility.copyPrimitiveProperties(order, dbOrder, false);
		repoDetail.deleteChildrenByOid(dbOrder.getOid());
		dbOrder.setOrderDetailList(order.getOrderDetailList());
		setChildrenParent(dbOrder);
		dbOrder.setCreated(new Date());
		repo.save(dbOrder);
	}


	@Override
	public Order getOrderByOid(String oid) {
		// TODO get child
		Order dbOrder = repo.getByOid(oid);
		return dbOrder;
	}

	@Override
	public Order getOrderByBarcode(String oid) {
		Order dbOrder = repo.getByBarcode(oid);
		return dbOrder;
	}

	@Override
	public void updateOrderStatus(String oid, String status) {
		// Order dbOrder = repo.getByOid(oid);
		// Utility.copyPrimitiveProperties(order, dbOrder, false);
		// repoDetail.deleteChildrenByOid(dbOrder.getOid());
		// dbOrder.setOrderDetailList(order.getOrderDetailList());
		// setChildrenParent(dbOrder);
		// repo.save(dbOrder);
	}

	private void setChildrenParent(Order order) {
		if (!order.getOrderDetailList().isEmpty()) {
			for (OrderDetail od : order.getOrderDetailList()) {
				od.setOrder(order);
				od.setRemoteId(order.getRemoteId());
			}
		}
	}

	@Override
	public List<Order> getOrderList(String orgOid) {
		List<Order> list = repo.getListByBranchOid();

		for (Order order : list) {
			order.setOrderDetailList(new ArrayList<OrderDetail>());
		}
		return list;
	}

	@Override
	public void updateBarcode(String oid, String barcode) {
		// TODO Auto-generated method stub
		
	}
}
