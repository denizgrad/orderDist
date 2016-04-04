<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header">
	<h3 class="modal-title">Barkod</h3>
</div>
<div class="modal-body">
	<div>
		
				<h2>Teslimat</h2>
		
		
		<form name="form" ng-submit="teslimEt(form)" role="form">
		<div class="row">
			<div class="col-md-4 column ui-sortable">
				<div class="box box-element" style="display: block;">
					<div class="view">
						<div class="form-group">
							<label class="col-md-5 control-label" for="barkod">Barkod</label>  
							<div class="col-md-7">
								<input type="text" name="barkod" ng-model="barkod" required ng-blur="getSiparis(form)" ng-class="{
	                                'has-error':  form.barkod.$invalid  && form.barkod.$dirty,
	                                'has-success':form.barkod.$valid  &&  form.barkod.$dirty}"
									ng-pattern="/^[0-9]*$/" ng-minlength={{barLen}}
									maxlength={{barLen}} class="form-control input-md"/>
								<div ng-show="form.barkod.$error.required && form.barkod.$dirty">
									<span class="help-block">Lütfen barkod numarası giriniz.</span>
								</div>
								<div ng-show="form.barkod.$error.minlength &&  form.barkod.$dirty">
									<span class="help-block">Geçerli bir barkod numarası giriniz.</span>
								</div>
								<div ng-show="form.barkod.$error.pattern && form.barkod.$dirty">
									<span class="help-block">Barko numarası sadece sayı içerebilir.</span>
								</div>
							</div>
						</div>
						</div>
				</div>
			</div>
						<div class="col-md-4 column ui-sortable">
				<div class="box box-element" style="display: block;">
					<div class="view">
						<div class="form-group">
							<label class="col-md-5 control-label" for="status">Sipariş Durumu</label>  
							<div class="col-md-7">
								<select name="status" id="status" ng-model="statusData.selectedOption" ng-disabled="true" class="form-control input-md">
									<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>
								</select>
							</div>
						</div>
						</div>
				</div>
			</div>
			<div class="col-md-4 column ui-sortable">
				<div class="box box-element" style="display: block;">
					<div class="view">
						<div class="form-group">
							<label class="col-md-5 control-label" for="siparisAciklamasi">Siparişi Teslim Et</label>  
							<div class="col-md-7">
								<div class="form-actions">
									<button type="submit" class="btn btn-primary btn-default" ng-disabled="form.$invalid || vm.dataLoading"  >Teslim Et</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>

		<div class="sipars">
			<h3>Sipariş Bilgileri</h3>
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

			<hr>



			<div class="detayTable">
				<div ui-grid="gridOptions" class="siparisDetay"></div>
			</div>
			
		</div>
		
		
	</div>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" type="button" ng-click="getSiparisDetay()">Barkod Oluştur</button>
    <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
</div>