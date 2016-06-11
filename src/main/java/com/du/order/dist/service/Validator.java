package com.du.order.dist.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.du.order.dist.interfaces.IValidator;
import com.du.order.dist.model.util.ValidationError;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.SiparisKalemIn;
@Component
public class Validator implements IValidator{

	private static String EMPTY = "Boş";
	private static BigDecimal ZERO = BigDecimal.ZERO;
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void validate(GenelSiparisIn siparis) throws ValidationError {

		//must must
		if(StringUtils.isBlank(siparis.getSfId())){
//			throw new ValidationError(new String[]{"SalesForceId ( alan adı: sfId )"}, null);
//			siparis.setSfId(EMPTY);
		};
		
		//must
//		List<String> nullFields = new ArrayList<>();
		if(StringUtils.isBlank(siparis.getAdres())){
//			nullFields.add("adres");
			siparis.setAdres(EMPTY);
		}else{
			siparis.setAdres(siparis.getAdres().substring(0, Math.min(siparis.getAdres().length(), 800))+"...");
		}
		if((siparis.getAraToplam() == null  || siparis.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add("araToplam");
			siparis.setAraToplam(ZERO);
		};
		if((siparis.getGenelToplam() == null  || siparis.getGenelToplam().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add("genelToplam");
			siparis.setGenelToplam(ZERO);
		};
		if((siparis.getKdv() == null  || siparis.getKdv().compareTo(BigDecimal.ZERO) == 0)){
			//nullFields.add("kdv");
			logger.info("Sipariş kdv bos geldi. Create yapılıyor.");
			siparis.setKdv(ZERO);
		};
		if(siparis.getSiparisTalepTeslimTarihi() == null){
//			nullFields.add("siparisTalepTeslimTarihi");
			siparis.setSiparisTalepTeslimTarihi(new Date());
		};
		if(StringUtils.isBlank(siparis.getSiparisAdi())){
//			nullFields.add("siparisAdi");
			siparis.setSiparisAdi(EMPTY);
		}else{
			siparis.setSiparisAdi(siparis.getSiparisAdi().substring(0, Math.min(siparis.getSiparisAdi().length(), 800))+"...");
		}
		if(StringUtils.isBlank(siparis.getSiparisVerenFirma())&& StringUtils.isBlank(siparis.getSiparisVerenKisi())){
//			nullFields.add("siparisVerenFirma ya da siparisVerenKisi");
			siparis.setSiparisVerenFirma(EMPTY);
			siparis.setSiparisVerenKisi(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getTedarikEdenFirma())&& StringUtils.isBlank(siparis.getTedarikEdenKisi())){
//			nullFields.add("tedarikEdenFirma ya da tedarikEdenKisi");
			siparis.setTedarikEdenFirma(EMPTY);
			siparis.setTedarikEdenKisi(EMPTY);
		};
		
		if(siparis.getSiparisDurum() == null){
			siparis.setSiparisDurum("Sipariş Oluşturuldu"); 
		};
		
		if((siparis.getIndirim() == null  || siparis.getIndirim().compareTo(BigDecimal.ZERO) == 0)){
			siparis.setIndirim(BigDecimal.ZERO);
		};

		if(siparis.getSiparisOlusmaTarihi() == null){
			siparis.setSiparisOlusmaTarihi(new Date()); 
		};
		if(StringUtils.isBlank(siparis.getOdemeSekli())){
//			nullFields.add("tedarikEdenFirma ya da tedarikEdenKisi");
			siparis.setOdemeSekli(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getOdemeSekli())){
//			nullFields.add("tedarikEdenFirma ya da tedarikEdenKisi");
			siparis.setOdemeSekli(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getSiparisAciklama())){
//			nullFields.add("tedarikEdenFirma ya da tedarikEdenKisi");
			siparis.setSiparisAciklama(EMPTY);
		}else{
			siparis.setSiparisAciklama(siparis.getSiparisAciklama().substring(0, Math.min(siparis.getSiparisAciklama().length(), 800))+"...");
		}
		if(StringUtils.isBlank(siparis.getTeslimAlacakAd())){
			siparis.setTeslimAlacakAd(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getTeslimAlacakSoyad())){
			siparis.setTeslimAlacakSoyad(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getTeslimAlacakGsm())){
			siparis.setTeslimAlacakGsm(EMPTY);
		};
		if(StringUtils.isBlank(siparis.getTeslimAlacakTel())){
			siparis.setTeslimAlacakTel(EMPTY);
		};
		logger.info("Validation OK");
	}
	@Override
	public void validateDetay(SiparisKalemIn kalem) throws ValidationError {
//		List<String> nullFields = new ArrayList<>();
		if(StringUtils.isBlank(kalem.getSiparisKalemAdi())){
			kalem.setSiparisKalemAdi(EMPTY);
//			nullFields.add( "siparisKalemAdi");
		};
		if(StringUtils.isBlank(kalem.getUrunId())){
			kalem.setUrunId(EMPTY);
//			nullFields.add( "kalem için urunId");
		};
		if(StringUtils.isBlank(kalem.getUrunAdi())){
			kalem.setUrunAdi(EMPTY);
//			nullFields.add( " kalem için urunAdi");
		};
		if((kalem.getAdet() == null  || kalem.getAdet().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add( " kalem için adet");
			kalem.setAdet(ZERO);
		};
		if((kalem.getBirimFiyati() == null  || kalem.getBirimFiyati().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add( " kalem için birimFiyati");
			kalem.setBirimFiyati(ZERO); 
		};
		if((kalem.getAraToplam() == null  || kalem.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add( " kalem için araToplam");
			kalem.setAraToplam(ZERO) ;
		};
		if((kalem.getKalemGenelToplam() == null  || kalem.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
//			nullFields.add( " kalem için araToplam");
			kalem.setKalemGenelToplam(ZERO) ;
		};
		if((kalem.getIndirim() == null  || kalem.getIndirim().compareTo(BigDecimal.ZERO) == 0)){
//			kalem.setIndirim(BigDecimal.ZERO);
			kalem.setIndirim(ZERO) ;
		};
		if((kalem.getAraToplam() == null  || kalem.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
			kalem.setAraToplam(ZERO);
//			nullFields.add( " kalem için araToplam");
		};
//		if(!nullFields.isEmpty()){
//			throw new ValidationError(nullFields.toArray(new String[nullFields.size()]), null);
//		}
		logger.info("Validation Detay OK");
	}
}
