var sipApp = angular.module("siparisModule", ["ui.grid", 'ngAnimate', 'ui.bootstrap']);

sipApp.controller("siparisCtrl", function($scope, $http, $uibModal, $location, statusRepoService, i18nService) {
	
	$scope.langs = i18nService.getAllLangs();
	$scope.lang = 'tr';
	
	setInterval(function(){ getSiparisList(); }, 1000*60*1);
	
	var onFetchError = function(message) {
		console.log("error: " +  message);
		$scope.error = "Sipariş durumları yüklenemedi. Lütfen sayfayı yenileyiniz. ";
		$scope.loadingStatus = false;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = "";
		getSiparisList();
		$scope.loadingStatus = false;
		
	};

	var getStatus = function() {
		$scope.loadingStatus = true;
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();     
	
	

	$scope.gridOptions = {
			enableGridMenu: true,
			rowTemplate: rowTemplate( 'row' ),
			enableHorizontalScrollbar : 0,
			enableVerticalScrollbar : 1,
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false, enableHiding: false},
		                 { field: 'siparisAdi', displayName: "Sipariş Adı", visible: true},
		                 { field: 'adres', displayName: "Adres", visible: false},
		                 { field: 'adresAciklama', displayName: "Adres Açıkama", visible: true},
		                 { field: 'siparisTalepTeslimTarihi', displayName: "Talep Edilen Teslim Tarihi", visible: true, cellFilter: 'date:\'dd-MM-yyyy HH:mm\'', width : 140},
		                 { field: 'siparisAciklama', displayName: "Sipariş Açıkama", visible: true},
		                 { field: 'siparisDurum', displayName: "Sipariş Durumu", visible: true, cellTemplate: arrangeCombobox('row')},
		                 { field: 'siparisVerenFirma', displayName: "Sipariş Veren Firma", visible: false},
		                 { field: 'siparisVerenKisi', displayName: "Sipariş Veren Kişi", visible: false, height: 30},
//		                 { field: 'tedarikEdenFirma', displayName: "Tedarik Eden Firma", visible: false},
//		                 { field: 'tedarikEdenKisi', displayName: "Tedarik Eden Kişi", visible: false},
		                 { field: 'siparisOlusmaTarihi', displayName: "Sipariş Tarihi", visible: false, cellFilter: 'date:\'dd-MM-yyyy HH:mm\'', width : 140},
		                 { field: 'siparisTeslimTarihi', displayName: "Teslim Tarihi", visible: false, cellFilter: 'date:\'dd-MM-yyyy HH:mm\'', width : 140},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: false, cellFilter: 'currency'},
		                 { field: 'kdv', displayName: "KDV", visible: false},
		                 { field: 'indirim', displayName: "İndirim", visible: false, cellFilter: 'currency'},
		                 { field: 'genelToplam', displayName: "Genel Toplam", visible: false, cellFilter: 'currency'},
		                 { field: 'btnGroup', displayName: "", visible: true, enableHiding: false, enableSorting: false, enableColumnMenu:false,
		                	 cellTemplate: arrangeButtonGroup('row'), minWidth : "150", width:"150",
		                		 
		                 }
		    ], 
		    data : [],
		    onRegisterApi: function(gridApi){
		        grid = gridApi;
		    }
		    
	}
	function arrangeCombobox(row) {

		return '<div>'
					+'<select ng-disabled = "true" ng-model = "row.entity.siparisDurum" style = "width:100%; height:30px">'
					+	'<option ng-repeat = "option in grid.appScope.statusData" value="{{option.value}}">{{option.key}}</option>'
					+'</select>'
				+'</div>'
	
	}

	function rowTemplate(row) {
		//'<div ng-class="{ \'grey\':grid.appScope.rowFormatter( row ) }" ng-dblclick="grid.appScope.openPopupSiparisDetayi(row.entity)">'
		return '<div ng-class="{ '
		
		+ '  \'rowColorOrange\':grid.appScope.rowFormatter( row ) == \'rowColorOrange\',  '
		+ '  \'rowColorGreen\':grid.appScope.rowFormatter( row ) == \'rowColorGreen\', '
		+ '  \'rowColorRed\':grid.appScope.rowFormatter( row ) == \'rowColorRed\'}"'
		
		+ ' ng-dblclick="grid.appScope.openPopupSiparisDetayi(row.entity)">'
		+ ' <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>'
		+ '</div>';
	}
	$scope.rowFormatter = function(row, color) {
		var color = "";

		var teslimEdildi = "Teslim Edildi";
		var iptalEdildi = "İptal Edildi";
		var siparisDegisti = "Sipariş Değiştirildi";
		var siparisOlustu = "Sipariş Oluşturuldu";

		var now = new Date();
		var delivery = new Date(row.entity.siparisTalepTeslimTarihi); 
		
		var diff = (delivery - now)/1000;
		
			
		if((row.entity.siparisDurum.localeCompare(teslimEdildi) != 0 && row.entity.siparisDurum.localeCompare(iptalEdildi) != 0) && diff < 0){
			color = 'rowColorRed';
			return color;
		}
		
		if(row.entity.siparisDurum.localeCompare(siparisOlustu) == 0){
			color = 'rowColorGreen';
			return color;
		}
		
	    diff = Math.abs(Math.floor(diff));
	    
	    var days = Math.floor(diff/(24*60*60));
	    var leftSec = diff - days * 24*60*60;
	    
	    var hrs = Math.floor(leftSec/(60*60));
	    var leftSec = leftSec - hrs * 60*60;
	      
	    var min = Math.floor(leftSec/(60));
	    var leftSec = leftSec - min * 60;
	    
	    if(days <= 0 && hrs <= 4){
	    	color = 'rowColorRed';
			return color;
	    }
	    
	    if(row.entity.siparisDurum.localeCompare(siparisDegisti) == 0){
	    	// orange değil mor oldu css de 
	    	color = 'rowColorOrange';
	    	return color;
	    }
		return false;
		
	}
	
	function arrangeButtonGroup(row) {
		return '<div class="btn-group">'+

		 '<button type="button" class="btn btn-default btn-sipDetay" ng-click="grid.appScope.openPopupSiparisDetayi(row.entity)">'+
		 	'<span class="glyphicon glyphicon-eye-open" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default btn-sipUpdate" ng-click="grid.appScope.openPopupDurumGuncelle(row.entity)">'+
		 	'<span class="glyphicon glyphicon-pencil" ></span>'+
		 '</button>'+
		 '<button type="button" class="btn btn-default btn-sipBarcode" ng-click="grid.appScope.openPopupBarkodOlustur(row.entity)">'+
		 	'<span class="glyphicon glyphicon-barcode" ></span>'+
		 '</button>'+
		 '</div>';
	}

