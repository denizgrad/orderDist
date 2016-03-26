<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<script src="js/lib/angular/angular.min.js"></script>
<script src="js/app/controllers/teslimat.controller.js"></script>


<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body ng-app="teslimatModule">
	<div ng-controller="tes">

		<h2>Teslimat</h2>
		<form name="form"
			ng-submit="teslimEdildi(vm.barkod, data.selectedOption)" role="form">
			<div class="form-group">
				<label for="barkod">Barkod</label> <input type="text" name="barkod"
					ng-model="vm.barkod" required />
			</div>
			<!-- TODO disabled="disabled" -->
			<label for="status"> Sipariş Durumu: </label> <select name="status"
				id="status" ng-model="data.selectedOption">
				<!-- 				default data vermek için ng-module kullan -->
				<option ng-repeat="option in data" value="{{option.key}}">{{option.value}}</option>
			</select>

			<div class="form-actions">
				<button type="submit" ng-disabled="form.$invalid || vm.dataLoading"
					class="btn btn-primary">Teslim Et</button>
			</div>
		</form>

		<button type="submit" ng-click="asdad()">Dummy</button>
	</div>




</body>
</html>