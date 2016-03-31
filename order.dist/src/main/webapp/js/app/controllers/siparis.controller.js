var sipApp = angular.module("siparisModule", [ "ui.grid" ]);

sipApp.controller("siparisCtrl", function($scope, $http) {

	debugger;

	getSiparisList();
	getSiparisStatus();

	function getSiparisStatus() {
		debugger;
		// TODO service name ve post
		$http.get('js/app/services/getSiparisStatus.js').success(function(response) {
					debugger;
					$scope.statusData = response;
					$scope.statusData.selectedOption = null;

				}).error(function(error) {
			debugger;

		})
	}
	$scope.gridOptions = {
			enableGridMenu: true,
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false},
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
		                 { field: 'siparisDurumu', displayName: "Sipariş Durumu", visible: true}
		    ], 
		    data : []
		    
	}
	
	function getSiparisList() {
		$http.get('js/app/services/getSiparisList.json').success(
				function(response) {
					debugger;
					$scope.gridOptions.data = response;
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


sipApp.controller('popupCtrl', function ($scope, $http) {
    $scope.showModal = false;
//    $scope.buttonClicked = "";
    $scope.detayGoruntule = function(siparis){
    	debugger;
    	
    	//--
    	
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
    						
    					}).error(function(error) {
    				debugger;

    			});
    		}
    	}
    	
    	
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
	          
		      		'<h2>Teslimat</h2>' + 
		    		
		    		'<form name="form" ng-submit="teslimEt(form)" role="form">' +
		    		'<div class="row">' +
		    		'	<div class="col-md-4 column ui-sortable">' +
		    		'		<div class="box box-element" style="display: block;">' +
		    		'			<div class="view">' +
		    		'				<div class="form-group">' +
		    		'					<label class="col-md-5 control-label" for="barkod">Barkod</label>' +  
		    		'					<div class="col-md-7">' +
		    		'					<input type="text" name="barkod" ng-model="barkod" required ng-blur="getSiparis(form)" ng-class="{' +
		    	    '                          \'has-error\':  form.barkod.$invalid  && form.barkod.$dirty,' +
		    	    '                           \'has-success\':form.barkod.$valid  &&  form.barkod.$dirty}"' +
		    		'							ng-pattern="/^[0-9]*$/" ng-minlength={{barLen}}' +
		    		'							maxlength={{barLen}} class="form-control input-md"/>' +
		    		'						<div ng-show="form.barkod.$error.required && form.barkod.$dirty">' +
		    		'							<span class="help-block">Lütfen barkod numarası giriniz.</span>' +
		    		'						</div>' +
		    		'						<div ng-show="form.barkod.$error.minlength &&  form.barkod.$dirty">' +
		    		'							<span class="help-block">Geçerli bir barkod numarası giriniz.</span>' +
		    		'						</div>' +
		    		'						<div ng-show="form.barkod.$error.pattern && form.barkod.$dirty">' +
		    		'							<span class="help-block">Barko numarası sadece sayı içerebilir.</span>' +
		    		'						</div>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'				</div>' +
		    		'		</div>' +
		    		'	</div>' +
		    		'				<div class="col-md-4 column ui-sortable">' +
		    		'		<div class="box box-element" style="display: block;">' +
		    		'			<div class="view">' +
		    		'				<div class="form-group">' +
		    		'					<label class="col-md-5 control-label" for="status">Sipariş Durumu</label>' +  
		    		'					<div class="col-md-7">' +
		    		'						<select name="status" id="status" ng-model="statusData.selectedOption" ng-disabled="true" class="form-control input-md">' +
		    		'							<option ng-repeat="option in statusData" value="{{option.value}}">{{option.key}}</option>' +
		    		'						</select>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'				</div>' +
		    		'		</div>' +
		    		'	</div>' +
		    		'	<div class="col-md-4 column ui-sortable">' +
		    		'		<div class="box box-element" style="display: block;">' +
		    		'			<div class="view">' +
		    		'				<div class="form-group">' +
		    		'					<label class="col-md-5 control-label" for="siparisAciklamasi">Siparişi Teslim Et</label>' +  
		    		'					<div class="col-md-7">' +
		    		'						<div class="form-actions">' +
		    		'							<button type="submit" class="btn btn-primary btn-default" ng-disabled="form.$invalid || vm.dataLoading"  >Teslim Et</button>' +
		    		'						</div>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'	</div>' +
		    		'</div>' +
		    		'</form>' +
		
		    		'<div class="sipars">' +
		    		'	<h3>Sipariş Bilgileri</h3>' +
		    		'	<input type="hidden" name="oid" ng-value="oid" />' +
		
		    		'	<div class="row">' +
		    		'		<div class="col-md-4 column ui-sortable">' +
		    		'			<div class="box box-element" style="display: block;">' +
		    		'				<div class="view">' +
		    		'						<div class="form-group row">' +
		    		'						  <label class="col-md-5 control-label" for="siparisVerenFirma">Siparişi Veren Firma</label>' +  
		    		'						  <div class="col-md-7">' +
		    		'						  	<span id="siparisVerenFirma" class="form-control input-md"> {{siparisVerenFirma}}</span>' +
		    		'						  </div>' +
		    		'						</div>' +
		    		'						<div class="form-group row">' +
		    		'						  <label class="col-md-5 control-label" for="siparisTarihi">Sipariş Tarihi</label>' +  
		    		'						  <div class="col-md-7">' +
		    		'						  <span id="siparisTarihi" class="form-control input-md">{{siparisTarihi}}</span>' +
		    		'						  </div>' +
		    		'						</div>' +
		    		'						<div class="form-group row">' +
		    		'						  <label class="col-md-5 control-label" for="siparisAciklamasi">Sipariş Açıklaması</label>' +  
		    		'						  <div class="col-md-7">' +
		    		'						  <textArea id="siparisAciklamasi" class="form-control input-md" >{{siparisAciklama}}</textArea>' +
		    		'						  </div>' +
		    		'						</div>' +
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'		<div class="col-md-4 column ui-sortable">' +
		    		'			<div class="box box-element" style="display: block;">' +
		    						
		    		'				<div class="view">' +
		    		
		    		'					<div class="form-group row">' +
		    		'					  <label class="col-md-5 control-label" for="siparisVerenKisi">Siparişi Veren Kişi</label>' +  
		    		'					  <div class="col-md-7">' +
		    		'					  <span id="siparisVerenKisi" class="form-control input-md">{{siparisVerenKisi}}</span>' +
		    							    
		    		'					  </div>' +
		    		'					</div>' +
		    							
		    		'					<div class="form-group row">' +
		    		'					  <label class="col-md-5 control-label" for="talepEdilenTeslimTarihi">Talep Edilen Teslim Tarihi</label>' +  
		    		'					  <div class="col-md-7">' +
		    		'					  <span id="talepEdilenTeslimTarihi" class="form-control input-md">{{talepEdilenTeslimTarihi}}</span>' +
		    							    
		    		'					  </div>' +
		    		'					</div>' +
		    						
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'	</div>' +
		    		'	<div class="row">' +
		    		'		<div class="col-md-4 column ui-sortable">' +
		    		'			<div class="box box-element" style="display: block;">' +
		    		'				<div class="view">' +
		    		'					<div class="form-group">' +
		    		'						<label class="col-md-5 control-label" for="adres">Adres</label>' +
		    		'						<div class="col-md-7">' +
		    		'							<textArea class="form-control" id="adres" >{{adres}}</textArea>' +
		    		'						</div>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'		<div class="col-md-4 column ui-sortable">' +
		    		'			<div class="box box-element" style="display: block;">' +
		    		'				<div class="view">' +
		    		'					<div class="form-group">' +
		    		'						<label class="col-md-5 control-label" for="adresAciklamasi">Adres Açıklaması</label>' +
		    		'						<div class="col-md-7">' +
		    		'							<textArea class="form-control" id="adresAciklamasi" >{{adresAciklamasi}}</textArea>' +
		    		'						</div>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'	</div>' +
		
		    		'	<div class="row">' +
		    		'		<div class="col-md-4 column ui-sortable">' +
		    		'			<div class="box box-element" style="display: block;">' +
		    		'				<div class="view">' +
		    		'					<div class="form-group">' +
		    		'						<label class="col-md-5 control-label" for="genelToplam">Genel Toplam</label>' +
		    		'						<div class="col-md-7">' +
		    		'							<span class="form-control" id="genelToplam" >{{genelToplam}}</span>' +
		    		'						</div>' +
		    		'					</div>' +
		    		'				</div>' +
		    		'			</div>' +
		    		'		</div>' +
		    		'	</div>' +
		
		    		'	<hr>' +
		
		    		'	<div class="detayTable">' +
		    		'		<div ui-grid="gridOptions" class="siparisDetay"></div>' +
		    		'	</div>' +
		    		'</div>' +
	              
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