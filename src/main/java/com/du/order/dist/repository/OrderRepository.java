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

	// TODO org oid ekle veya sube
	@Query("SELECT o FROM Order o")
	public List<Order> getListByBranchOid();

	// TODO org oid ekle veya sube
	@Query("SELECT o FROM Order o WHERE o.barcodeNumber = :barcode")
	public Order getByBarcode(@Param("barcode") String barcode);
	
}
