package com.du.order.dist.model.util;

public class LoginForm {
	private String username;
	private String password;
	private String accId;
	private String roleId;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAccId() {
		return accId;
	}
	public void setAccId(String userId) {
		this.accId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
