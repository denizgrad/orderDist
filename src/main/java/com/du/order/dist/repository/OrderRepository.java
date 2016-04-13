package com.du.order.dist.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.du.order.dist.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT o FROM Order o WHERE o.remoteId = :remoteId")
	public Order getByRemoteId(@Param("remoteId") String remoteId);

	@Query("SELECT o FROM Order o WHERE o.oid = :oid")
	public Order getByOid(@Param("oid") String oid);

	// TODO order class ında org oid yi iliştir
//	@Query("SELECT o FROM Order o WHERE o.orgOid= :orgOid")
//	public List<Order> getListByBranchOid(@Param("orgOid") String orgOid);
	// statu kriteri ekle;
	
	@Query("SELECT o FROM Order o WHERE (o.siparisDurum != '6' and o.siparisDurum != '7') or (o.siparisTeslimTarihi >= current_date ) ")
	public List<Order> getListByBranchOid();
	
	@Query("SELECT o FROM Order o WHERE o.barcodeNumber = :barcode")
	public Order getByBarcode(@Param("barcode") String barcode);
	
}
