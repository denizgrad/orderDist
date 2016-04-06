<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Sipariş</title>

	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script>
	<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-1.2.5.js"></script>
	
	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jquery-ui-1.9.2/js/jquery-barcode.js"></script>

	<link rel="styleSheet" href="js/lib/angular/ui-grid/ui-grid-stable.min.css"/>
	<script src="js/lib/angular/ui-grid/ui-grid-stable.min.js"></script>

	<link rel="styleSheet" href="css/siparis.css"/>
	<script src="js/app/controllers/siparis.controller.js"></script>
	
    
</head>
<body ng-app="siparisModule" >

	<div ng-controller="siparisCtrl">
		<div class="siparisTable" class="container">
			
			<div>
				<div ui-grid="gridOptions" class="grid"></div>
			</div>
			
		</div>
	</div>

</body>
</html>