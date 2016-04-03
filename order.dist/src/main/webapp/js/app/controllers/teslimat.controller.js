var tesApp = angular.module("teslimatModule", [ "ui.grid"/*, "smart-table" */]);

tesApp.controller("teslimatCtrl", function($scope, $http) {

	var barcodeLength = 10;
	$scope.barLen = barcodeLength;
	debugger;

	getTeslimatStatus();

	function getTeslimatStatus() {
		debugger;
		// TODO service name ve post
		$http.get('js/app/services/getTeslimatStatus.js').success(
				function(response) {
					debugger;
					$scope.statusData = response;
					$scope.statusData.selectedOption = response[0].value;

				}).error(function(error) {
			debugger;

		})
	}

	$scope.teslimEt = function teslimEdildi(form) {

		if (form.$valid) {
			// TODO teslim et de çcalışıcak olan servisi yaz
			var barkod = form.barkod;
			var status = form.status;
			$http.get('js/app/services/getTeslimatStatus.js').success(
					function(response) {
						debugger;
						$scope.statusData = response;
						$scope.statusData.selectedOption = response[0].value;

					}).error(function(error) {
				debugger;

			});
			debugger;
			alert("barkod:" + form.barkod + "statu:" + form.status);
		} else {
			console.log("hatalar hatalar..");
		}

	}
	$scope.gridOptions = {
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
//	$scope.gridOptions.onRegisterApi = function(gridApi){
//		gridApi.ScrollingVertically = false;
//		gridApi.ScrollingHorizontally = false; 
//		
//		$scope.gridApi = gridApi;
//        
//     };
     
	$scope.getSiparis = function getSiparis(form) {
		debugger;
		if (form.$valid) {
			$http.get('js/app/services/getSiparis.json').success(
					function(response) {
						debugger;
						$scope.siparisAdres = response.adres;
						$scope.siparisToplamTutar = response.toplamTutar;
						
						
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
						
						
						$scope.gridOptions.data = response.siparisDetay;
						
//						removeFromGridMenu(angular.element(document.getElementById("uiGridTable")), 7);
						
						debugger;
						
					}).error(function(error) {
				debugger;

			});
		}
	}
});
