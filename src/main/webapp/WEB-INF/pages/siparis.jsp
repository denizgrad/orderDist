<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<title>Sipariş</title>
	
		<script type="text/javascript" src="js/lib/angular/1.5.3/angular.min.js"></script>
		<script type="text/javascript" src="js/lib/angular/1.5.3/angular-animate.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/ui-bootstrap/1.3.1/ui-bootstrap-tpls-1.3.1.min.js"></script>
		
		<link href="css/lib/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet" media="screen">
		
		<script type="text/javascript" src="js/lib/jquery/1.12.3/jquery-1.12.3.min.js"></script>
		<script type="text/javascript" src="js/lib/bootstrap/3.3.6/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/lib/jquery/jquery-barcode.js"></script>
	
		<link rel="styleSheet" href="css/lib/angular/ui-grid/3.1.1/ui-grid.min.css"/>
		<script type="text/javascript" src="js/lib/angular/ui-grid/3.1.1/ui-grid.min.js"></script>
	
		<link rel="styleSheet" href="css/siparis.css"/>
		<script type="text/javascript" src="js/app/controllers/siparis.controller.js"></script>
	
    
	</head>
<body ng-app="siparisModule"  >


	<div class = "logo"></div>

	<div class = "siparis-block" ng-controller="siparisCtrl" >
		<button class="btn btn-primary btn-default sendButton" ng-click = "redirectTeslimat()"> Teslimat </button>
		<div ng-show="loadingList || loadingOrder || loadingStatus" class="loading-container">
			<div class = "loading-inner-container">
				<span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>Yükleniyor...
			</div>
		</div>
	
		<div ui-i18n="{{lang}}" class = "tableDiv">
			<div ui-grid="gridOptions" class="siparisList"></div>
		</div>
	</div>
	
</body>
</html>