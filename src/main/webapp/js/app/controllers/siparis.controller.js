var sipApp = angular.module("siparisModule", ["ui.grid", 'ngAnimate', 'ui.bootstrap']);

sipApp.controller("siparisCtrl", function($scope, $http, $uibModal, $log, statusRepoService) {

	setInterval(function(){ getSiparisList(); }, 1000*60*2);
	
	var onFetchError = function(message) {
		$scope.error = "Error Fetching Order statuses. Message:" + message;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = "";
		getSiparisList();
		
	};

	var getStatus = function() {
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();     
	
	

	$scope.gridOptions = {
			enableGridMenu: true,
			rowTemplate: rowTemplate( 'row' ),
			enableHorizontalScrollbar : 2,
			enableVerticalScrollbar : 2,
			enableColumnResizing: true,
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false, enableHiding: false},
		                 { field: 'siparisAdi', displayName: "Sipariş Adı", visible: false},
		                 { field: 'siparisVerenFirma', displayName: "Sipariş Veren Firma", visible: true},
		                 { field: 'siparisVerenKisi', displayName: "Sipariş Veren Kişi", visible: true},
		                 { field: 'tedarikEdenFirma', displayName: "Tedarik Eden Firma", visible: false},
		                 { field: 'tedarikEdenKisi', displayName: "Tedarik Eden Kişi", visible: false},
		                 { field: 'siparisOlusmaTarihi', displayName: "Sipariş Tarihi", visible: true, cellFilter: 'date:\'dd-MM-yyyy hh:mm\''},
		                 { field: 'siparisTeslimTarihi', displayName: "Teslim Tarihi", visible: true, cellFilter: 'date:\'dd-MM-yyyy hh:mm\'' },
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: false, cellFilter: 'currency'},
		                 { field: 'kdv', displayName: "KDV", visible: false},
		                 { field: 'indirim', displayName: "İndirim", visible: false, cellFilter: 'currency'},
		                 { field: 'genelToplam', displayName: "Genel Toplam", visible: true, cellFilter: 'currency'},
		                 { field: 'adres', displayName: "Adres", visible: false},
		                 { field: 'siparisAciklama', displayName: "Sipariş Açıkama", visible: true},
		                 { field: 'adresAciklama', displayName: "Adres Açıkama", visible: false},
		                 { field: 'siparisTalepTeslimTarihi', displayName: "Talep Edilen Teslim Tarihi", visible: true, cellFilter: 'date:\'dd-MM-yyyy hh:mm\''},
		                 { field: 'siparisDurum', displayName: "Sipariş Durumu", visible: true, cellTemplate: arrangeCombobox('row')},
		                 { field: 'btnGroup', displayName: "", visible: true, enableHiding: false, enableSorting: false, enableColumnMenu:false,
		                	 cellTemplate: arrangeButtonGroup('row'), minWidth : "150", width:"150"
		                		 
		                 }
		    ], 
		    data : [],
		    onRegisterApi: function(gridApi){
		        grid = gridApi;
		    }
		    
	}
	function arrangeCombobox(row) {

		return '<div>'
					+'<select ng-disabled = "true" ng-model = "row.entity.siparisDurum" style = "width:100%; height:32px">'
					+	'<option ng-repeat = "option in grid.appScope.statusData" value="{{option.value}}">{{option.key}}</option>'
					+'</select>'
				+'</div>'
	
	}

	function rowTemplate(row) {
		return '<div ng-class="{ \'grey\':grid.appScope.rowFormatter( row ) }" ng-dblclick="grid.appScope.openPopupSiparisDetayi(row.entity)">'
		+ '  <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>'
		+ '</div>';
	}
	
	function arrangeButtonGroup(row) {
		return '<div class="btn-group">'+

		 '<button type="button" class="btn btn-default" style=" height : 32px; margin-left: 2px;" ng-click="grid.appScope.openPopupSiparisDetayi(row.entity)">'+
		 	'<span class="glyphicon glyphicon-eye-open" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default" style = "height : 32px;" ng-click="grid.appScope.openPopupDurumGuncelle(row.entity)">'+
		 	'<span class="glyphicon glyphicon-pencil" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default" style=" height : 32px; margin-right:2px; " ng-click="grid.appScope.openPopupBarkodOlustur(row.entity)">'+
		 	'<span class="glyphicon glyphicon-barcode" ></span>'+
		 '</button>'+
		 '</div>';
	}
	$scope.rowFormatter = function(row) {
		var warn = false;
		
		var now = new Date();
		var delivery = new Date(row.entity.siparisTalepTeslimTarihi); 
		
		var diff = (delivery - now)/1000;
		if((row.entity.siparisDurum != 6 && row.entity.siparisDurum != 7) && diff < 0){
			warn = true;
		}
	    diff = Math.abs(Math.floor(diff));
	    
	    var days = Math.floor(diff/(24*60*60));
	    var leftSec = diff - days * 24*60*60;
	    
	    var hrs = Math.floor(leftSec/(60*60));
	    var leftSec = leftSec - hrs * 60*60;
	      
	    var min = Math.floor(leftSec/(60));
	    var leftSec = leftSec - min * 60;
	    
	    if(((days <= 0 && hrs <= 4) || (row.entity.siparisDurum == 1))){
	    	warn = true;
	    }
		return warn;
	};
	
	function getSiparisList() {

		$http.post('v1/siparis/islem/getOrderList').success(
				function(response) {
//					debugger;

					var len = response.length;
					if(len == 0){
						len = 1;
					}
					$scope.gridOptions.rowHeight = 35 * len + "px",
					$scope.gridOptions.data = response;
					
					
				}).error(function(error) {

		})
		
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
		var oid = row.oid;
		var status = row.siparisDurum;
		
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupStatusUpdate.html',
		      controller: 'updateStatusInstanceCtrl',
		      size: 'sm',
		      resolve: {
		    	  // açılmadan once
		    	  rowOid: function(){
		    		  return oid;  
		    	  },
		    	  rowStatu: function(){
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
	$scope.openPopupBarkodOlustur = function openPopupBarkodOlustur(entities) {
		debugger;
		var oid = entities.oid;
	    var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'popupBarcode.html',
		      controller: 'barcodeInstanceCtrl',
		      size: 'md',
		      resolve: {
		    	  // açılmadan once
		    	  oid : function(){
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
//		                 { field: 'siparisOid', displayName: "siparisOid", visible: false, enableHiding: false},
		                 { field: 'siparisKalemAdi', displayName: "Ürün Adi", visible: true},
		                 { field: 'adet', displayName: "Adet", visible: true},
		                 { field: 'birimFiyati', displayName: "Birim Fiyat", visible: true, cellFilter: 'currency'},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: true, cellFilter: 'currency'},
		                 { field: 'indirim', displayName: "İndirim", visible: true, cellFilter: 'currency'},
		                 { field: 'kalemGenelToplam', displayName: "Ürün Fiyatı", visible: true, cellFilter: 'currency'},
		                 { field: 'urunAdi', displayName: "Ürün Adı", visible: false}
		    ], 
		    data : []
		    
	}

	function getSiparisDetay(siparisOid) {
		debugger;
		if(siparisOid === null || "" == siparisOid){
			console.log("siparisOid bos");
		}else {
			$http.post('v1/siparis/islem/getOrderByOid/' + siparisOid).success(
					function(response) {
						debugger;
						
						$scope.oid = response.oid;
						$scope.barcodeNumber = response.barcodeNumber;
						$scope.siparisAdi = response.siparisAdi;
						$scope.siparisVerenFirma = response.siparisVerenFirma;
						$scope.siparisVerenKisi = response.siparisVerenKisi;
						$scope.tedarikEdenFirma = response.tedarikEdenFirma;
						$scope.tedarikEdenKisi = response.tedarikEdenKisi;
						$scope.siparisOlusmaTarihi = response.siparisOlusmaTarihi;
						$scope.siparisTeslimTarihi = response.siparisTeslimTarihi;
						$scope.araToplam = response.araToplam;
						$scope.kdv = response.kdv;
						$scope.indirim = response.indirim;
						$scope.genelToplam = response.genelToplam;
						$scope.adres = response.adres;
						$scope.siparisAciklama = response.siparisAciklama;
						$scope.adresAciklama = response.adresAciklama;
						$scope.siparisTalepTeslimTarihi = response.siparisTalepTeslimTarihi;
						$scope.siparisDurum = response.siparisDurum;
						
						$scope.teslimatGridOptions.data = response.orderDetailList;
						
					}).error(function(error) {
				debugger;

			});
		}
	}
});


sipApp.controller('updateStatusInstanceCtrl', function($scope, $http, $window, $uibModalInstance, rowOid, rowStatu, statusRepoService) {
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
		$scope.statusData.selectedOption = rowStatu + "";
		
	};

	var getStatus = function() {
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();     
	     
	
	$scope.durumGuncelle = function durumGuncelle(statu) {
		debugger;
		
		$http.post('v1/siparis/islem/updateOrderStatus/' + rowOid + '-' + statu).success(
		function(response) {
			$uibModalInstance.close();
			$window.location.reload();
			
			
		}).error(function(error) {
			debugger;

		})
	}
});

sipApp.controller('barcodeInstanceCtrl', function($scope, $http, $uibModalInstance, oid) {
	getOrder(oid);
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
	$scope.barkodEslestir = function barkodEslestir(barcode){
		
		$http.post('v1/siparis/islem/updateOrderBarcode/' + oid + '-' + barcode).success(
				function(response) {
					
				}).error(function(error) {
					debugger;

				})
	}
	
	function getOrder(oid) {
		debugger;
		if(oid === null || "" == oid){
			console.log("oid bos");
		}else {
			$http.post('v1/siparis/islem/getOrderByOid/' + oid).success(
					function(response) {
						debugger;
						if(null !== response.barcode && "" !== response.barcodeNumber){
							$scope.barcode = response.barcodeNumber;	
						}
					}).error(function(error) {
				debugger;

			});
		}
	}
	
});

// ------------------------ FILTER ------------------------
sipApp.filter('currency', function () {
    return function (input) {
    	var str = "";
    	if(null != input && "" != input){
    		str = input + " TL"; 
    	}
      //Do your formatting here.
      return str;
    };
});
// ------------------------ COMBOBOX SERVICE ------------------------


var statusRepoService = function($http){
    
	var getStatus = function() {
		return $http.post("v1/siparis/islem/fillCombo/OrderStatus").then(
				function(response) {
//					debugger;
					return response.data;
				});
	};

    return {
        get: getStatus
    };
      
  };
  sipApp.factory("statusRepoService", statusRepoService);
  
