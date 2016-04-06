//			$scope.asdad = function asdad() {
//
//				$http.post('siparis/islem/yarat', {
//					"key" : "asd",
//					"value" : "asd"
//				}).success(function(response) {
//					debugger;
//
//				});
//			}

/*
 * [
 {
 'repeat:5': {
 _id: '{{objectId()}}',
 siparisVerenFirma: '{{company().toUpperCase()}}',
 siparisVerenKisi: '{{firstName()}} {{surname()}}',
 tedarikEdenFirma: '{{company().toUpperCase()}}',
 tedarikEdenKisi: '{{firstName()}} {{surname()}}',
 siparisTarihi:'{{moment(this.date(new Date(2014, 0, 1), new Date())).format("LLLL")}}',
 teslimTarihi:'{{moment(this.date(new Date(2014, 0, 1), new Date())).format("LLLL")}}',
 araToplam:'{{floating(1000, 4000, 2, "$0,0.00")}}',
 kdv:"18",
 indirim:'{{floating(1000, 4000, 2, "$0,0.00")}}',
 genelToplam:'{{floating(1000, 4000, 2, "$0,0.00")}}',
 adres:'{{integer(100, 999)}} {{street()}}, {{city()}}, {{state()}}, {{integer(100, 10000)}}',
 siparisAciklama:'{{lorem(1, "words")}}',
 adresAciklama:'{{lorem(1, "words")}}',
 talepEdilenTeslimTarihi:'{{moment(this.date(new Date(2014, 0, 1), new Date())).format("LLLL")}}',
 siparisDurumu:'{{random("1", "2", "3","4","5","6","7")}}'
 }
 }
 ]
 * 
 * 
 */



/*
 * 
 * smart table tesliamt
 * ctrl
 * 
 *  //	$scope.getSiparis = function getSiparis(form) {
//		debugger;
//		if (form.$valid) {
//			$http.get('js/app/services/getSiparis.json').success(
//					function(response) {
//						debugger;
//						
//						$scope.oid = response.oid;
//						$scope.siparisVerenFirma = response.siparisVerenFirma;
//						$scope.siparisVerenKisi = response.siparisVerenKisi;
//						$scope.tedarikEdenFirma = response.tedarikEdenFirma;
//						$scope.tedarikEdenKisi = response.tedarikEdenKisi;
//						$scope.siparisTarihi = response.siparisTarihi;
//						$scope.teslimTarihi = response.teslimTarihi;
//						$scope.araToplam = response.araToplam;
//						$scope.kdv = response.kdv;
//						$scope.indirim = response.indirim;
//						$scope.genelToplam = response.genelToplam;
//						$scope.adres = response.adres;
//						$scope.siparisAciklama = response.siparisAciklama;
//						$scope.adresAciklama = response.adresAciklama;
//						$scope.talepEdilenTeslimTarihi = response.talepEdilenTeslimTarihi;
//						$scope.siparisDurumu = response.siparisDurumu;
//						
//						$scope.siparisCollection = response.siparisDetay;
//						
//					}).error(function(error) {
//				debugger;
//
//			});
//		}
//	}

html

<!-- 			<div class = "siparisDetay"> -->
<!-- 				<table st-table="rowCollection" class="table table-striped"> -->
<!-- 					<thead> -->
<!-- 					<tr> -->
<!-- 							<th ng-hide="true">oid</th> -->
<!-- 							<th ng-hide="true">siparisOid</th> -->
<!-- 							<th>Sipariş Kalem Adı</th> -->
<!-- 							<th>Adet</th> -->
<!-- 							<th>Birim Fiyat</th> -->
<!-- 							<th>Ara Toplam</th> -->
<!-- 							<th>İndirim</th> -->
<!-- 							<th>Kalem Fiyat</th> -->

<!-- 						</tr> -->
<!-- 					</thead> -->
<!-- 					<tbody> -->
<!-- 					<tr ng-repeat="siparis in siparisCollection"> -->
						
<!-- 						<td ng-hide="true">{{siparis.oid}}</td> -->
<!-- 						<td ng-hide="true">{{siparis.siparisOid}}</td> -->
<!-- 						<td>{{siparis.siparisKalemAdi}}</td> -->
<!-- 						<td>{{siparis.adet}}</td> -->
<!-- 						<td>{{siparis.birimFiyat}}</td> -->
<!-- 						<td>{{siparis.araToplam}}</td> -->
<!-- 						<td>{{siparis.indirim}}</td> -->
<!-- 						<td>{{siparis.kalemFiyat}}</td> -->
<!-- 					</tr> -->
					
<!-- 					</tbody> -->
<!-- 				</table>	 -->
<!-- 			</div> -->
 * 
 *
 *
 *
 */

