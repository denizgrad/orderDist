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
	
		<link rel="styleSheet" href="css/lib/angular/ui-grid/ui-grid-stable.min.css"/>
		<script type="text/javascript" src="js/lib/angular/ui-grid/ui-grid-stable.min.js"></script>
	
		<link rel="styleSheet" href="css/siparis.css"/>
		<script type="text/javascript" src="js/app/controllers/siparis.controller.js"></script>
	
    
	</head>
<body ng-app="siparisModule">

	<header> <div> <div class = "logo"></div> </div></header>
	
<!-- <div class = "logo"></div>	 -->
		<div ng-controller="siparisCtrl">
			<div class="siparisTable" class="container">
				
				<div>
					<div ui-grid="gridOptions" class="grid"></div>
				</div>
				
			</div>
		</div>
	
<footer>
</footer>

</body>
</html>