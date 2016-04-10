<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	
		<meta content="text/html; charset=UTF-8">
		<title>Sipariş Teslim Et</title>
		
		<script src="js/lib/angular/1.5.3/angular.min.js"></script>
		<link href="css/lib/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="js/lib/jquery/1.12.3/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/3.3.6/bootstrap.min.js" ></script>
		
		<link rel="styleSheet" href="css/lib/angular/ui-grid/3.1.1/ui-grid.min.css"/>
		<script type="text/javascript" src="js/lib/angular/ui-grid/3.1.1/ui-grid.min.js"></script>
		
		<link rel="styleSheet" href="css/teslimat.css"/>
		<script src="js/app/controllers/teslimat.controller.js"></script>

	</head>
<body ng-app="teslimatModule">

	<div class = "logo"></div>

	<div ng-controller="teslimatCtrl" class = "teslimat-block">

		<form name="form" ng-submit="teslimEt(form)" role="form">
			<fieldset>
				<div class="column">
					<label class="col-sm-2 control-label" for="barkod">Barkod</label>  
					<div class="col-sm-4">
						<input type="text" name="barkod" ng-model="barkod" required ng-blur="getSiparis(form)" ng-class="{
		                                'has-error':  form.barkod.$invalid  && form.barkod.$dirty,
		                                'has-success':form.barkod.$valid  &&  form.barkod.$dirty}"
										ng-pattern="/^[0-9]*$/" class="form-control input-sm"/>
						<div ng-show="form.barkod.$error.required && form.barkod.$dirty" class="alert alert-danger">
							<span> <strong>Lütfen barkod numarası giriniz. </strong></span>
						</div>
						<div ng-show="form.barkod.$error.minlength &&  form.barkod.$dirty" class="alert alert-danger">
							<span> <strong>Geçerli bir barkod numarası giriniz. </strong></span>
						</div>
						<div ng-show="form.barkod.$error.pattern && form.barkod.$dirty" class="alert alert-danger">
							<span ><strong>Barko numarası sadece sayı içerebilir.</strong></span>
						</div>
					</div>
				</div>
				<div class="column">
					<label class="col-sm-2 control-label" for="status">Sipariş Durumu</label>  
					<div class="col-sm-2">
						<select name="status" id="status" ng-model="siparisDurum" ng-disabled="true" class="form-control input-sm">
							<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>
						</select>
					</div>
					<div class="col-sm-2">
						<button type="submit" class="btn btn-primary btn-default sendButton" ng-disabled="form.$invalid || vm.dataLoading"  >Teslim Et</button>
					</div>
				</div>
				
			</fieldset>
		</form>
		
		<fieldset>
			<legend class="title"> Sipariş Bilgileri</legend>
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
				<legend class="title"> Adres </legend>
				<div class="column">
					<label class="col-sm-2 control-label" for="adres">Adres</label>
					<div class="col-lg-10">
						<textArea id="adres" class="form-control col-lg-4" readonly>{{adres}}</textArea>
					</div>
				</div>
			</fieldset>
			
			<fieldset class = "form-group">
				<legend class="title"> Açıklamalar </legend>
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
				<legend class="title"> Genel Tolam </legend>
				<div class="column" style = "display: flex; justify-content: flex-end;">
					<div class="col-sm-4">
						<span class="form-control input-sm" id="genelToplam" readonly>{{genelToplam | currency}}</span>
					</div>
				</div>
			</fieldset>
		</fieldset>

		<div ui-i18n="{{lang}}" class = "tableDiv">
			<div ui-grid="teslimatGridOptions" class="siparisDetay"></div>
		</div>
			
	</div>
		
</body>
</html>