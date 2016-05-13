var tesApp = angular.module("teslimatModule", [ "ui.grid"]);

tesApp.controller("teslimatCtrl", function($scope, $http, $window, statusRepoService, i18nService) {
	 
	$scope.langs = i18nService.getAllLangs();
	$scope.lang = 'tr';
	  
	var onFetchError = function(message) {
		$scope.error = "referans datalar getirilemedi. Message:" + message;
		$scope.loadingStatus = false;
	};

	var onFetchCompleted = function(data) {
		$scope.statusData = data;
		$scope.statusData.selectedOption = "";
		$scope.loadingStatus = false;
		
	};

	var getStatus = function() {
		$scope.loadingStatus = true;
		statusRepoService.get().then(onFetchCompleted, onFetchError);
	};

	getStatus();
	
	var barcodeLength = 10;
	$scope.barLen = barcodeLength;

	
	$scope.teslimEt = function teslimEdildi(form) {

		if (form.$valid) {
			var oid = $scope.oid;
			$scope.loadingDeliver = true;
			$http.post('v1/siparis/islem/deliverOrder/' + oid).success(
					function(response) {
						
						$scope.loadingDeliver = false;
						$window.location.reload();
					}).error(function(error) {
						$scope.loadingDeliver = false;

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
		                 { field: 'siparisKalemAdi', displayName: "Sipariş Kalem Adı", visible: true, width : 175},
		                 { field: 'adet', displayName: "Adet", visible: true, width : 75},
		                 { field: 'birimFiyati', displayName: "Birim Fiyat", visible: true, cellFilter: 'currency', width : 110},
		                 { field: 'araToplam', displayName: "Ara Toplam", visible: true, cellFilter: 'currency', width : 110},
		                 { field: 'indirim', displayName: "İndirim", visible: true, cellFilter: 'currency', width : 80},
		                 { field: 'kalemGenelToplam', displayName: "Ürün Fiyatı", visible: true, cellFilter: 'currency', width : 110},
		                 { field: 'urunAdi', displayName: "Ürün Adı", visible: true }
		    ], 
		    data : []
		    
	}
	
	$scope.getSiparis = function getSiparis(form) {
		
		if (form.$valid) {
			$scope.loadingBarcode = true;
			$http.post('v1/siparis/islem/getOrderByBarcode/' + form.barkod.$viewValue).success(
					function(response) {
						if("" !== response && null !== response){
							
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
							
							$scope.gelal = response.gelal == 1 ? "Evet" : "Hayır";
							$scope.odemeSekli = response.odemeSekli;
							$scope.adSoyad = response.teslimAlacakAd + " " + response.teslimAlacakSoyad;
							$scope.teslimAlacakTel = response.teslimAlacakTel;
							$scope.teslimAlacakGsm = response.teslimAlacakGsm;
							$scope.teslimAlacakEmail = response.teslimAlacakEmail;
							
							$scope.teslimatGridOptions.data = response.orderDetailList;
						}
						else {
							alert("Kayıt bulunamadı!");
						}
						$scope.loadingBarcode = false;
					}).error(function(error) {
						alert("Kayıt getirilirken hata oluştu. Lütfen tekrar deneyiniz!");
						$scope.loadingBarcode = false;
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