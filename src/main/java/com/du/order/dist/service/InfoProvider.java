package com.du.order.dist.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class InfoProvider implements IInfoProvider{
	
	private String accID;
	@Override
	public String getAccId(){
		return accID;
	}
	@Override
	public void setAccId(String accId){
		accID = accId;
	}
}
