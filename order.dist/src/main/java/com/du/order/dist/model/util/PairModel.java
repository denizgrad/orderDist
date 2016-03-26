package com.du.order.dist.model.util;

public class PairModel {
	String key;
	String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public PairModel(String key, String value){
		this.key=key;
		this.value=value;
	}
	public PairModel(String value){
		this.key=value;
		this.value=value;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("PairModel: key: ");
		sb.append(this.key);
		sb.append(" value: " +this.value);
		return sb.toString();
	}
}
