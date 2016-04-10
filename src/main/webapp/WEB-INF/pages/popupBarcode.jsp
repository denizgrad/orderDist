<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="modal-header">
	<h3 class="modal-title">Barkod</h3>
</div>
<div class="modal-body">
	<div>
		<input type="text" name="barkod" ng-model="barcode" required ng-class="{
	                                'has-error':  barkod.$invalid  && barkod.$dirty,
	                                'has-success':barkod.$valid  &&  barkod.$dirty}"
									ng-pattern="/^[0-9]*$/" class="form-control input-md" readonly/>
	</div>
	<fieldset style = "margin-top: 20px; border: 1px solid silver; border-radius: 6px;">
		<div id = "order-barcode-container" class = "center-block" style = "margin-left: 30%; padding-top: 5px;">
			<div id = "order-barcode"></div>
		</div>
	</fieldset>
</div>
<div class="modal-footer">
	<button class="btn btn-primary" type="button" ng-click="yazdir()">Çıktı Al</button>
    <button class="btn btn-warning" type="button" ng-click="cancel()">Kapat</button>
</div>
