package com.du.order.dist.model.util.combo;

import com.du.order.dist.interfaces.NamedEnum;

public enum OrderStatus implements NamedEnum {
//	SIPARIS_OLUSTURULDU("Sipariş Oluşturuldu", 1), 
//	GORULDU("Görüldü", 2), 
//	HAZIRLANIYOR("Hazırlanıyor", 3), 
//	HAZIRLANDI("Hazırlandı",4), 
//	YOLA_CIKTI("Yola Çıktı", 5), 
//	TESLIM_EDILDI("Teslim Edildi", 6), 
//	IPTAL_EDILDI("İptal Edildi", 7);

	SIPARIS_OLUSTURULDU("Sipariş Oluşturuldu"), 
	GORULDU("Görüldü"), 
	HAZIRLANIYOR("Hazırlanıyor"), 
	HAZIRLANDI("Hazırlandı"), 
	TESLIMATTA("Teslimatta"), 
	TESLIM_EDILDI("Teslim Edildi"), 
	IPTAL_EDILDI("İptal Edildi");
	
	private String key;
//	private int value;

	@Override
	public String getKey() {
		return key;
	}

//	@Override
//	public int getValue() {
//		return value;
//	}

	public void setKey(String key) {
		this.key = key;
	}

//	public void setValue(int value) {
//		this.value = value;
//	}

//	private OrderStatus(String key, int value) {
//		this.key = key;
//		this.value = value;
//
//	}
	private OrderStatus(String key) {
		this.key = key;

	}
}
