
// 검색 필터를 위한 대분류 소분류 번호
let filterNum = -1
// 대분류 소분류 식별자
let filterCategory = ''



// 전체 물품 출력
function getItemList(  ){
	
	clusterer.clear()
	//var infowindow
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data: { type : "getIndexItemList",
				filterCategory : filterCategory,	// 대분류 소분류 식별자
				filterNum : filterNum, 				// 검색 필터를 위한 대분류 소분류 번호
		},
		success: result => {
			
			console.log(result)
			
			var markers
			// 데이터에서 좌표 값을 가지고 마커를 표시합니다
			// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
			markers = result.map( s => {
				
				// 중개거래소 1개 당 마커 1개 '객체' 선언
				let marker = new kakao.maps.Marker({
					// 마커 1개의 위치 지정
					position: new kakao.maps.LatLng(s.dlat, s.dlng),
					clickable: true
				});
				// 해당 마커에 클릭이벤트 지정
				
				// 인포윈도우를 선언
				var infowindow = new kakao.maps.InfoWindow({
				    position : new kakao.maps.LatLng(s.dlat, s.dlng), 
				    content : `
						<div class="col">
								<div class="card">
									
									<img src="/Ezen_teamB/jsp/item/img/${ s.pimg == null ? 'defaultImg.png' : s.pimg }" class="card-img-top" alt="...">
									
									<div  class="card-body">
										<div class="itemCardTitle"> ${s.ititle} </div>
										<div class="itemCardPrice"> ${s.iprice}원 </div>
										<div class="itemCardAdress"> 안전결제 : ${ s.isafepayment == 0 ? '사용' : '미사용' } </div>
									</div>
									
								</div>
							</a>
						</div>
					`
				});
				
				  
				// 마우스를 올렸을 때 제품 정보 표시
				kakao.maps.event.addListener( marker, 'mouseover', function() {
					
					infowindow.open(map, marker);
					
				});
				// 마우스가 범위에서 나갔을 때 정보 제거
				kakao.maps.event.addListener( marker, 'mouseout', function() {
					
					infowindow.close(map, marker);
					
				});
				// 마커를 클릭하였을 때 상세물품 페이지로 이동
				kakao.maps.event.addListener( marker, 'click', function() {
					
					location.href = `/Ezen_teamB/jsp/item/detaileditems.jsp?ino=${s.ino}&itrade=2`;
					
				});
				
				// return 을 통해 물품 마커 1개를 markers에 대입 후 다음 중개거래소 마커들 선언
				return marker;
				
			});
			
			// 클러스터러에 마커들을 추가합니다
			clusterer.addMarkers(markers);
			
		},
		error: e => {
			
			console.log('실패')
			console.log(e)
		}
	})
	
}

// 지도 출력
var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
	center : new kakao.maps.LatLng(37.5663, 126.9779), // 지도의 중심좌표
	level : 10 // 지도의 확대 레벨
});

/* 카카오맵 클러스터 [ 마커 여러개일때 집합모양 ] */
var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
    minLevel: 10, // 클러스터 할 최소 지도 레벨
    disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
});

kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {
    var level = map.getLevel()-1;
    map.setLevel(level, {anchor: cluster.getCenter()});
});


// 카카오지도에서 드래그를 하고 끝났을 때 1번 함수 재실행
kakao.maps.event.addListener(map, 'dragend', function(){
	getItemList();
});

// 카카오지도에서 스크롤확대/축소 하고 끝났을 때 1번 함수 재실행
kakao.maps.event.addListener(map, 'idle', function(){
	getItemList();
});

getItemList()

// 대분류 카테고리 출력
getMainCategory()
function getMainCategory(){
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data: { type : "getMainCategory" },
		success: s => {
			
			let html = `<tr>`
			for( let i=0; i<s.length; i++ ){
				
				if( i==3 || ( i>2 && i%3==0 ) ){
					html += `</tr><tr>`
				}

				html += `<td onclick="outputSubCategory(${s[i].uno}, '${s[i].uname}')"> ${s[i].uname} </td>`
			}
			html += `</tr>`
			
			document.querySelector('.outputUmaincategory').innerHTML = html 
		},
		error: e => {
			console.log('에러발생')
		}
	})
	
}

// 대분류 카테고리 필터
function outputSubCategory( uno, uname ){
	
	// 상세 카테고리 필터기능 수행
	filterNum = uno
	filterCategory = 'uno';
	document.querySelector('.selCategory1').innerHTML = ` 선택된 카테고리 : ${uname}`
	document.querySelector('.selCategory2').innerHTML = ``
	getItemList();
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		data: { type : "getSubCategory", uno : uno },
		success: s => {
			
			// 소분류 카테고리 출력
			let html = `
				<div class="guideLabel">  </div>
					<table class="outputDsubcategory">
						<tr>`
			for( let i=0; i<s.length; i++ ){
				
				if( i==3 || ( i>2 && i%3==0 ) ){
					html += `</tr><tr>`
				}
				html += `<td onclick="subCategoryFilter(${s[i].dno}, '${s[i].dname}' )"> ${s[i].dname} </td>`
			}
			
			// 테이블의 간격을 일정하게 유지하기 위해 
			// 소분류의 길이가 5이하일 경우 5까지 부족한 td의 갯수만큼 추가 
			if( s.length < 5 ){
				
				for(let i=0; i<5-s.length; i++){
					html += `<td></td>`	
				}
			}
			
			html += `</table></tr>`
			
			document.querySelector('.bottomCategory').innerHTML = html 
			
		},
		error: e => {
			console.log('에러발생')
		}
		
	})
}

// 소분류 카테고리 필터
function subCategoryFilter( dno, dname ){
	
	// 상세 카테고리 필터기능 수행
	document.querySelector('.selCategory2').innerHTML = ` > ${dname}`
	filterNum = dno
	filterCategory = 'dno'
	getItemList();
	
}

// 카테고리 전체보기
function searchAll(){
	
	filterCategory = ''
	searchWord = ''
	document.querySelector('.selCategory1').innerHTML = ``
	document.querySelector('.selCategory2').innerHTML = ``
	
	getItemList();
}


























