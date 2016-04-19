<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div ng-show="loadingList || loadingOrder || loadingStatus" class="loading-container">
	<div class = "loading-inner-container">
		<span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>Yükleniyor...
	</div>
</div>
	
<div class="modal-header">
	<div style = "display: flex; justify-content: flex-end;">
		<button type="button" ng-click="cancel()">X</button>
	</div>
	<h3 class="modal-title">Sipariş Bilgileri</h3>
</div>
<div class="modal-body">

	<form>
		<input type="hidden" name="oid" ng-value="oid" />
		<fieldset class = "form-group">
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisVerenFirma">Siparişi Veren Firma</label>  
				<div class="col-sm-4">
					<input type = "text"  id="siparisVerenFirma" class="form-control input-sm" readonly ng-model = "siparisVerenFirma"/>
				</div>
			</div>
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisVerenKisi">Siparişi Veren Kişi</label>  
				<div class="col-sm-4">
					<input type = "text"  id="siparisVerenKisi" class="form-control input-sm" readonly ng-model = "siparisVerenKisi"/>
				</div>
			</div>
	
		</fieldset>
		
		<fieldset class = "form-group">
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisOlusmaTarihi">Sipariş Tarihi</label>  
				<div class="col-sm-4">
					<input type = "text"  id="siparisOlusmaTarihi" class="form-control input-sm" readonly ng-model = "siparisOlusmaTarihi | date:'dd-MM-yyyy hh:mm'"/>
				</div>
			</div>
			
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisTalepTeslimTarihi">Talep Edilen Teslim Tarihi</label>  
				<div class="col-sm-4">
					<input type = "text"  id="siparisTalepTeslimTarihi" class="form-control input-sm" readonly ng-model = "siparisTalepTeslimTarihi | date:'dd-MM-yyyy hh:mm'"/>
				</div>
			</div>
		
		</fieldset>
		
		<fieldset class = "form-group">
			<legend> Adres </legend>
			<div class="column">
				<label class="col-sm-2 control-label" for="adres">Adres</label>
				<div class="col-lg-10">
					<textArea id="adres" class="form-control col-lg-4" readonly>{{adres}}</textArea>
				</div>
			</div>
		</fieldset>
		
		<fieldset class = "form-group">
			<legend> Açıklamalar </legend>
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisAciklama">Sipariş Açıklaması</label>  
				<div class="col-lg-4">
					<textArea id="siparisAciklama" class="form-control input-lg-4" readonly>{{siparisAciklama}}</textArea>
				</div>
			</div>
			
			<div class="column">
				<label class="col-sm-2 control-label" for="adresAciklama">Adres Açıklaması</label>
				<div class="col-lg-4">
					<textArea id="adresAciklama"  class="form-control input-lg-4" readonly>{{adresAciklama}}</textArea>
				</div>
			</div>
		
		</fieldset>
		
		<fieldset class = "form-group">
			<legend> Genel Toplam </legend>
			<div class="column" style = "display: flex; justify-content: flex-end;">
				<div class="col-sm-4">
					<input type = "text"  class="form-control input-sm" id="genelToplam" readonly ng-model = "genelToplam | currency"/>
				</div>
			</div>
		</fieldset>
	</form>

	<div class="detayTable">
		<div ui-grid="teslimatGridOptions" class="siparisDetay"></div>
	</div>
			
</div>

<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="cancel()">Kapat</button>
</div>