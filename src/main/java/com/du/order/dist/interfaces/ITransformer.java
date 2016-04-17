package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;

public interface ITransformer {

	public Order transformCreate(GenelSiparisIn objectIn) throws Exception; 
	public Order transformUpdate(GenelSiparisIn objectIn) throws Exception;;
}
