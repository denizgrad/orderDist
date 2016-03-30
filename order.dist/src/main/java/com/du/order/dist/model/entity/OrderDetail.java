package com.du.order.dist.model.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.du.order.dist.model.base.BaseModel;

@Entity
@Table(name = "DU_ORDER_DETAIL")
public class OrderDetail extends BaseModel {

	// TODO product ayrı bir entity olabilir
	private String orderOid;
	private String orderDate;
	private String productId;
	private String productName;
	private double amount;
	private String description;
	private BigDecimal price;

	public String getOrderOid() {
		return orderOid;
	}

	public void setOrderOid(String orderOid) {
		this.orderOid = orderOid;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
