<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header">
	<h3 class="modal-title">Sipariş Durumu</h3>
</div>
<div class="modal-body">
	<div>
		<select ng-model="statusData.selectedOption">
			<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>
		</select>
	</div>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" type="button" ng-click="durumGuncelle(statusData.selectedOption)">Durum Güncelle</button>
    <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
</div>
