<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
					<span id="siparisVerenFirma" class="form-control input-sm" readonly> {{siparisVerenFirma}}</span>
				</div>
			</div>
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisVerenKisi">Siparişi Veren Kişi</label>  
				<div class="col-sm-4">
					<span id="siparisVerenKisi" class="form-control input-sm" readonly>{{siparisVerenKisi}}</span>
				</div>
			</div>
	
		</fieldset>
		
		<fieldset class = "form-group">
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisOlusmaTarihi">Sipariş Tarihi</label>  
				<div class="col-sm-4">
					<span id="siparisOlusmaTarihi" class="form-control input-sm" readonly>{{siparisOlusmaTarihi | date:'dd-MM-yyyy hh:mm'}}</span>
				</div>
			</div>
			
			<div class="column">
				<label class="col-sm-2 control-label" for="siparisTalepTeslimTarihi">Talep Edilen Teslim Tarihi</label>  
				<div class="col-sm-4">
					<span id="siparisTalepTeslimTarihi" class="form-control input-sm" readonly>{{siparisTalepTeslimTarihi | date:'dd-MM-yyyy hh:mm'}}</span>
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
			<legend> Genel Tolam </legend>
			<div class="column" style = "display: flex; justify-content: flex-end;">
				<div class="col-sm-4">
					<span class="form-control input-sm" id="genelToplam" readonly>{{genelToplam | currency}}</span>
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