var sipApp = angular.module("siparisModule", [ "ngTable" ]);

sipApp.controller("siparisCtrl", function($scope, $http, NgTableParams) {

	debugger;

	asd();
	getTeslimatStatus();

	function getTeslimatStatus() {
		debugger;
		// TODO service name ve post
		$http.get('js/app/services/getSiparisStatus.json').success(
				function(response) {
					debugger;
					$scope.statusData = response;
					$scope.statusData.selectedOption = null;

				}).error(function(error) {
			debugger;

		})
	}
	
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
	
	$scope.durumGuncelle = function durumGuncelle(siparis) {
		
		$http.get('js/app/services/updateSiparis.json').success(
				function(response) {
//					debugger;
//					$scope.siparisList = response.siparisList;
//
//					$scope.siparisTable = new NgTableParams({}, {
//						total : response.siparisList.length,
//						getData : function($defer, params) {
//							debugger;
//							return $defer.resolve($scope.siparisList);
//						}
//					});
				}).error(function(error) {
			debugger;

		})

		
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


sipApp.controller('popupCtrl', function ($scope, $http, NgTableParams) {
    $scope.showModal = false;
//    $scope.buttonClicked = "";
    $scope.detayGoruntule = function(siparis){
    	debugger;
    	
    	//--
    	
    	$http.get('js/app/services/getSiparis.json').success(function(response) {
    		debugger;
			$scope.siparisAdres = response.adres;
			$scope.siparisToplamTutar = response.toplamTutar;
			$scope.siparisDetay = response.siparisDetay;

			$scope.siparisDetayTable = new NgTableParams({}, {
				total : response.siparisDetay.length,
				group : "urunKodu",
				getData : function($defer, params) {
					debugger;
					return $defer.resolve($scope.siparisDetay);
				}
			});
    	}).error(function(error) {
    		debugger;
    	});
    	
    	
    	// --
    	
    	
    	// scope artık directive in içerisi
//        $scope.buttonClicked = btnClicked;
        $scope.showModal = !$scope.showModal;
    };
  });

sipApp.controller('basicsCtrl', function ($scope) {
    $scope.rowCollection = [
        {firstName: 'Laurent', lastName: 'Renard', birthDate: "1459367221000", balance: 102, email: 'whatever@gmail.com'},
        {firstName: 'Blandine', lastName: 'Faivre', birthDate: new Date('1987-04-25'), balance: -2323.22, email: 'oufblandou@gmail.com'},
        {firstName: 'Francoise', lastName: 'Frere', birthDate: new Date('1955-08-27'), balance: 42343, email: 'raymondef@gmail.com'}
    ];
});

sipApp.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
              '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                 'Sipariş Detayı'+
              '</div>' + 
              '<div class="modal-body">' +
	          
              	'<div class="siparsi">'+
    				'<h3>Sipariş Detayı</h3>'+
    				'<div class="adres">adres : {{siparisAdres}}</div>'+
    				'<div class="tutar">Toplam Tutar: {{siparisToplamTutar}}</div>'+
    				'<div class="detayTable">'+
    					'<table ng-table="siparisDetayTable" class="table table-bordered table-striped">'+
    						'<colgroup>'+
    							'<col width="60%" />'+
    							'<col width="20%" />'+
    							'<col width="20%" />'+
    						'</colgroup>'+
    						'<tr ng-repeat="detay in siparisDetay">'+
    							'<td title="\'Ürün Kodu\'">{{detay.urunKodu}}</td>'+
    							'<td title="\'Miktar\'">{{detay.miktar}}</td>'+
    							'<td title="\'Tutar\'">{{detay.birimTutar}}</td>'+
    						'</tr>'+
    					'</table>'+
    				'</div>'+
    			'</div>'+
	              
              '</div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
          scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });