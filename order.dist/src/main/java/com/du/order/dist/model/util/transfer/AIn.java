package com.du.order.dist.model.util.transfer;

public abstract class AIn {

	private String userName;
	private String password;
	private String sfId;
	
	public String getSfId() {
		return sfId;
	}
	public void setSfId(String sfId) {
		this.sfId = sfId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
