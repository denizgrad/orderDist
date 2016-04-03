package com.du.order.dist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.du.order.dist.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
	
    @Query("SELECT o FROM Order o WHERE o.remoteId = :remoteId")
    public Order getByRemoteId(@Param("remoteId") String remoteId);
    
    @Query("SELECT o FROM Order o WHERE o.oid = :oid")
    public Order getByOid(@Param("oid") String oid);

    @Modifying
    @Query("DELETE FROM OrderDetail p WHERE p.order = :oidOrder")
	public void deleteChildrenByOid(@Param("oidOrder")String oidOrder);

}
