<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header" style = "display: flex; justify-content: center;">
	<h3 class="modal-title">Sipariş Durumu</h3>
</div>

<div class="modal-body">
	<div style = "display: flex; justify-content: center;">
		<select ng-model="statusData.selectedOption" >
			<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>
		</select>
	</div>
</div>
<div class="modal-footer" style ="display: flex; justify-content: center;">
	<button class="btn btn-primary" type="button" ng-click="durumGuncelle(statusData.selectedOption)">Durum Güncelle</button>
    <button class="btn btn-warning" type="button" ng-click="cancel()">Kapat</button>
</div>
