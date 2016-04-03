package com.du.order.dist.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.plexus.util.StringUtils;
import org.springframework.stereotype.Component;

import com.du.order.dist.interfaces.IValidator;
import com.du.order.dist.model.util.ValidationError;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.SiparisKalemIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;
@Component
public class Validator implements IValidator{

	@Override
	public void validate(GenelSiparisIn siparis) throws ValidationError {

		//must must
		if(StringUtils.isBlank(siparis.getSfId())){
			throw new ValidationError(new String[]{"SalesForceId ( alan adı: sfId )"}, null);
		};
		
		//must
		List<String> nullFields = new ArrayList<>();
		if(StringUtils.isBlank(siparis.getAdres())){
			nullFields.add("adres");
		};
		if((siparis.getAraToplam() == null  || siparis.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
			nullFields.add("araToplam");
		};
		if((siparis.getGenelToplam() == null  || siparis.getGenelToplam().compareTo(BigDecimal.ZERO) == 0)){
			nullFields.add("genelToplam");
		};
		if((siparis.getKdv() == null  || siparis.getKdv().compareTo(BigDecimal.ZERO) == 0)){
			nullFields.add("kdv");
		};
		if(siparis.getSiparisTalepTeslimTarihi() == null){
			nullFields.add("siparisTalepTeslimTarihi");
		};
		if(StringUtils.isBlank(siparis.getSiparisAdi())){
			nullFields.add("siparisAdi");
		};
		if((siparis.getSiparisKalemList()==null||siparis.getSiparisKalemList().isEmpty())){
			nullFields.add("siparisKalemList");
		} else {
			//kalem validation
			int sira = 0;
			for (SiparisKalemIn kalem : siparis.getSiparisKalemList()) {
				sira++;
				if(StringUtils.isBlank(kalem.getSiparisKalemAdi())){
					nullFields.add( sira +". kalem için siparisKalemAdi");
				};
				if(StringUtils.isBlank(kalem.getUrunAdi())){
					nullFields.add( sira +". kalem için urunAdi");
				};
				if((kalem.getAdet() == null  || kalem.getAdet().compareTo(BigDecimal.ZERO) == 0)){
					nullFields.add( sira +". kalem için adet");
				};
				if((kalem.getBirimFiyati() == null  || kalem.getBirimFiyati().compareTo(BigDecimal.ZERO) == 0)){
					nullFields.add( sira +". kalem için birimFiyati");
				};
				if((kalem.getAraToplam() == null  || kalem.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
					nullFields.add( sira +". kalem için araToplam");
				};
				if((kalem.getIndirim() == null  || kalem.getIndirim().compareTo(BigDecimal.ZERO) == 0)){
					kalem.setIndirim(BigDecimal.ZERO);
				};
				if((kalem.getAraToplam() == null  || kalem.getAraToplam().compareTo(BigDecimal.ZERO) == 0)){
					nullFields.add( sira +". kalem için araToplam");
				};
			}
		}
		if(StringUtils.isBlank(siparis.getSiparisVerenFirma())&& StringUtils.isBlank(siparis.getSiparisVerenKisi())){
			nullFields.add("siparisVerenFirma ya da siparisVerenKisi");
		};
		if(StringUtils.isBlank(siparis.getTedarikEdenFirma())&& StringUtils.isBlank(siparis.getTedarikEdenKisi())){
			nullFields.add("tedarikEdenFirma ya da tedarikEdenKisi");
		};
		
		if(!nullFields.isEmpty()){
			throw new ValidationError(nullFields.toArray(new String[nullFields.size()]), null);
		}
		
		//if null set zero

		if(siparis.getSiparisDurum() == null){
			siparis.setSiparisDurum("Sipariş Oluşturuldu"); 
		};
		
		if((siparis.getIndirim() == null  || siparis.getIndirim().compareTo(BigDecimal.ZERO) == 0)){
			siparis.setIndirim(BigDecimal.ZERO);
		};

		if(siparis.getSiparisOlusmaTarihi() == null){
			siparis.setSiparisOlusmaTarihi(new Date()); 
		};
		
	}

}
