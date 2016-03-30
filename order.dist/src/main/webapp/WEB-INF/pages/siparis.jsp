<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Sipariş</title>

	<script src="js/lib/angular/angular.min.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="js/lib/bootstrap/bootstrap.min.js" type="text/javascript"></script>
	<link rel="stylesheet" href="css/ng-table.min.css">
	<script src="js/lib/angular/ng-table.min.js"></script>
	<script src="js/app/controllers/siparis.controller.js"></script>


</head>
<body ng-app="siparisModule">

	<div class = "siparisTable" ng-controller="siparisCtrl">
		<table ng-table="siparisTable" class="table table-bordered table-striped">
			<tr ng-repeat="siparis in siparisList">
				<td title="'Oid'">{{siparis.oid}}</td>
				<td title="'Sipariş Zamanı'">{{siparis.orderDate}}</td>
				<td title="'Barkod'">{{siparis.barcodeNumber}}</td>
				<td title="'Teslim Alacak Kişi'">{{siparis.deliveryPerson}}</td>
				<td title="'Teslim Alan Tel No'">{{siparis.receiverPhoneNumber}}</td>
				<td title="'Teslim Tarihi'">{{siparis.deliveryDate}}</td>


				<td>
					<button ng-click = "durumGuncelle(siparis)">Sipariş Durum Güncelle</button>
				</td>				
				<td>
					<button ng-click = "detayGoruntule(siparis)">Sipariş Detay</button>
				</td>
				<td>
					<button ng-click = "barkodOlustur(siparis)">Barkod Oluştur</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>