<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Sipariş</title>

	<script src="js/lib/angular/angular.min.js"></script>
	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	
<!-- 	<link rel="stylesheet" href="css/ng-table.min.css"> -->
<!-- 	<script src="js/lib/angular/ng-table.min.js"></script> -->
	<link rel="styleSheet" href="js/lib/angular/ui-grid/ui-grid-stable.min.css"/>
	<script src="js/lib/angular/ui-grid/ui-grid-stable.min.js"></script>

<!-- 	<script src="js/lib/angular/smart-table/smart-table.module.js"></script> -->
<!-- 	<script src="js/lib/angular/smart-table/stTable.js"></script> -->
	
	<script src="js/app/controllers/siparis.controller.js"></script>


</head>
<body ng-app="siparisModule" >

	<div ng-controller="siparisCtrl">
		<div class="siparisTable" class="container" ng-controller="popupCtrl">
			<modal visible="showModal">
			</modal>
			
			<div>
				<div ui-grid="gridOptions"></div>
			</div>
			
			
<!-- 			<table ng-table ="siparisTable" class="table table-bordered table-striped"> -->
<!-- 				<tr ng-repeat="siparis in siparisList"> -->
<!-- 					<td title="'Oid'">{{siparis.oid}}</td> -->
<!-- 					<td title="'Sipariş Zamanı'">{{siparis.orderDate | date}}</td> -->
<!-- 					<td title="'Barkod'">{{siparis.barcodeNumber}}</td> -->
<!-- 					<td title="'Teslim Alacak Kişi'">{{siparis.deliveryPerson}}</td> -->
<!-- 					<td title="'Teslim Alan Tel No'">{{siparis.receiverPhoneNumber}}</td> -->
<!-- 					<td title="'Teslim Tarihi'">{{siparis.deliveryDate}}</td> -->
<!-- 					<td title="'Sipariş Durumu'"> -->
<!-- <!-- 						<select name="status" id="status" ng-model= "{{siparis.status}}"> -->
<!-- <!-- 							<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option> -->
<!-- <!-- 						</select> --> 
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<button ng-click="durumGuncelle(siparis)">Sipariş Durum Güncelle</button> -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<button type="button" class="btn btn-default" aria-label="Left Align" ng-click="detayGoruntule(siparis)"> -->
<!-- 							<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span> -->
<!-- 						</button>  -->
<!-- 					</td> -->
<!-- 					<td> -->
<!-- 						<button ng-click="barkodOlustur(siparis)">Barkod Oluştur</button> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->

<!-- 			<div> -->
<!-- 				<table st-table="rowCollection" class="table table-striped"> -->
<!-- 					<thead> -->
<!-- 					<tr> -->
<!-- 							<th ng-hide="true">oid</th> -->
<!-- 							<th>Siparişi Veren Firma</th> -->
<!-- 							<th>Siparişi Veren Kişi</th> -->
<!-- 							<th>Tedarik Eden Firma</th> -->
<!-- 							<th>Tedarik Eden Kişi</th> -->
<!-- 							<th>Sipariş Tarihi</th> -->
<!-- 							<th>Teslim Tarihi</th> -->
<!-- 							<th>Ara Toplam</th> -->
<!-- 							<th>KDV</th> -->
<!-- 							<th>İndirim</th> -->
<!-- 							<th>Genel Toplam</th> -->
<!-- 							<th>Adres</th> -->
<!-- 							<th>Sipariş Açıklama</th> -->
<!-- 							<th>Adres Açıklama</th> -->
<!-- 							<th>Talep Edilen Teslim Tarihi</th> -->
<!-- 							<th>Sipariş Durumu</th> -->

<!-- 						</tr> -->
<!-- 					</thead> -->
<!-- 					<tbody> -->
<!-- 						<tr ng-repeat="siparis in siparisCollection"  ng-class=""> -->
							
<!-- 							<td ng-hide="true">{{siparis.oid}}</td> -->
<!-- 							<td>{{siparis.siparisVerenFirma}}</td> -->
<!-- 							<td>{{siparis.siparisVerenKisi}}</td> -->
<!-- 							<td>{{siparis.tedarikEdenFirma}}</td> -->
<!-- 							<td>{{siparis.tedarikEdenKisi}}</td> -->
<!-- 							<td>{{siparis.siparisTarihi}}</td> -->
<!-- 							<td>{{siparis.teslimTarihi}}</td> -->
<!-- 							<td>{{siparis.araToplam}}</td> -->
<!-- 							<td>{{siparis.kdv}}</td> -->
<!-- 							<td>{{siparis.indirim}}</td> -->
<!-- 							<td>{{siparis.genelToplam}}</td> -->
<!-- 							<td>{{siparis.adres}}</td> -->
<!-- 							<td>{{siparis.siparisAciklama}}</td> -->
<!-- 							<td>{{siparis.adresAciklama}}</td> -->
<!-- 							<td>{{siparis.talepEdilenTeslimTarihi}}</td> -->
<!-- 							<td> -->
<!-- 								{{siparis.siparisDurumu}} -->
<!-- 								<select ng-model="siparis.siparisDurumu" required="required" ng-init="siparis.siparisDurumu = siparis.siparisDurumu"> -->
<!-- 									<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option> -->
<!-- 							    </select> -->
<!-- <!-- 								<select ng-model="siparis.siparisDurumu" required="required" ng-init="siparis.siparisDurumu='bug'" --> -->
<!-- <!-- 								  ng-options="option.value as option.key for option in statusData" > --> -->
<!-- <!-- 								</select> --> -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<button ng-click="detayGoruntule(siparis)"> detay</button> -->
<!-- 							</td> -->
<!-- 							<td> -->
<!-- 								<button ng-click="durumGuncelle(siparis)"> durum güncelle</button> -->
<!-- 							</td> -->
							
<!-- 						</tr> -->
<!-- 					</tbody> -->
<!-- 				</table>	 -->
<!-- 			</div> -->
		</div>
	</div>
<!-- 	<div ng-controller="basicsCtrl"> -->
<!-- 		<table st-table="rowCollection" class="table table-striped"> -->
<%-- 		<colgroup> --%>
<%-- 				          <col width="60%" /> --%>
<%-- 				          <col width="20%" /> --%>
<%-- 				          <col width="10%" /> --%>
<%-- 				          <col width="5%" /> --%>
<%-- 				          <col width="5%" /> --%>
				          
<%-- 				        </colgroup> --%>
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th>first name</th> -->
<!-- 					<th>last name</th> -->
<!-- 					<th>birth date</th> -->
<!-- 					<th>balance</th> -->
<!-- 					<th>email</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<!-- 				<tr ng-repeat="row in rowCollection"> -->
<!-- 					<td>{{row.firstName | uppercase}}</td> -->
<!-- 					<td>{{row.lastName}}</td> -->
<!-- 					<td>{{row.birthDate | date:"dd/MM/yyyy HH:mm:ss"}}</td> -->
<!-- 					<td>{{row.balance | currency}}</td> -->
<!-- 					<td>{{row.email}}</td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 	</div> -->




</body>
</html>