//var barApp = angular.module("barcodeModule", ['ngAnimate', 'ui.bootstrap']);
//
//
//barApp.controller('barcodeCtrl', function ($scope, $uibModal) {
//
//
//  $scope.open = function (size) {
//
//    var modalInstance = $uibModal.open({
//      animation: true,
//      templateUrl: 'barcode.html',
//      controller: 'barcodeInstanceCtrl',
//      size: size,
//      resolve: {
//    	  // açılmadan once
//        items: function () {
//          return $scope.items;
//        }
//      }
//    });
//
//    modalInstance.result.then(function (selectedItem) {
//    	// success
//      $scope.selected = selectedItem;
//    }, function () {
//    	// error
//      
//    });
//  };
//
//});
//
//// Please note that $uibModalInstance represents a modal window (instance) dependency.
//// It is not the same as the $uibModal service used above.
//
//barApp.controller('barcodeInstanceCtrl', function($scope, $uibModalInstance, items) {
//
//	$scope.ok = function() {
//		$uibModalInstance.close($scope.selected.item);
//	};
//
//	$scope.cancel = function() {
//		$uibModalInstance.dismiss('cancel');
//	};
//
//	$scope.barkodOlustur = function barkodOlustur(siparis) {
//
//		// TODO http call 
//		$("#order-barcode").barcode({
//			code : "1234567",
//			crc : false
//		}, "int25", {
//			barWidth : 2,
//			barHeight : 30
//		});
//
//	}
//	$scope.yazdir = function yazdir(siparis) {
//
//		// TODO print out
//		alert("this gonna be printed");
//	}
//	
//});


