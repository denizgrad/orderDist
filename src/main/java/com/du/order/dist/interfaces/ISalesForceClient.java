package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;

public interface ISalesForceClient {

	public Order updateStatus(Order shipment);

	public String returnAccountId(String userName, String password);

}
