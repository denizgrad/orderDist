package com.du.order.dist.model.util.transfer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
public class GenelSiparisIn extends AIn{
	private String siparisAdi;
	private String siparisVerenFirma;
	private String siparisVerenKisi;
	private String tedarikEdenKisi;
	private String tedarikEdenFirma;
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
	private Date siparisOlusmaTarihi;
	/**
	 * //yapılan
	 */
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
	private Date siparisTeslimTarihi;
	/**
	 * //istenen
	 */
	@JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
	private Date siparisTalepTeslimTarihi;
	private BigDecimal araToplam;
	private BigDecimal kdv;
	private BigDecimal indirim;
	private BigDecimal genelToplam;
	private String adres;
	private String adresAciklama;
	private String siparisAciklama;
	private String teslimAlacakAd;
	private String teslimAlacakSoyad;
	private String teslimAlacakTel;
	private String teslimAlacakGsm;
	private String teslimAlacakEmail;
	private boolean gelal;
	private String odemeSekli;

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

	public String getSiparisAciklama() {
		return siparisAciklama;
	}

	public void setSiparisAciklama(String siparisAciklama) {
		this.siparisAciklama = siparisAciklama;
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
	private String siparisDurum = "Sipariş Oluşturuldu";
	private List<SiparisKalemIn> siparisKalemList = null;
	
	public void addSiparisKalem(SiparisKalemIn kalem){
		if(this.siparisKalemList == null){
			this.siparisKalemList = new ArrayList<SiparisKalemIn>();
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
	public String getSiparisDurum() {
		return siparisDurum;
	}
	public void setSiparisDurum(String siparisDurum) {
		this.siparisDurum = siparisDurum;
	}
	public List<SiparisKalemIn> getSiparisKalemList() {
		return siparisKalemList;
	}
	public void setSiparisKalemList(List<SiparisKalemIn> siparisKalemList) {
		this.siparisKalemList = siparisKalemList;
	}
	
}
