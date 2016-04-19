package com.du.order.dist.interfaces;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.SiparisKalemIn;

public interface ITransformer {

	public Order transformCreate(GenelSiparisIn objectIn) throws Exception; 
	public Order transformUpdate(GenelSiparisIn objectIn) throws Exception;
	public OrderDetail transformCreateDetay(SiparisKalemIn objectIn) throws Exception;
}
