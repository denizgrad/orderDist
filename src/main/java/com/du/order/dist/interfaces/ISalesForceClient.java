package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;

public interface ISalesForceClient {

	public Order updateStatus(Order shipment);

	/**
	 * 
	 * @param userName
	 * @param password
	 * @return sırasıyla userID ve roleID döner
	 */
	public String[] controlCredentials(String userName, String password);

}
