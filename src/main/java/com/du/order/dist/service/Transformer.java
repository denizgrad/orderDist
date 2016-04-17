package com.du.order.dist.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.du.order.dist.Utility;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.du.order.dist.model.util.OrderError;
import com.du.order.dist.model.util.SystemError;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.SiparisKalemIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;
import com.du.order.dist.repository.OrderDetailRepository;
import com.du.order.dist.repository.OrderRepository;

@Component
@Transactional
public class Transformer implements ITransformer{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    OrderRepository repo;
    @Autowired
    OrderDetailRepository repoDetail;
	@Override
	public Order transformCreate(GenelSiparisIn objectIn) throws Exception {
		Order order = new Order();
		if(repo.getByRemoteId(objectIn.getSfId()) != null){
			throw new OrderError("Yaratılmaya çalışılan sipariş için sfId zaten girilmiş.");
		}
		try {
			Utility.copyPrimitiveProperties(objectIn, order, false);
			order.setRemoteId(objectIn.getSfId());
			List<OrderDetail> productList = new ArrayList<>();
			for(SiparisKalemIn sk : objectIn.getSiparisKalemList()){
				OrderDetail product = new OrderDetail();
				Utility.copyPrimitiveProperties(sk, product, false);
				productList.add(product);
			}
			order.setOrderDetailList(productList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return order;
	}

	@Override
	public Order transformUpdate(GenelSiparisIn objectIn) throws Exception{
		Order order = repo.getByRemoteId(objectIn.getSfId());
		if(order == null){
			logger.info("Bu sfId ile daha önce bir kayıt girilmemiş. Kayıt yapılacak");
			return null;
		}
		try {
			Utility.copyPrimitiveProperties(objectIn, order, false);
			order.setRemoteId(objectIn.getSfId());
			if(!CollectionUtils.isEmpty(objectIn.getSiparisKalemList())){
				List<OrderDetail> productList = new ArrayList<>();
				
//				deleteAllProducts(repo.getByRemoteId(objectIn.getSfId()).getOid());
				
				for(SiparisKalemIn sk : objectIn.getSiparisKalemList()){
					OrderDetail product = new OrderDetail();
					Utility.copyPrimitiveProperties(sk, product, false);
					productList.add(product);
				}
				order.setOrderDetailList(productList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return order;
	}

	private void deleteAllProducts(String oidOrder) {
		repoDetail.deleteChildrenByOid(oidOrder);
	}

}
