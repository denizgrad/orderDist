<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header">
	<h3 class="modal-title">Sipariş Bilgileri</h3>
</div>
<div class="modal-body">
	<div>
		<div class="sipars">
			<input type="hidden" name="oid" ng-value="oid" />
			<div class="row">
				<div class="col-md-4 column ui-sortable">
					<div class="box box-element" style="display: block;">
						<div class="view">
								<div class="form-group row">
								  <label class="col-md-5 control-label" for="siparisVerenFirma">Siparişi Veren Firma</label>  
								  <div class="col-md-7">
								  	<span id="siparisVerenFirma" class="form-control input-md"> {{siparisVerenFirma}}</span>
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-md-5 control-label" for="siparisTarihi">Sipariş Tarihi</label>  
								  <div class="col-md-7">
								  <span id="siparisTarihi" class="form-control input-md">{{siparisTarihi}}</span>
								  </div>
								</div>
								<div class="form-group row">
								  <label class="col-md-5 control-label" for="siparisAciklamasi">Sipariş Açıklaması</label>  
								  <div class="col-md-7">
								  <textArea id="siparisAciklamasi" class="form-control input-md" >{{siparisAciklama}}</textArea>
								  </div>
								</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 column ui-sortable">
					<div class="box box-element" style="display: block;">
						
						<div class="view">
		
							<div class="form-group row">
							  <label class="col-md-5 control-label" for="siparisVerenKisi">Siparişi Veren Kişi</label>  
							  <div class="col-md-7">
							  <span id="siparisVerenKisi" class="form-control input-md">{{siparisVerenKisi}}</span>
							    
							  </div>
							</div>
							
							<div class="form-group row">
							  <label class="col-md-5 control-label" for="talepEdilenTeslimTarihi">Talep Edilen Teslim Tarihi</label>  
							  <div class="col-md-7">
							  <span id="talepEdilenTeslimTarihi" class="form-control input-md">{{talepEdilenTeslimTarihi}}</span>
							    
							  </div>
							</div>
						
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 column ui-sortable">
					<div class="box box-element" style="display: block;">
						<div class="view">
							<div class="form-group">
								<label class="col-md-5 control-label" for="adres">Adres</label>
								<div class="col-md-7">
									<textArea class="form-control" id="adres" >{{adres}}</textArea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4 column ui-sortable">
					<div class="box box-element" style="display: block;">
						<div class="view">
							<div class="form-group">
								<label class="col-md-5 control-label" for="adresAciklamasi">Adres Açıklaması</label>
								<div class="col-md-7">
									<textArea class="form-control" id="adresAciklamasi" >{{adresAciklamasi}}</textArea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4 column ui-sortable">
					<div class="box box-element" style="display: block;">
						<div class="view">
							<div class="form-group">
								<label class="col-md-5 control-label" for="genelToplam">Genel Toplam</label>
								<div class="col-md-7">
									<span class="form-control" id="genelToplam" >{{genelToplam}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="detayTable">
				<div ui-grid="teslimatGridOptions" class="siparisDetay"></div>
			</div>
			
		</div>
		
	</div>
</div>
<div class="modal-footer">
    <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
</div>