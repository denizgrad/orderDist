var sipApp = angular.module("siparisModule", [ "ngTable" ]);

sipApp.controller("siparisCtrl", function($scope, $http, NgTableParams) {

	debugger;

	asd();
	function asd() {
		$http.get('js/app/services/getSiparisList.json').success(
				function(response) {
					debugger;
					$scope.siparisList = response.siparisList;

					$scope.siparisTable = new NgTableParams({}, {
						total : response.siparisList.length,
						getData : function($defer, params) {
							debugger;
							return $defer.resolve($scope.siparisList);
						}
					});
				}).error(function(error) {
			debugger;

		})
	}
	$scope.detayGoruntule = function detayGoruntule(siparis) {
		alert("detay görüntüle");
	}
	$scope.durumGuncelle = function durumGuncelle(siparis) {
		alert("durum güncelle");
	}
	$scope.barkodOlustur = function barkodOlustur(siparis) {
		alert("barkod oluştur");
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
