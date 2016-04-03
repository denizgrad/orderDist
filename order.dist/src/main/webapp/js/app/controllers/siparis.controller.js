var sipApp = angular.module("siparisModule", ["ui.grid"/* , "smart-table" */]);

sipApp.controller("siparisCtrl", function($scope, $http) {

	debugger;
		getSiparisStatus();	
	
//	var statusData = [];

	function getSiparisStatus() {
		debugger;
		// TODO service name ve post
//		alert("init out");
		$http.get('js/app/services/getSiparisStatus.json').success(function(response) {
					debugger;
//					alert("init in");
//					statusData = response;
					$scope.statusData = response.data;
					$scope.selectedStatus = response.selectedData;
					getSiparisList();
					
//					$scope.statusData.selectedOption = null;

				}).error(function(error) {
			debugger;

		})
	}
	$scope.gridOptions = {
			enableGridMenu: true,
		    columnDefs: [
		                 { field: 'oid', displayName: "Oid", visible: false, enableHiding: false},
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
		                 { field: 'siparisDurumu', displayName: "Sipariş Durumu", visible: true},
		                 { field: 'getDetay', displayName: "", visible: true, enableHiding: false, enableSorting: false, enableColumnMenu:false,
		                	 cellTemplate:
		                		 '<div class="btn-group">'+

		                		 '<button type="button" class="btn btn-default" style=" margin-left: 2px;" ng-click="grid.appScope.detayGoruntule(row.entity)">'+
		                		 	'<span class="glyphicon glyphicon-eye-open" ></span>'+
		                		 '</button>'+
		                		 '<button type="button" class="btn btn-default" ng-click="grid.appScope.durumGuncelle(row.entity)">'+
		                		 	'<span class="glyphicon glyphicon-pencil" ></span>'+
		                		 '</button>'+
		                		 '<button type="button" class="btn btn-default" style=" margin-right:2px; " ng-click="grid.appScope.barkodOlustur(row.entity)">'+
		                		 	'<span class="glyphicon glyphicon-barcode" ></span>'+
		                		 '</button>'+
		                		 '</div>'
		                		 
		                 }
		               // cellFilter: 'date:"yyyy-MM-dd"'
		    ], 
		    data : []
		    
	}
	
	function getSiparisList() {
		$http.get('js/app/services/getSiparisList.json').success(
				function(response) {
					debugger;
//					$scope.siparisCollection = response;
//					$scope.siparisList = response.siparisList;
					var len = response.length;
					if(len == 0){
						len = 1;
					}
					$scope.gridOptions.rowHeight = 35 * len + "px",
					$scope.gridOptions.data = response;
					
					
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
    	
    			$http.get('js/app/services/getSiparis.json').success(
    					function(response) {
    						debugger;
    						
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
    						
    						$scope.siparisDetayCollection = response.siparisDetay;
    						
    					}).error(function(error) {
    				debugger;

    			});
    	// scope artık directive in içerisi
//        $scope.buttonClicked = btnClicked;
        $scope.showModal = !$scope.showModal;
    };
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
		
		      		'	<div class = "siparisDetay">' +
		      		'		<table st-table="rowCollection" class="table table-striped">' +
		      		'			<thead>' +
		      		'			<tr>' +
		      		'					<th ng-hide="true">oid</th>' +
		      		'					<th ng-hide="true">siparisOid</th>' +
		      		'					<th>Sipariş Kalem Adı</th>' +
		      		'					<th>Adet</th>' +
		      		'					<th>Birim Fiyat</th>' +
		      		'					<th>Ara Toplam</th>' +
		      		'					<th>İndirim</th>' +
		      		'					<th>Kalem Fiyat</th>' +
		
		      		'				</tr>' +
		      		'			</thead>' +
		      		'			<tbody>' +
		      		'			<tr ng-repeat="siparis in siparisDetayCollection">' +
		      						
		      		'				<td ng-hide="true">{{siparis.oid}}</td>' +
		      		'				<td ng-hide="true">{{siparis.siparisOid}}</td>' +
		      		'				<td>{{siparis.siparisKalemAdi}}</td>' +
		      		'				<td>{{siparis.adet}}</td>' +
		      		'				<td>{{siparis.birimFiyat}}</td>' +
		      		'				<td>{{siparis.araToplam}}</td>' +
		      		'				<td>{{siparis.indirim}}</td>' +
		      		'				<td>{{siparis.kalemFiyat}}</td>' +
		      		'			</tr>' +
		      					
		      		'			</tbody>' +
		      		'		</table>' +	
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