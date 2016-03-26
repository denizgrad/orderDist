angular.module("teslimatModule", []).controller(
		"tes",
		function($scope, $http) {
			debugger;
			// TODO servis olarak server a taşı
			// $scope.data = {// ilk olarak seçili gelmesi için
			// selectedOption : "6",
			// status : [ {
			// id : '6',
			// name : 'Teslim edildi'
			// } ]
			// };
			var status = {};
			getStatus();

			function getStatus() {
				debugger;
				$http.post('siparis/islem/fillCombo/OrderStatus').success(
						function(response) {
							debugger;
							// if (response.success) {
							// alert(success);
							// } else {
							// alert("hatalar hatalar");
							// }
							$scope.data = response;
						});

			}
			;
			$scope.asdad = function asdad() {

				$http.post('siparis/islem/yarat', {
					"data" : {
						"key" : "asd",
						"value" : "asd"
					}
				}).success(function(response) {
					debugger;

				});
			}
			$scope.teslimEdildi = function teslimEdildi(barkod, statu) {
				debugger;
				alert("barkod:" + barkod + "statu:" + statu);
			};

		});