//	$scope.rowFormatterRed = function(row) {
//		debugger;
//		var warn = false;
//		
//		var now = new Date();
//		var delivery = new Date(row.entity.siparisTalepTeslimTarihi); 
//		
//		var diff = (delivery - now)/1000;
//		
//		var teslimEdildi = "Teslim Edildi";
//		var iptalEdildi = "İptal Edildi";
//		var siparisOlustu = "Sipariş Oluşturuldu";
//			
//		if((row.entity.siparisDurum.localeCompare(teslimEdildi) != 0 && row.entity.siparisDurum.localeCompare(iptalEdildi) != 0) && diff < 0){
//			warn = true;
//		}
//	    diff = Math.abs(Math.floor(diff));
//	    
//	    var days = Math.floor(diff/(24*60*60));
//	    var leftSec = diff - days * 24*60*60;
//	    
//	    var hrs = Math.floor(leftSec/(60*60));
//	    var leftSec = leftSec - hrs * 60*60;
//	      
//	    var min = Math.floor(leftSec/(60));
//	    var leftSec = leftSec - min * 60;
//	    
//	    if(((days <= 0 && hrs <= 4) || (row.entity.siparisDurum.localeCompare(siparisOlustu) == 0))){
//	    	warn = true;
//	    }
//		return warn;
//	};
//	
//	$scope.rowFormatterGreen = function(row) {
//		debugger;
//		var siparisOlustu = "Sipariş Oluşturuldu";
//		var warn = false;
//		if(row.entity.siparisDurum.localeCompare(siparisOlustu) === 0){
//			warn = true;
//		}
//		return warn;
//	}
//	
//	$scope.rowFormatterOrange = function(row) {
//		debugger;
//		var warn = true;
//		
//		return warn;
//	}
	
	function getSiparisList() {
		$scope.loadingList = true;
		
		$http.post('v1/siparis/islem/getOrderList').success(
				function(response) {

					var len = response.length;
					if(len == 0){
						len = 1;
					}
					$scope.gridOptions.rowHeight = 40 * len + "px",
					$scope.gridOptions.data = response;
					$scope.loadingList = false;
					
				}).error(function(error) {
					$scope.loadingList = false;
					alert("Siparişler getirilirken hata oluştu. Lütfen daha sonra tekrar deneyiniz.");
		})
		
	}
	
	$scope.openPopupSiparisDetayi = function openPopupSiparisDetayi(row) {
		
		var oid = row.oid;
		if(oid === null || "" == oid){
			console.log("siparis bilgisi bos");
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
		
		var oid = row.oid;
		var status = row.siparisDurum;
		if(oid === null || "" == oid){
			console.log("siparis bilgisi bos");
		}else {
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

		
	    
		    
	}
	$scope.openPopupBarkodOlustur = function openPopupBarkodOlustur(entities) {
		
		var oid = entities.oid;
		if(oid === null || "" == oid){
			console.log("siparis bilgisi bos");
		}else {
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
	}
	
	$scope.redirectTeslimat = function redirectTeslimat(){

		var url = 'teslimat';
		window.location.href = url;
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
		                 { field: 'siparisKalemAdi', displayName: "Sipariş Kalem Adı", visible: true},
		                 { field: 'adet', displayName: "Adet", visible: true},
		                 { field: 'birimFiyati', displayName: "Birim Fiyat", visible: true, cellFilter: 'currency'},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: true, cellFilter: 'currency'},
		                 { field: 'indirim', displayName: "İndirim", visible: true, cellFilter: 'currency'},
		                 { field: 'kalemGenelToplam', displayName: "Ürün Fiyatı", visible: true, cellFilter: 'currency'},
		                 { field: 'urunAdi', displayName: "Ürün Adı", visible: true}
		    ], 
		    data : []
		    
	}

	function getSiparisDetay(siparisOid) {
		
		if(siparisOid === null || "" == siparisOid){
			console.log("siparisOid bos");
		}else {
			$scope.loadingOrder = true;
			$http.post('v1/siparis/islem/getOrderByOid/' + siparisOid).success(
					function(response) {
						
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
						
						$scope.gelal = response.gelal == 1 ? "Evet" : "Hayır";
						$scope.odemeSekli = response.odemeSekli;
						$scope.adSoyad = response.teslimAlacakAd + " " + response.teslimAlacakSoyad;
						$scope.teslimAlacakTel = response.teslimAlacakTel;
						$scope.teslimAlacakGsm = response.teslimAlacakGsm;
						$scope.teslimAlacakEmail = response.teslimAlacakEmail;
						
						$scope.teslimatGridOptions.data = response.orderDetailList;
						$scope.loadingOrder = false;
						
					}).error(function(error) {
						$scope.loadingOrder = false;
			});
		}
	}
});