//
//sipApp.controller('popupCtrl', function ($scope, $http) {
//    $scope.showModal = false;
////    $scope.buttonClicked = "";
//    $scope.detayGoruntule = function(siparis){
//    	debugger;
//    	
//    			$http.get('js/app/services/getSiparis.json').success(
//    					function(response) {
//    						debugger;
//    						
//    						$scope.oid = response.oid;
//    						$scope.siparisVerenFirma = response.siparisVerenFirma;
//    						$scope.siparisVerenKisi = response.siparisVerenKisi;
//    						$scope.tedarikEdenFirma = response.tedarikEdenFirma;
//    						$scope.tedarikEdenKisi = response.tedarikEdenKisi;
//    						$scope.siparisTarihi = response.siparisTarihi;
//    						$scope.teslimTarihi = response.teslimTarihi;
//    						$scope.araToplam = response.araToplam;
//    						$scope.kdv = response.kdv;
//    						$scope.indirim = response.indirim;
//    						$scope.genelToplam = response.genelToplam;
//    						$scope.adres = response.adres;
//    						$scope.siparisAciklama = response.siparisAciklama;
//    						$scope.adresAciklama = response.adresAciklama;
//    						$scope.talepEdilenTeslimTarihi = response.talepEdilenTeslimTarihi;
//    						$scope.siparisDurumu = response.siparisDurumu;
//    						
//    						$scope.siparisDetayCollection = response.siparisDetay;
//    						
//    					}).error(function(error) {
//    				debugger;
//
//    			});
//    	// scope artık directive in içerisi
////        $scope.buttonClicked = btnClicked;
//        $scope.showModal = !$scope.showModal;
//    };
//  });
//
//sipApp.directive('modal', function () {
//    return {
//      template: '<div class="modal fade">' + 
//          '<div class="modal-dialog">' + 
//            '<div class="modal-content">' + 
//              '<div class="modal-header">' + 
//              '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
//                 'Sipariş Detayı'+
//              '</div>' + 
//              '<div class="modal-body">' +
//		
//		      		'<div class="sipars">' +
//		      		'	<h3>Sipariş Bilgileri</h3>' +
//		      		'	<input type="hidden" name="oid" ng-value="oid" />' +
//		      		'	<div class="row">' +
//		      		'		<div class="col-md-4 column ui-sortable">' +
//		      		'			<div class="box box-element" style="display: block;">' +
//		      		'				<div class="view">' +
//		      		'						<div class="form-group row">' +
//		      		'						  <label class="col-md-5 control-label" for="siparisVerenFirma">Siparişi Veren Firma</label>' +  
//		      		'						  <div class="col-md-7">' +
//		      		'						  	<span id="siparisVerenFirma" class="form-control input-md"> {{siparisVerenFirma}}</span>' +
//		      		'						  </div>' +
//		      		'						</div>' +
//		      		'						<div class="form-group row">' +
//		      		'						  <label class="col-md-5 control-label" for="siparisTarihi">Sipariş Tarihi</label>' +  
//		      		'						  <div class="col-md-7">' +
//		      		'						  <span id="siparisTarihi" class="form-control input-md">{{siparisTarihi}}</span>' +
//		      		'						  </div>' +
//		      		'						</div>' +
//		      		'						<div class="form-group row">' +
//		      		'						  <label class="col-md-5 control-label" for="siparisAciklamasi">Sipariş Açıklaması</label>' +  
//		      		'						  <div class="col-md-7">' +
//		      		'						  <textArea id="siparisAciklamasi" class="form-control input-md" >{{siparisAciklama}}</textArea>' +
//		      		'						  </div>' +
//		      		'						</div>' +
//		      		'				</div>' +
//		      		'			</div>' +
//		      		'		</div>' +
//		      		'		<div class="col-md-4 column ui-sortable">' +
//		      		'			<div class="box box-element" style="display: block;">' +
//		      						
//		      		'				<div class="view">' +
//		      		
//		      		'					<div class="form-group row">' +
//		      		'					  <label class="col-md-5 control-label" for="siparisVerenKisi">Siparişi Veren Kişi</label>' +  
//		      		'					  <div class="col-md-7">' +
//		      		'					  <span id="siparisVerenKisi" class="form-control input-md">{{siparisVerenKisi}}</span>' +
//		      							    
//		      		'					  </div>' +
//		      		'					</div>' +
//		      							
//		      		'					<div class="form-group row">' +
//		      		'					  <label class="col-md-5 control-label" for="talepEdilenTeslimTarihi">Talep Edilen Teslim Tarihi</label>' +  
//		      		'					  <div class="col-md-7">' +
//		      		'					  <span id="talepEdilenTeslimTarihi" class="form-control input-md">{{talepEdilenTeslimTarihi}}</span>' +
//		      							    
//		      		'					  </div>' +
//		      		'					</div>' +
//		      						
//		      		'				</div>' +
//		      		'			</div>' +
//		      		'		</div>' +
//		      		'	</div>' +
//		      		'	<div class="row">' +
//		      		'		<div class="col-md-4 column ui-sortable">' +
//		      		'			<div class="box box-element" style="display: block;">' +
//		      		'				<div class="view">' +
//		      		'					<div class="form-group">' +
//		      		'						<label class="col-md-5 control-label" for="adres">Adres</label>' +
//		      		'						<div class="col-md-7">' +
//		      		'							<textArea class="form-control" id="adres" >{{adres}}</textArea>' +
//		      		'						</div>' +
//		      		'					</div>' +
//		      		'				</div>' +
//		      		'			</div>' +
//		      		'		</div>' +
//		      		'		<div class="col-md-4 column ui-sortable">' +
//		      		'			<div class="box box-element" style="display: block;">' +
//		      		'				<div class="view">' +
//		      		'					<div class="form-group">' +
//		      		'						<label class="col-md-5 control-label" for="adresAciklamasi">Adres Açıklaması</label>' +
//		      		'						<div class="col-md-7">' +
//		      		'							<textArea class="form-control" id="adresAciklamasi" >{{adresAciklamasi}}</textArea>' +
//		      		'						</div>' +
//		      		'					</div>' +
//		      		'				</div>' +
//		      		'			</div>' +
//		      		'		</div>' +
//		      		'	</div>' +
//		
//		      		'	<div class="row">' +
//		      		'		<div class="col-md-4 column ui-sortable">' +
//		      		'			<div class="box box-element" style="display: block;">' +
//		      		'				<div class="view">' +
//		      		'					<div class="form-group">' +
//		      		'						<label class="col-md-5 control-label" for="genelToplam">Genel Toplam</label>' +
//		      		'						<div class="col-md-7">' +
//		      		'							<span class="form-control" id="genelToplam" >{{genelToplam}}</span>' +
//		      		'						</div>' +
//		      		'					</div>' +
//		      		'				</div>' +
//		      		'			</div>' +
//		      		'		</div>' +
//		      		'	</div>' +
//		
//		      		'	<hr>' +
//		
//		      		'	<div class = "siparisDetay">' +
//		      		'		<table st-table="rowCollection" class="table table-striped">' +
//		      		'			<thead>' +
//		      		'			<tr>' +
//		      		'					<th ng-hide="true">oid</th>' +
//		      		'					<th ng-hide="true">siparisOid</th>' +
//		      		'					<th>Sipariş Kalem Adı</th>' +
//		      		'					<th>Adet</th>' +
//		      		'					<th>Birim Fiyat</th>' +
//		      		'					<th>Ara Toplam</th>' +
//		      		'					<th>İndirim</th>' +
//		      		'					<th>Kalem Fiyat</th>' +
//		
//		      		'				</tr>' +
//		      		'			</thead>' +
//		      		'			<tbody>' +
//		      		'			<tr ng-repeat="siparis in siparisDetayCollection">' +
//		      						
//		      		'				<td ng-hide="true">{{siparis.oid}}</td>' +
//		      		'				<td ng-hide="true">{{siparis.siparisOid}}</td>' +
//		      		'				<td>{{siparis.siparisKalemAdi}}</td>' +
//		      		'				<td>{{siparis.adet}}</td>' +
//		      		'				<td>{{siparis.birimFiyat}}</td>' +
//		      		'				<td>{{siparis.araToplam}}</td>' +
//		      		'				<td>{{siparis.indirim}}</td>' +
//		      		'				<td>{{siparis.kalemFiyat}}</td>' +
//		      		'			</tr>' +
//		      					
//		      		'			</tbody>' +
//		      		'		</table>' +	
//		      		'	</div>' +
//		      			
//		      		'</div>' +	              
//              '</div>' + 
//            '</div>' + 
//          '</div>' + 
//        '</div>',
//      restrict: 'E',
//      transclude: true,
//      replace:true,
//      scope:true,
//      link: function postLink(scope, element, attrs) {
//          scope.$watch(attrs.visible, function(value){
//          if(value == true)
//            $(element).modal('show');
//          else
//            $(element).modal('hide');
//        });
//
//        $(element).on('shown.bs.modal', function(){
//          scope.$apply(function(){
//            scope.$parent[attrs.visible] = true;
//          });
//        });
//
//        $(element).on('hidden.bs.modal', function(){
//          scope.$apply(function(){
//            scope.$parent[attrs.visible] = false;
//          });
//        });
//      }
//    };
//  });