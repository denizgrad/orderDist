package com.du.order.dist.model.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.du.order.dist.model.base.BaseModel;
@Entity
@Table(name="DU_ORDER_DETAIL")
public class OrderDetail extends BaseModel{

	@ManyToOne(cascade=CascadeType.DETACH)
	private Order order;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	private String siparisKalemAdi;
	private String urunAdi;
	private BigDecimal adet;
	private BigDecimal birimFiyati;
	private BigDecimal araToplam;
	private BigDecimal indirim;
	private BigDecimal kalemGenelToplam;
	
	public String getSiparisKalemAdi() {
		return siparisKalemAdi;
	}
	public void setSiparisKalemAdi(String siparisKalemAdi) {
		this.siparisKalemAdi = siparisKalemAdi;
	}
	public String getUrunAdi() {
		return urunAdi;
	}
	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}
	public BigDecimal getAdet() {
		return adet;
	}
	public void setAdet(BigDecimal adet) {
		this.adet = adet;
	}
	public BigDecimal getBirimFiyati() {
		return birimFiyati;
	}
	public void setBirimFiyati(BigDecimal birimFiyati) {
		this.birimFiyati = birimFiyati;
	}
	public BigDecimal getAraToplam() {
		return araToplam;
	}
	public void setAraToplam(BigDecimal araToplam) {
		this.araToplam = araToplam;
	}
	public BigDecimal getIndirim() {
		return indirim;
	}
	public void setIndirim(BigDecimal indirim) {
		this.indirim = indirim;
	}
	public BigDecimal getKalemGenelToplam() {
		return kalemGenelToplam;
	}
	public void setKalemGenelToplam(BigDecimal kalemGenelToplam) {
		this.kalemGenelToplam = kalemGenelToplam;
	}
	
	
}
