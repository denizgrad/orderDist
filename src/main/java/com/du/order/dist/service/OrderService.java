package com.du.order.dist.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.du.order.dist.Utility;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ISalesForceClient;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.du.order.dist.model.util.combo.OrderStatus;
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

	@Autowired
	ISalesForceClient salesForceClient;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Override
	public void create(Order order) {
		setChildrenParent(order);
		order.setCreated(new Date());
		String barcodeNumber = generateBarcode(order);
		order.setBarcodeNumber(barcodeNumber);
		repo.save(order);
		logger.info("order saved");
	}

	private String generateBarcode(Order order) {
		String remoteIdCopy = order.getRemoteId();
		if (StringUtils.isNotBlank(order.getRemoteId())) {
			while (remoteIdCopy.length() < 8) {
				remoteIdCopy = remoteIdCopy + "x";
			}
		}
		return String.valueOf(Math.abs(remoteIdCopy.hashCode()));
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
		logger.info("order updated");
	}

	@Override
	public Order getOrderByOid(String oid) {
		Order dbOrder = repo.getByOid(oid);
		return dbOrder;
	}

	@Override
	public Order getOrderByBarcode(String oid) {
		Order dbOrder = repo.getByBarcode(oid);
		return dbOrder;
	}

	@Override
	public void updateOrderStatus(String oid, String status) throws Exception {

		Order dbOrder = repo.getByOid(oid);
		dbOrder.setSiparisDurum(status);
		if (status.equals(String.valueOf(OrderStatus.TESLIM_EDILDI.getValue()))) {
			dbOrder.setSiparisTeslimTarihi(new Date());
		}

		repo.save(dbOrder);

		sendToSF(dbOrder);

	}

	@Override
	public void updateOrderBarcode(String oid, String barcode) {

		Order dbOrder = repo.getByOid(oid);
		dbOrder.setBarcodeNumber(barcode);
		repo.save(dbOrder);

	}

	private void setChildrenParent(Order order) {
		if (!order.getOrderDetailList().isEmpty()) {
			for (OrderDetail od : order.getOrderDetailList()) {
				od.setOrder(order);
				od.setRemoteId(order.getRemoteId());
			}
		}
	}

	// @Override
	// public List<Order> getOrderList(String orgOid) {
	// List<Order> list = repo.getListByBranchOid(orgOid);
	//
	// for (Order order : list) {
	// order.setOrderDetailList(new ArrayList<OrderDetail>());
	// }
	// return list;
	// }

	@Override
	public List<Order> getOrderList(String orgOid) {
		List<Order> list = repo.getListByBranchOid(orgOid);
		return list;
	}

	@Override
	public void deliverOrder(String oid) throws Exception {

		updateOrderStatus(oid, String.valueOf(OrderStatus.TESLIM_EDILDI.getValue()));

	}

	@Transactional(readOnly = true)
	private void sendToSF(Order dbOrder) throws Exception {
		// Hazirlaniyor Teslimatta Teslim Edildi

		Order order = new Order();
		Utility.copyPrimitiveProperties(dbOrder, order, false);
		// repoDetail.deleteChildrenByOid(dbOrder.getOid());
		order.setOrderDetailList(dbOrder.getOrderDetailList());

		if (order.getSiparisDurum().equals(String.valueOf(OrderStatus.YOLA_CIKTI.getValue()))) {
			order.setSiparisDurum("Teslimatta");
			salesForceClient.updateStatus(order);
		} else if (order.getSiparisDurum().equals(String.valueOf(OrderStatus.TESLIM_EDILDI.getValue()))) {
			order.setSiparisDurum("Teslim Edildi");
			salesForceClient.updateStatus(order);
		} else {
			order.setSiparisDurum("Hazirlaniyor");
			salesForceClient.updateStatus(order);
		}

	}

}
