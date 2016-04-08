var tesApp = angular.module("teslimatModule", [ "ui.grid"]);

tesApp.controller("teslimatCtrl", function($scope, $http,$window , statusRepoService) {

	var onFetchError = function(message) {
		$scope.error = "referans datalar getirilemedi. Message:" + message;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = "";
		
	};

	var getStatus = function() {
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();
	
	var barcodeLength = 10;
	$scope.barLen = barcodeLength;

	
	$scope.teslimEt = function teslimEdildi(form) {

		if (form.$valid) {
			// TODO teslim et de çcalışıcak olan servisi yaz
			var oid = $scope.oid;
			$http.post('v1/siparis/islem/deliverOrder/' + oid).success(
					function(response) {
						
						$window.location.reload();
					}).error(function(error) {
				

			});
			
		} else {
			console.log("hatalar hatalar..");
		}

	}
	
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
	
	$scope.getSiparis = function getSiparis(form) {
		
		if (form.$valid) {
			$http.post('v1/siparis/islem/getOrderByBarcode/' + form.barkod.$viewValue).success(
					function(response) {
						
						$scope.oid = response.oid;
						$scope.barkod = response.barcodeNumber;
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

			});
		}
	}
	
});
//------------------------ FILTER ------------------------
tesApp.filter('currency', function () {
    return function (input) {
    	var str = "";
    	if(null != input && "" != input){
    		str = input + " TL"; 
    	}
      return str;
    };
});


//------------------------------ REF DATA ------------------------------

var refDataService = function($http){
    
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
tesApp.factory("statusRepoService", refDataService);