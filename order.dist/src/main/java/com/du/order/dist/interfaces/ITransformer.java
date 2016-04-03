package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;

public interface ITransformer {

	public Order transform(CreateGenelSiparisIn objectIn) throws Exception; 
	public Order transform(UpdateGenelSiparisIn objectIn) throws Exception;;
}
