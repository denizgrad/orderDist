var sipApp = angular.module("siparisModule", ["ui.grid", 'ngAnimate', 'ui.bootstrap']);

sipApp.controller("siparisCtrl", function($scope, $http, $uibModal, $log) {

	getSiparisList();	

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
			rowTemplate: rowTemplate( 'row' ),
			enableHorizontalScrollbar : 2,
			enableVerticalScrollbar : 2,
			enableColumnResizing: true,
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
	

	function rowTemplate(row) {
		return '<div ng-class="{ \'grey\':grid.appScope.rowFormatter( row ) }" ng-dblclick="grid.appScope.openPopupSiparisDetayi(row.entity)">'
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
		var delivery = new Date(row.entity.talepEdilenTeslimTarihi); 
		
		var diff = (delivery - now)/1000;
		if(diff < 0){
			warn = true;
		}
	    diff = Math.abs(Math.floor(diff));
	    
	    var days = Math.floor(diff/(24*60*60));
	    var leftSec = diff - days * 24*60*60;
	    
	    var hrs = Math.floor(leftSec/(60*60));
	    var leftSec = leftSec - hrs * 60*60;
	      
	    var min = Math.floor(leftSec/(60));
	    var leftSec = leftSec - min * 60;
	    debugger;
	    if((days <= 0 && hrs <= 4) || (row.entity.siparisDurumu == 1)){
	    	warn = true;
	    }
	    // TODO teslim edilmedi ise
		return warn;
	};
	
	function getSiparisList() {
//		$http.get('js/app/services/getSiparisList.json').success(
		$http.post('v1/siparis/islem/getOrderList/123456789').success(
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
		
//		$http.post('v1/siparis/islem/yarat', {
//			"key" : "asd",
//			"value" : "asd"
//		}).success(function(response) {
//			debugger;
//
//		});
	}
	
	$scope.openPopupSiparisDetayi = function openPopupSiparisDetayi(row) {
		
		debugger;
		var oid = row.oid;
		if(oid === null || "" == oid){
			console.log("siparisOid bos");
		}else {
			var modalInstance = $uibModal.open({
			      animation: true,
			      templateUrl: 'popupSiparisDetayi.html',
			      controller: 'siparisDetayiInstanceCtrl',
			      size: 'lg',
			      resolve: {
			    	  // açılmadan once parametre geçirir
			    	 siparisOid : function(){

			    		  return oid;
			    	  } 
			      }
			    });

			    modalInstance.result.then(function () {
			    	// success

			    }, function () {
			    	// error
			      
			    });
		}
	    
		    
	}
	
	$scope.openPopupDurumGuncelle = function openPopupDurumGuncelle(row) {
		
		debugger;
		// TODO null chceck
		var status = row.siparisDurumu;
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupStatusUpdate.html',
		      controller: 'updateStatusInstanceCtrl',
		      size: 'lg',
		      resolve: {
		    	  // açılmadan once
		    	  
		    	  status : function(){

		    		  return status;
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

sipApp.controller('siparisDetayiInstanceCtrl', function($scope, $uibModalInstance, $http, siparisOid) {

	getSiparisDetay(siparisOid);
	
	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.teslimatGridOptions = {
			//enableSorting: true,
			enableGridMenu: true,
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false, enableHiding: false},
		                 { field: 'siparisOid', displayName: "siparisOid", visible: false, enableHiding: false},
		                 { field: 'siparisKalemAdi', displayName: "Ürün Adi", visible: true},
		                 { field: 'adet', displayName: "Miktar", visible: true},
		                 { field: 'birimFiyat', displayName: "Birim Fiyat", visible: true},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: true},
		                 { field: 'indirim', displayName: "İndirim", visible: true},
		                 { field: 'kalemFiyat', displayName: "Ürün Fiyatı", visible: true}
		    ], 
		    data : []
		    
	}

	function getSiparisDetay(siparisOid) {
		debugger;
		if(siparisOid === null || "" == siparisOid){
			console.log("siparisOid bos");
		}else {
			$http.get('v1/siparis/islem/getOrderByOid', {oid : siparisOid}).success(
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
						
						$scope.teslimatGridOptions.data = response.siparisDetay;
						
					}).error(function(error) {
				debugger;

			});
		}
	}
});


sipApp.controller('updateStatusInstanceCtrl', function($scope, $http, $uibModalInstance, status, statusRepoService) {
debugger;

	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	var onFetchError = function(message) {
		$scope.error = "Error Fetching Users. Message:" + message;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = status + "";
		
	};

	var getStatus = function() {
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();     
	     
	
	
/*
 * 
 * $http({ method: 'GET', url: '/Admin/GetTestAccounts', data: { applicationId:
 * 3 } }).success(function (result) { $scope.testAccounts = result; });
 */
	$scope.durumGuncelle = function durumGuncelle(statu) {
debugger;
// TODO nullcheck

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

	$scope.barkodOlustur = function barkodOlustur(barcode) {

		// TODO http call 
		// TODO null check
		$("#order-barcode").barcode({
			code : barcode,
			crc : false
		}, "int25", {
			barWidth : 2,
			barHeight : 30
		});

	}
	$scope.yazdir = function yazdir() {
		
		var newWindow = window.open();
	    newWindow.document.write(document.getElementById("order-barcode-container").innerHTML);
	    newWindow.print();
	}
	
});


//------------------------ COMBOBOX SERVICE ------------------------


var statusRepoService = function($http){
    
    var getStatus = function(){
          return $http.get("js/app/services/getSiparisStatus.json")
                      .then(function(response){
                         return response.data.data; 
                      });
    };

    return {
        get: getStatus
    };
      
  };
  sipApp.factory("statusRepoService", statusRepoService);
  
