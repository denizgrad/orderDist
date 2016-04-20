package com.du.order.dist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.remoteId = :remoteId")
	public Order getByRemoteId(@Param("remoteId") String remoteId);

	@Query("SELECT o FROM Order o WHERE o.oid = :oid")
	public Order getByOid(@Param("oid") String oid);

	@Query("SELECT o FROM OrderDetail o WHERE o.orderRemoteId = :remoteOid")
	public List<OrderDetail> fetchOrderDetail(@Param("remoteOid") String remoteOid);

	@Query("SELECT o FROM Order o WHERE (((o.siparisDurum != 'Teslim Edildi' and o.siparisDurum != 'İptal Edildi') or (o.siparisTeslimTarihi >= current_date )) AND (o.tedarikEdenAccount = :orgOid)) ORDER BY o.created DESC")
	public List<Order> getListByBranchOid(@Param("orgOid") String orgOid);

	@Query("SELECT o FROM Order o WHERE o.barcodeNumber = :barcode")
	public Order getByBarcode(@Param("barcode") String barcode);

}
