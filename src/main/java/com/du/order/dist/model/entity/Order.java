package com.du.order.dist.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.du.order.dist.model.base.BaseModel;

@Entity
@Table(name = "DU_ORDER")
public class Order extends BaseModel {

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetailList;

	private String barcodeNumber;
	private String siparisAdi;
	private String siparisVerenFirma;
	private String siparisVerenKisi;
	
	private String tedarikEdenAccount;
	
	private String tedarikEdenKisi;
	private String tedarikEdenFirma;
	private String teslimAlacakAd;
	private String teslimAlacakSoyad;
	private String teslimAlacakTel;
	private String teslimAlacakGsm;
	private String teslimAlacakEmail;
	private Date siparisOlusmaTarihi;
	/**
	 * //yapılan
	 */
	private Date siparisTeslimTarihi;
	/**
	 * //istenen
	 */
	private Date siparisTalepTeslimTarihi;
	private BigDecimal araToplam;
	private BigDecimal kdv;
	private BigDecimal indirim;
	private BigDecimal genelToplam;
	private String adres;
	private String adresAciklama;
	private String siparisAciklama;
	private boolean gelal;
	private String odemeSekli;

	/**
	 * <pre>
	  	("Sipariş Oluşturuldu")
		("Görüldü")
		("Hazırlanıyor")
		("Hazırlandı")
		("Yola Çıktı")
		("Teslim Edildi")
		("İptal Edildi")
	 * </pre>
	 */
	private String siparisDurum = "Sipariş Oluşturuldu";

	// getters setters
	public String getOdemeSekli() {
		return odemeSekli;
	}

	public void setOdemeSekli(String odemeSekli) {
		this.odemeSekli = odemeSekli;
	}

	public boolean isGelal() {
		return gelal;
	}

	public void setGelal(boolean gelal) {
		this.gelal = gelal;
	}

	
	public String getBarcodeNumber() {
		return barcodeNumber;
	}

	public String getTeslimAlacakEmail() {
		return teslimAlacakEmail;
	}

	public void setTeslimAlacakEmail(String teslimAlacakEmail) {
		this.teslimAlacakEmail = teslimAlacakEmail;
	}

	public String getTeslimAlacakAd() {
		return teslimAlacakAd;
	}

	public void setTeslimAlacakAd(String teslimAlacakAd) {
		this.teslimAlacakAd = teslimAlacakAd;
	}

	public String getTeslimAlacakSoyad() {
		return teslimAlacakSoyad;
	}

	public void setTeslimAlacakSoyad(String teslimAlacakSoyad) {
		this.teslimAlacakSoyad = teslimAlacakSoyad;
	}

	public String getTeslimAlacakTel() {
		return teslimAlacakTel;
	}

	public void setTeslimAlacakTel(String teslimAlacakTel) {
		this.teslimAlacakTel = teslimAlacakTel;
	}

	public String getTeslimAlacakGsm() {
		return teslimAlacakGsm;
	}

	public void setTeslimAlacakGsm(String teslimAlacakGsm) {
		this.teslimAlacakGsm = teslimAlacakGsm;
	}

	public String getTedarikEdenAccount() {
		return tedarikEdenAccount;
	}

	public void setTedarikEdenAccount(String tedarikEdenAccount) {
		this.tedarikEdenAccount = tedarikEdenAccount;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	public String getSiparisAdi() {
		return siparisAdi;
	}

	public void setSiparisAdi(String siparisAdi) {
		this.siparisAdi = siparisAdi;
	}

	public String getSiparisVerenFirma() {
		return siparisVerenFirma;
	}

	public void setSiparisVerenFirma(String siparisVerenFirma) {
		this.siparisVerenFirma = siparisVerenFirma;
	}

	public String getSiparisVerenKisi() {
		return siparisVerenKisi;
	}

	public void setSiparisVerenKisi(String siparisVerenKisi) {
		this.siparisVerenKisi = siparisVerenKisi;
	}

	public String getTedarikEdenKisi() {
		return tedarikEdenKisi;
	}

	public void setTedarikEdenKisi(String tedarikEdenKisi) {
		this.tedarikEdenKisi = tedarikEdenKisi;
	}

	public String getTedarikEdenFirma() {
		return tedarikEdenFirma;
	}

	public void setTedarikEdenFirma(String tedarikEdenFirma) {
		this.tedarikEdenFirma = tedarikEdenFirma;
	}

	public Date getSiparisOlusmaTarihi() {
		return siparisOlusmaTarihi;
	}

	public void setSiparisOlusmaTarihi(Date siparisOlusmaTarihi) {
		this.siparisOlusmaTarihi = siparisOlusmaTarihi;
	}

	public Date getSiparisTeslimTarihi() {
		return siparisTeslimTarihi;
	}

	public void setSiparisTeslimTarihi(Date siparisTeslimTarihi) {
		this.siparisTeslimTarihi = siparisTeslimTarihi;
	}

	public Date getSiparisTalepTeslimTarihi() {
		return siparisTalepTeslimTarihi;
	}

	public void setSiparisTalepTeslimTarihi(Date siparisTalepTeslimTarihi) {
		this.siparisTalepTeslimTarihi = siparisTalepTeslimTarihi;
	}

	public BigDecimal getAraToplam() {
		return araToplam;
	}

	public void setAraToplam(BigDecimal araToplam) {
		this.araToplam = araToplam;
	}

	public BigDecimal getKdv() {
		return kdv;
	}

	public void setKdv(BigDecimal kdv) {
		this.kdv = kdv;
	}

	public BigDecimal getIndirim() {
		return indirim;
	}

	public void setIndirim(BigDecimal indirim) {
		this.indirim = indirim;
	}

	public BigDecimal getGenelToplam() {
		return genelToplam;
	}

	public void setGenelToplam(BigDecimal genelToplam) {
		this.genelToplam = genelToplam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getAdresAciklama() {
		return adresAciklama;
	}

	public void setAdresAciklama(String adresAciklama) {
		this.adresAciklama = adresAciklama;
	}

	public String getSiparisDurum() {
		return siparisDurum;
	}

	public void setSiparisDurum(String siparisDurum) {
		this.siparisDurum = siparisDurum;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getSiparisAciklama() {
		return siparisAciklama;
	}

	public void setSiparisAciklama(String siparisAciklama) {
		this.siparisAciklama = siparisAciklama;
	}

}
