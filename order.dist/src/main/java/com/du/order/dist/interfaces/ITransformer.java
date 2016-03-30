package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.transfer.CreateSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateSiparisIn;

public interface ITransformer {

	public Order transform(CreateSiparisIn objectIn);
	public Order transform(UpdateSiparisIn objectIn);
}
