package com.du.order.dist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.du.order.dist.model.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

    @Modifying
    @Query("DELETE FROM OrderDetail p WHERE p.order.oid = :oidOrder")
	public void deleteChildrenByOid(@Param("oidOrder")String oidOrder);

    @Modifying
    @Query("DELETE FROM OrderDetail p WHERE p.remoteId = :remoteId")
	public void deleteByRemoteId(@Param("remoteId") String remoteId);

}