sipApp.controller('updateStatusInstanceCtrl', function($scope, $http, $window, $uibModalInstance, rowOid, rowStatu, statusRepoService) {

	$scope.ok = function() {
		$uibModalInstance.close();
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	var onFetchError = function(message) {
		$scope.loadingStatus = false;
		$scope.error = "Error Fetching Users. Message:" + message;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = rowStatu + "";
		$scope.loadingStatus = false;
		
	};

	var getStatus = function() {
		$scope.loadingStatus = true;
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();     
	     
	
	$scope.durumGuncelle = function durumGuncelle(statu) {
		
		$scope.loadingUpdateStatus = true;
		$http.post('v1/siparis/islem/updateOrderStatus/' + rowOid + '-' + statu).success(
		function(response) {
			$uibModalInstance.close();
			$window.location.reload();
			$scope.loadingUpdateStatus = false;
			
		}).error(function(error) {
			$scope.loadingUpdateStatus = false;
		})
	};
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

		if(barcode === null || "" == barcode){
			console.log("barkod bilgisi bos");
		}else {
			$("#order-barcode").barcode({
				code : barcode,
				crc : false
			}, "int25", {
				barWidth : 2,
				barHeight : 30
			});
		}

	}
	$scope.yazdir = function yazdir() {
		
		var newWindow = window.open();
	    newWindow.document.write(document.getElementById("order-barcode-container").innerHTML);
	    newWindow.print();
	    
	}
	
	function getOrder(oid) {
		
		if(oid === null || "" == oid){
			console.log("oid bos");
		}else {
			$scope.loadingOrder = true;
			
			$http.post('v1/siparis/islem/getOrderByOid/' + oid).success(
					function(response) {
						
						if(null !== response.barcode && "" !== response.barcodeNumber){
							$scope.barcode = response.barcodeNumber;	
							$scope.barkodOlustur(response.barcodeNumber);
						}
						$scope.loadingOrder = false;
					}).error(function(error) {
						$scope.loadingOrder = false;
			});
		}
	};
	
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

					return response.data;
				});
	};

    return {
        get: getStatus
    };
      
  };
  sipApp.factory("statusRepoService", statusRepoService);
  
