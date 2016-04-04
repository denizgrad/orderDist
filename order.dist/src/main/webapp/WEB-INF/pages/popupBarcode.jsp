<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header">
	<h3 class="modal-title">Barkod</h3>
</div>
<div class="modal-body">
	<div>
		<div id = "order-barcode"></div>
	</div>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" type="button" ng-click="barkodOlustur()">Barkod Oluştur</button>
	<button class="btn btn-primary" type="button" ng-click="yazdir()">Çıktı Al</button>
    <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
</div>
