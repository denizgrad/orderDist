angular
		.module("teslimatModule", [ "ngTable" ])
		.controller(
				"teslimatCtrl",
				function($scope, $http, NgTableParams) {

					var barcodeLength = 10;
					$scope.barLen = barcodeLength;
					debugger;

					getTeslimatStatus();

					function getTeslimatStatus() {
						debugger;
						// TODO service name ve post
						$http
								.get('js/app/services/getTeslimatStatus.js')
								.success(
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
							$http
									.get('js/app/services/getTeslimatStatus.js')
									.success(
											function(response) {
												debugger;
												$scope.statusData = response;
												$scope.statusData.selectedOption = response[0].value;

											}).error(function(error) {
										debugger;

									});
							debugger;
							alert("barkod:" + form.barkod + "statu:"
									+ form.status);
						} else {
							console.log("hatalar hatalar..");
						}

					}
					$scope.getSiparisDetay = function getSiparisDetay(form) {
						debugger;
						if (form.$valid) {
							$http
									.get('js/app/services/getSiparis.js')
									.success(
											function(response) {
												debugger;
												$scope.siparisAdres = response.adres;
												$scope.siparisToplamTutar = response.toplamTutar;
												$scope.siparisDetay = response.siparisDetay;

												$scope.siparisDetayTable = new NgTableParams(
														{
														},
														{
															total : response.siparisDetay.length,
															getData : function(
																	$defer,
																	params) {
																debugger;
																return $defer
																		.resolve($scope.siparisDetay);
															}
														});
											}).error(function(error) {
										debugger;

									});
						}

					}

				});
