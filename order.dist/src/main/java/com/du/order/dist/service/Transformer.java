package com.du.order.dist.service;

import org.springframework.stereotype.Component;

import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;

@Component
public class Transformer implements ITransformer{

	@Override
	public Order transform(CreateGenelSiparisIn objectIn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order transform(UpdateGenelSiparisIn objectIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
