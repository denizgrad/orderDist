package com.du.order.dist.model.util.combo;

import com.du.order.dist.interfaces.NamedEnum;

public enum OrderStatus implements NamedEnum{
	SIPARIS_OLUSTURULDU("Sipariş Oluşturuldu"),
	GORULDU("Görüldü"),
	HAZIRLANIYOR("Hazırlanıyor"),
	HAZIRLANDI("Hazırlandı"),
	YOLA_CIKTI("Yola Çıktı"),
	TESLIM_EDILDI("Teslim Edildi"),
	IPTAL_EDILDI("İptal Edildi");
	
	private String name; 
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private OrderStatus(String name){
		this.name = name;
	}
}
