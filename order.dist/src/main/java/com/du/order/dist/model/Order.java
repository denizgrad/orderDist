package com.du.order.dist.model;

import com.du.order.dist.model.base.BaseModel;
import com.du.order.dist.model.util.combo.OrderStatus;

public class Order extends BaseModel{

	private OrderStatus status = OrderStatus.SIPARIS_OLUSTURULDU;
	private String  barcodeNumber;

    private String  receiverName;
    private String  receiverAddress;
    private String  receiverCityName;
    private String  receiverTownName;
    private String  receiverPhoneNumber;
    private String  orderDate;
    private String  deliveryDate;
    private String  deliveryPerson;
    private String  deliveryStore;
	private String 	description;
    private double  amount;
    private String  productName;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public String getBarcodeNumber() {
		return barcodeNumber;
	}
	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverCityName() {
		return receiverCityName;
	}
	public void setReceiverCityName(String receiverCityName) {
		this.receiverCityName = receiverCityName;
	}
	public String getReceiverTownName() {
		return receiverTownName;
	}
	public void setReceiverTownName(String receiverTownName) {
		this.receiverTownName = receiverTownName;
	}
	public String getReceiverPhoneNumber() {
		return receiverPhoneNumber;
	}
	public void setReceiverPhoneNumber(String receiverPhoneNumber) {
		this.receiverPhoneNumber = receiverPhoneNumber;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryPerson() {
		return deliveryPerson;
	}
	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
	public String getDeliveryStore() {
		return deliveryStore;
	}
	public void setDeliveryStore(String deliveryStore) {
		this.deliveryStore = deliveryStore;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
