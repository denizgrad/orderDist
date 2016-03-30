<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<script src="js/lib/angular/angular.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/ng-table.min.css">
<script src="js/lib/angular/ng-table.min.js"></script>
<script src="js/app/controllers/teslimat.controller.js"></script>

<meta charset="utf-8">
<title>Sipariş Teslim Et</title>
</head>
<body ng-app="teslimatModule">

	<div ng-controller="teslimatCtrl">
		<h2>Teslimat</h2>
		<form name="form" ng-submit="teslimEt(form)" role="form">
			<div class="form-group">
				<label for="barkod">Barkod</label> <input type="text" name="barkod"
					ng-model="barkod" required ng-blur="getSiparisDetay(form)"
					ng-class="{
                                'has-error':  form.barkod.$invalid  && form.barkod.$dirty,
                                'has-success':form.barkod.$valid  &&  form.barkod.$dirty}"
					ng-pattern="/^[0-9]*$/" ng-minlength={{barLen}}
					maxlength={{barLen}} />



				<div class=""
					ng-show="form.barkod.$error.required && form.barkod.$dirty">
					<span class="help-block">Please enter order number</span>
				</div>
				<div class=""
					ng-show="form.barkod.$error.minlength &&  form.barkod.$dirty">
					<span class="help-block">Please enter valid routing Number</span>
				</div>
				<div class=""
					ng-show="form.barkod.$error.pattern && form.barkod.$dirty">
					<span class="help-block">order number contains only Numbers</span>
				</div>

			</div>
			<!-- TODO disabled="disabled" -->
			<label for="status"> Sipariş Durumu: </label>
			 <select name="status" id="status" ng-model="statusData.selectedOption" ng-disabled="true">
				<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>
			</select>

			<div class="form-actions">
				<button type="submit" ng-disabled="form.$invalid || vm.dataLoading"
					class="btn btn-primary">Teslim Et</button>
			</div>

		</form>

		<div class="siparsi">
			<h3>Sipariş Detayı</h3>
			<div class="adres">adres : {{siparisAdres}}</div>
			<div class="tutar">Toplam Tutar: {{siparisToplamTutar}}</div>
			<div class="detayTable">
				<table ng-table="siparisDetayTable" class="table table-bordered table-striped">
				        <colgroup>
				          <col width="60%" />
				          <col width="20%" />
				          <col width="20%" />
				        </colgroup>
					<tr ng-repeat="detay in siparisDetay">
						<td title="'Ürün Kodu'">{{detay.urunKodu}}</td>
						<td title="'Miktar'">{{detay.miktar}}</td>
						<td title="'Tutar'">{{detay.birimTutar}}</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
		
</body>
</html>