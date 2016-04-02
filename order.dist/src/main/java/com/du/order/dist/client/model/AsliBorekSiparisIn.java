package com.du.order.dist.client.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AsliBorekSiparisIn {
	private String siparisAdi;
	private String siparisVerenFirma;
	private String siparisVerenKisi;
	private String tedarikEdenKisi;
	private String tedarikEdenFirma;
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
	/**
	 * <pre>
	  	("Sipariş Oluşturuldu")
		("Görüldü")
		("Hazırlanıyor")
		("Hazırlandı")
		("Yola Çıktı")
		("Teslim Edildi")
		("İptal Edildi")
	 *	</pre>
	 */
	private String siparisDurum = "Sipariş Oluşturuldu";
	private List<AsliBorekSiparisKalemIn> siparisKalemList = null;
	
	public void addSiparisKalem(AsliBorekSiparisKalemIn kalem){
		if(this.siparisKalemList == null){
			this.siparisKalemList = new ArrayList<AsliBorekSiparisKalemIn>();
		}
		this.siparisKalemList.add(kalem);
	}
	
	//setters getters
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
	public void setSiparisTeslimTarihi(Date sipraisTeslimTarihi) {
		this.siparisTeslimTarihi = sipraisTeslimTarihi;
	}
	public Date getSiparisTalepTeslimTarihi() {
		return siparisTalepTeslimTarihi;
	}
	public void setSiparisTalepTeslimTarihi(Date sipraisTalepTeslimTarihi) {
		this.siparisTalepTeslimTarihi = sipraisTalepTeslimTarihi;
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
	/**
	 * <pre>
	  	("Sipariş Oluşturuldu")
		("Görüldü")
		("Hazırlanıyor")
		("Hazırlandı")
		("Yola Çıktı")
		("Teslim Edildi")
		("İptal Edildi")
	 *	</pre>
	 */
	public String getSiparisDurum() {
		return siparisDurum;
	}
	/**
	 * <pre>
	  	("Sipariş Oluşturuldu")
		("Görüldü")
		("Hazırlanıyor")
		("Hazırlandı")
		("Yola Çıktı")
		("Teslim Edildi")
		("İptal Edildi")
	 *	</pre>
	 */
	public void setSiparisDurum(String siparisDurum) {
		this.siparisDurum = siparisDurum;
	}
	public List<AsliBorekSiparisKalemIn> getSiparisKalemList() {
		return siparisKalemList;
	}
	public void setSiparisKalemList(List<AsliBorekSiparisKalemIn> siparisKalemList) {
		this.siparisKalemList = siparisKalemList;
	}
	
}
