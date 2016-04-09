<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script src="js/lib/angular/angular.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="css/ng-table.min.css"> -->
<!-- <script src="js/lib/angular/ng-table.min.js"></script> -->

<link rel="styleSheet" href="js/lib/angular/ui-grid/ui-grid-stable.min.css"/>
<script src="js/lib/angular/ui-grid/ui-grid-stable.min.js"></script>

<!-- <script src="js/lib/angular/smart-table/smart-table.module.js"></script> -->
<!-- <script src="js/lib/angular/smart-table/stTable.js"></script> -->


<script src="js/app/controllers/teslimat.controller.js"></script>

<meta content="text/html; charset=UTF-8">

<title>Sipariş Teslim Et</title>
</head>
<body ng-app="teslimatModule">

	<div ng-controller="teslimatCtrl">
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
								<select name="status" id="status" ng-model="siparisDurum" ng-disabled="true" class="form-control input-md">
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
							<label class="col-md-5 control-label" >Siparişi Teslim Et</label>  
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
								  <label class="col-md-5 control-label" for="siparisOlusmaTarihi">Sipariş Tarihi</label>  
								  <div class="col-md-7">
								  <span id="siparisOlusmaTarihi" class="form-control input-md">{{siparisOlusmaTarihi  | date:'dd-MM-yyyy hh:mm'}}</span>
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
							  <label class="col-md-5 control-label" for="siparisTalepTeslimTarihi">Talep Edilen Teslim Tarihi</label>  
							  <div class="col-md-7">
							  <span id="siparisTalepTeslimTarihi" class="form-control input-md">{{siparisTalepTeslimTarihi | date:'dd-MM-yyyy hh:mm'}}</span>
							    
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
								<label class="col-md-5 control-label" for="adresAciklama">Adres Açıklaması</label>
								<div class="col-md-7">
									<textArea class="form-control" id="adresAciklama" >{{adresAciklama}}</textArea>
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
									<span class="form-control" id="genelToplam" >{{genelToplam | currency}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<hr>



			<div class="detayTable">
				<div ui-grid="teslimatGridOptions" class="siparisDetay"></div>
			</div>
			
		</div>
	</div>
		
</body>
</html>