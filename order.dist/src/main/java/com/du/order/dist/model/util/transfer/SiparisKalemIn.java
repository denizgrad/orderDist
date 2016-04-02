package com.du.order.dist.model.util.transfer;

import java.math.BigDecimal;

/**
 * 
 * @author User
 *
 *	Sipariş Kalem Objesi
	SiparişKalem Adı
	Sipariş(Siparişe lookup sipariş kalemin hangi siparişin altında olduğunu belirtiyor)
	Ürün(CustomProduct lookup sipariş kalemde sipariş verilen ürün)
	Adet
	Birim Fiyat
	Ara Toplam(Birim fiyat ile adetin çarpımından oluşuyor)
	İndirim(TL olacak)
	Kalem Fiyat(Ara toplamın indirimli hali. Siparişin hesaplanmasında kullanılacak değer)
 */
public class SiparisKalemIn {
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
