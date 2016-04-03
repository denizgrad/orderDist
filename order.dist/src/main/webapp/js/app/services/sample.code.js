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