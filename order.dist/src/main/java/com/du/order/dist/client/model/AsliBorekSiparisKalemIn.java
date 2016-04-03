package com.du.order.dist.client.model;

import java.math.BigDecimal;

public class AsliBorekSiparisKalemIn {
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
