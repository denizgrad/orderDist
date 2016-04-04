var sipApp = angular.module("siparisModule", ["ui.grid", 'ngAnimate', 'ui.bootstrap']);

sipApp.controller("siparisCtrl", function($scope, $http, $uibModal, $log) {

		getSiparisStatus();	

	function getSiparisStatus() {
		
		// TODO service name ve post
		$http.get('js/app/services/getSiparisStatus.json').success(function(response) {
					debugger;
					$scope.statusData = response.data;
					$scope.selectedStatus = response.selectedData;
					getSiparisList();
					
				}).error(function(error) {

		})
	}
	$scope.gridOptions = {
			enableGridMenu: true,
			rowTemplate: checkWarning( 'row' ),
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false, enableHiding: false},
		                 { field: 'siparisVerenFirma', displayName: "Sipariş Veren Firma", visible: true},
		                 { field: 'siparisVerenKisi', displayName: "Sipariş Veren Kişi", visible: true},
		                 { field: 'tedarikEdenFirma', displayName: "Tedarik Eden Firma", visible: false},
		                 { field: 'tedarikEdenKisi', displayName: "Tedarik Eden Kişi", visible: false},
		                 { field: 'siparisTarihi', displayName: "Sipariş Tarihi", visible: true},
		                 { field: 'teslimTarihi', displayName: "Teslim Tarihi", visible: true},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: false},
		                 { field: 'kdv', displayName: "KDV", visible: false},
		                 { field: 'indirim', displayName: "İndirim", visible: false},
		                 { field: 'genelToplam', displayName: "Genel Toplam", visible: true},
		                 { field: 'adres', displayName: "Adres", visible: false},
		                 { field: 'siparisAciklama', displayName: "Sipariş Açıkama", visible: true},
		                 { field: 'adresAciklama', displayName: "Adres Açıkama", visible: false},
		                 { field: 'talepEdilenTeslimTarihi', displayName: "Talep Edilen Teslim Tarihi", visible: true},
		                 { field: 'siparisDurumu', displayName: "Sipariş Durumu", visible: true},
		                 { field: 'getDetay', displayName: "", visible: true, enableHiding: false, enableSorting: false, enableColumnMenu:false,
		                	 cellTemplate: arrangeButtonGroup('row')
		                		 
		                 }
		               // cellFilter: 'date:"yyyy-MM-dd"'
		    ], 
		    data : [],
		    onRegisterApi: function(gridApi){
		        grid = gridApi;
		    }
		    
	}
	

	function checkWarning(row) {
		return '<div ng-class="{ \'grey\':grid.appScope.rowFormatter( row ) }">'
		+ '  <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>'
		+ '</div>';
	}
	
	function arrangeButtonGroup(row) {
		return '<div class="btn-group">'+

		 '<button type="button" class="btn btn-default" style=" margin-left: 2px;" ng-click="grid.appScope.openPopupSiparisDetayi(row.entity)">'+
		 	'<span class="glyphicon glyphicon-eye-open" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default" ng-click="grid.appScope.openPopupDurumGuncelle(row.entity)">'+
		 	'<span class="glyphicon glyphicon-pencil" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default" style=" margin-right:2px; " ng-click="grid.appScope.openPopupBarkodOlustur(row.entity)">'+
		 	'<span class="glyphicon glyphicon-barcode" ></span>'+
		 '</button>'+
		 '</div>';
	}
	$scope.rowFormatter = function(row) {
		var warn = false;
		
		var now = new Date();
		var delivery = new Date(row.entity.teslimTarihi); 
		
		var diff = (delivery - now)/1000;
	    diff = Math.abs(Math.floor(diff));
	    
	    var days = Math.floor(diff/(24*60*60));
	    var leftSec = diff - days * 24*60*60;
	    
	    var hrs = Math.floor(leftSec/(60*60));
	    var leftSec = leftSec - hrs * 60*60;
	      
	    var min = Math.floor(leftSec/(60));
	    var leftSec = leftSec - min * 60;
	    
	    if((days == 0 && hrs <= 4) || (row.entity.siparisDurumu == 1)){
	    	warn = true;
	    }
		return warn;
	};
	
	function getSiparisList() {
		$http.get('js/app/services/getSiparisList.json').success(
				function(response) {
					debugger;
//					$scope.siparisCollection = response;
//					$scope.siparisList = response.siparisList;
					var len = response.length;
					if(len == 0){
						len = 1;
					}
					$scope.gridOptions.rowHeight = 35 * len + "px",
					$scope.gridOptions.data = response;
					
					
//
//					$scope.siparisTable = new NgTableParams({}, {
//						total : response.siparisList.length,
//						getData : function($defer, params) {
//							debugger;
//							return $defer.resolve($scope.siparisList);
//						}
//					});
				}).error(function(error) {

		})
	}
	
	$scope.openPopupSiparisDetayi = function openPopupSiparisDetayi(siparis) {
		
		debugger;
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupSiparisDetayi.html',
		      controller: 'siparisDetayiInstanceCtrl',
		      size: 'lg',
		      resolve: {
		    	  // açılmadan once
		    	  
		      }
		    });

		    modalInstance.result.then(function () {
		    	// success

		    }, function () {
		    	// error
		      
		    });
		    
	}
	
	$scope.openPopupDurumGuncelle = function openPopupDurumGuncelle(siparis) {
		
		debugger;
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupStatusUpdate.html',
		      controller: 'updateStatusInstanceCtrl',
		      size: 'lg',
		      resolve: {
		    	  // açılmadan once
		    	  // TODO function ı düzelt
		    	  statusData : function(){

		    		  return $scope.statusData;
		    	  }
		      }
		    });

		    modalInstance.result.then(function () {
		    	// success

		    }, function () {
		    	// error
		      
		    });
		    
	}
	$scope.openPopupBarkodOlustur = function openPopupBarkodOlustur(siparis) {
		debugger;
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupBarcode.html',
		      controller: 'barcodeInstanceCtrl',
		      size: 'lg',
		      resolve: {
		    	  // açılmadan once
		    	  
		      }
		    });

		    modalInstance.result.then(function () {
		    	// success

		    }, function () {
		    	// error
		      
		    });
		
	}
	


		  

	/*
	 * $http.get('js/app/services/getTeslimatStatus.js').success(
	 * function(response) { debugger; $scope.statusData = response;
	 * $scope.statusData.selectedOption = response[0].value;
	 * 
	 * }).error(function(error) { debugger;
	 * 
	 * }); debugger;
	 */
});

