package com.du.order.dist.interfaces;

import java.util.List;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;

public interface IOrderService {

	public void create(Order order);

	public void update(Order order) throws Exception;

	public List<Order> getOrderList(String orgOid);

	public void updateOrderStatus(String oid, String status) throws Exception;

	public Order getOrderByOid(String oid);

	public Order getOrderByBarcode(String barcode);

	public void updateOrderBarcode(String oid, String barcode);

	public void deliverOrder(String oid) throws Exception;

	public void createDetay(OrderDetail orderDetail);

}
