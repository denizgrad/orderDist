package com.du.order.dist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.du.order.dist.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{

}