sipApp.controller('siparisDetayiInstanceCtrl', function($scope, $uibModalInstance, $http) {

	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.getSiparisDetay = function getSiparisDetay(siparis) {

		$http.get('js/app/services/getSiparis.json').success(
				function(response) {
					debugger;
					
					$scope.oid = response.oid;
					$scope.siparisVerenFirma = response.siparisVerenFirma;
					$scope.siparisVerenKisi = response.siparisVerenKisi;
					$scope.tedarikEdenFirma = response.tedarikEdenFirma;
					$scope.tedarikEdenKisi = response.tedarikEdenKisi;
					$scope.siparisTarihi = response.siparisTarihi;
					$scope.teslimTarihi = response.teslimTarihi;
					$scope.araToplam = response.araToplam;
					$scope.kdv = response.kdv;
					$scope.indirim = response.indirim;
					$scope.genelToplam = response.genelToplam;
					$scope.adres = response.adres;
					$scope.siparisAciklama = response.siparisAciklama;
					$scope.adresAciklama = response.adresAciklama;
					$scope.talepEdilenTeslimTarihi = response.talepEdilenTeslimTarihi;
					$scope.siparisDurumu = response.siparisDurumu;
					
					$scope.siparisDetayCollection = response.siparisDetay;
					
				}).error(function(error) {
			debugger;

		});

	}
	
});


sipApp.controller('updateStatusInstanceCtrl', function($scope, $uibModalInstance, statusData) {
debugger;

	$scope.statusData = statusData;
	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.durumGuncelle = function durumGuncelle(siparis) {

		$http.get('js/app/services/updateSiparis.json').success(
		function(response) {
			debugger;
//			$scope.siparisList = response.siparisList;
//
//			$scope.siparisTable = new NgTableParams({}, {
//				total : response.siparisList.length,
//				getData : function($defer, params) {
//					debugger;
//					return $defer.resolve($scope.siparisList);
//				}
//			});
		}).error(function(error) {
	debugger;

})


	}
	
});

sipApp.controller('barcodeInstanceCtrl', function($scope, $uibModalInstance) {

	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.barkodOlustur = function barkodOlustur(siparis) {

		// TODO http call 
		$("#order-barcode").barcode({
			code : "1234567",
			crc : false
		}, "int25", {
			barWidth : 2,
			barHeight : 30
		});

	}
	$scope.yazdir = function yazdir(siparis) {

		// TODO print out
		alert("this gonna be printed");
	}
	
});

