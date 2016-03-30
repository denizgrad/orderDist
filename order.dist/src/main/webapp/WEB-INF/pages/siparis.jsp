<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Sipariş</title>

	<script src="js/lib/angular/angular.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="css/ng-table.min.css">
	<script src="js/lib/angular/ng-table.min.js"></script>
	<script src="js/app/controllers/siparis.controller.js"></script>


</head>
<body ng-app="siparisModule">

	<div ng-controller="siparisCtrl">
		<div class="siparisTable" class="container" ng-controller="popupCtrl">
			<modal visible="showModal">
			</modal>
			<table ng-table ="siparisTable" class="table table-bordered table-striped">
				<tr ng-repeat="siparis in siparisList">
					<td title="'Oid'">{{siparis.oid}}</td>
					<td title="'Sipariş Zamanı'">{{siparis.orderDate | date}}</td>
					<td title="'Barkod'">{{siparis.barcodeNumber}}</td>
					<td title="'Teslim Alacak Kişi'">{{siparis.deliveryPerson}}</td>
					<td title="'Teslim Alan Tel No'">{{siparis.receiverPhoneNumber}}</td>
					<td title="'Teslim Tarihi'">{{siparis.deliveryDate}}</td>
					<td title="'Sipariş Durumu'">
<!-- 						<select name="status" id="status" ng-model= "{{siparis.status}}"> -->
<!-- 							<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option> -->
<!-- 						</select> -->
					</td>
					<td>
						<button ng-click="durumGuncelle(siparis)">Sipariş Durum Güncelle</button>
					</td>
					<td>
						<button type="button" class="btn btn-default" aria-label="Left Align" ng-click="detayGoruntule(siparis)">
							<span class="glyphicon glyphicon-align-justify" aria-hidden="true"></span>
						</button> 
					</td>
					<td>
						<button ng-click="barkodOlustur(siparis)">Barkod Oluştur</button>
					</td>
				</tr>
			</table>

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