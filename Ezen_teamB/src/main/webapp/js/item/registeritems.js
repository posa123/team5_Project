
/* ================================ 카테고리 */
if( !loginState ){
	alert('물품등록페이지는 로그인 후 이용가능합니다')
	location.href = "/Ezen_teamB/jsp/item/checkitems.jsp"
}

getMainCategory()
// 1. 카테고리 대분류/소분류 출력
function getMainCategory(){
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data : {type : "getMainCategory"},
		success: s => {
			
			let mainUl = document.querySelector('.mainUl');
			let mainhtml = ``;
			
			s.forEach( category => {
				mainhtml += `<li> <button onclick="getSubCategory( ${category.uno}, '${category.uname}' )" type="button"> ${ category.uname } </button> </li>`;
			});

			mainUl.innerHTML = mainhtml;
			
		},
		error: e => {
			console.log('에러 발생')
		}
	})
	
}

// 2. 대분류 카테고리를 클릭하였을 때 해당되는 소분류 카테고리 출력
function getSubCategory( uno, uname ){
	
	// 대분류 카테고리를 선택할 시 '선택한 카테고리' 출력화면을 해당 대분류로 초기화
	document.querySelector('.checkMain').innerHTML = `${uname} > `
	document.querySelector('.checkSub').innerHTML = ``;
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data : {type : "getSubCategory", uno : uno},
		success: s => {
			let subUl = document.querySelector('.subUl');
			let subhtml = ``;
			
			// 해당 대분류에 소분류 카테고리가 없으면 등록되어있지 않음을 출력
			if( s.length == 0 ) subhtml += `<li> <button onclick="" type="button"> 현재 소분류 카테고리가<br> 등록되어있지 않습니다 </button> </li>`;
			s.forEach( category => {
				subhtml += `<li> <button onclick="selectedCategory( ${category.dno}, '${category.dname}' )" type="button"> ${ category.dname } </button> </li>`;
			});

			// 선택된 카테고리를 출력
			subUl.innerHTML = subhtml;
			
		},
		error: e => {
			console.log('에러 발생')
		}
	})
}

// 3. 소분류 카테고리 선택 시 form 객체 생성
	// form 객체 전송을 위해 dno 변수 저장
function selectedCategory( dno, dname ){
	
	document.querySelector('.checkSub').innerHTML = `${dname}`;
	document.querySelector('.dno').value = `${dno}`;

}



/* ============================= 카테고리 end */


/* ============================= 거래방식 */
// JS에서 CSS 제어
let deliveryCSS = document.getElementsByClassName("delivery")[0].style;		// 배송
let faceToFaceCSS = document.getElementsByClassName("faceToFace")[0].style;	// 대면거래
let brokerageCSS = document.getElementsByClassName("brokerage")[0].style;	// 중개거래소


// 현재 거래방식을 저장하는 변수
let itrade = 0;
	// 1 배송, 2 대면거래, 3 중개거래


// 1. 거래방식 - 배송 방식 클릭하였을 때
function delivery(){
	deliveryCSS.backgroundColor = "#6AAFE6";
	faceToFaceCSS.backgroundColor = "#EFEFEF";
	brokerageCSS.backgroundColor = "#EFEFEF";
	 
	// 거래방식이 변경될때마다 대면거래 위치정보 초기화
	dlat = '';
	dlng = '';
	itradeplace = '';
	document.querySelector('.selectedAddress span').innerHTML = '';
	
	// 거래방식이 변경될 때마다 중개거래소 정보 초기화
	emediationInfo = { eno : '', ename : '', eadress : '' }
	document.querySelector('.emediationName').innerHTML = '';
	document.querySelector('.emediationAdress').innerHTML = '';
	
	 
	document.getElementById("outputMapField").style.display = "none";
	document.getElementById("outputMapField2").style.display = "none";
	
	itrade = 1;
}







// 2. 거래방식 - 대면거래 방식 클릭하였을 때

	// 대면거래 이용 시 위도 저장
let dlat = '';
	// 대면거래 이용 시 경도 저장
let dlng = '';
	// 대면거래 이용 시 주소값 저장
let itradeplace = ''

function faceToFace(){
	deliveryCSS.backgroundColor = "#EFEFEF";
	faceToFaceCSS.backgroundColor = "#6AAFE6";
	brokerageCSS.backgroundColor = "#EFEFEF";
	
	// 거래방식이 변경될때마다 대면거래 위치정보 초기화
	dlat = '';
	dlng = '';
	itradeplace = '';
	
	// 거래방식이 변경될 때마다 중개거래소 정보 초기화
	emediationInfo = { eno : '', ename : '', eadress : '' }
	document.querySelector('.emediationName').innerHTML = '';
	document.querySelector('.emediationAdress').innerHTML = '';
	
	document.getElementById("outputMapField").style.display = "block";
	document.getElementById("outputMapField").style.height = "450px";
	//document.getElementsByClassName("outputMapField")[0].style.display = "block";
	document.getElementById("outputMapField2").style.display = "none";
	
	itrade = 2;
	
}	// 거래방식 - 대면거래 방식 클릭하였을 때 end
	

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
	center: new kakao.maps.LatLng(37.5663, 126.9779), // 지도의 중심좌표
	level: 6 // 지도의 확대 레벨
};


// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();


//마커 생성
var marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map
});
var infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다





// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

	// 클릭한 위도, 경도 정보를 가져옵니다 
	var latlng = mouseEvent.latLng;

	// 마커 위치를 클릭한 위치로 옮깁니다
	marker.setPosition(latlng);

	var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	message += '경도는 ' + latlng.getLng() + ' 입니다';


	// 위 경도 좌표 저장
		// form객체 전송을 위함
	dlat = latlng.getLat();
	dlng = latlng.getLng();
	
	
});
	

// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            
            itradeplace = !!result[0].road_address ? result[0].road_address.address_name : result[0].address.address_name ;
			
			
            // 마커를 클릭한 위치에 표시합니다 
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(map);
	
			document.querySelector('.selectedAddress span').innerHTML = `${itradeplace}`;
	
        }   
    });
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(dlng, dlat, callback);
    
}





// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);


// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');

        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }    
}

function sample5_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = data.address; // 최종 주소 변수


			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample5_address").value = addr;
			// 주소로 상세 정보를 검색
			geocoder.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면
				if (status === daum.maps.services.Status.OK) {

					var result = results[0]; //첫번째 결과의 값을 활용
					
					// 주소 저장
					itradeplace = result.address_name
					document.querySelector('.selectedAddress span').innerHTML = `${itradeplace}`;

					// 위도 경도 좌표 저장
					dlat = result.y;
					dlng = result.x;
					
					
					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					
					
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map.relayout();
					// 지도 중심을 변경한다.
					map.setCenter(coords);
					// 마커를 결과값으로 받은 위치로 옮긴다.
					marker.setPosition(coords)
				}
			});
		}
	}).open();
}


// 3. 거래방식 - 중개거래소 방식 클릭하였을 때
	// 중개거래소 정보
		// pk
		// 업체명
		// 주소
let emediationInfo = { eno : '', ename : '', eadress : '' }
	// 지도에 표시된 마커 객체를 가지고 있을 배열입니다



function brokerage(){
	
	// 클러스터가 출력되는 것을 막기 위해 클러스터 배열을 clear함
	clusterer2.clear()
	
	

	deliveryCSS.backgroundColor = "#EFEFEF";
	faceToFaceCSS.backgroundColor = "#EFEFEF";
	brokerageCSS.backgroundColor = "#6AAFE6";

	// 거래방식이 변경될때마다 대면거래 위치정보 초기화
	dlat = '';
	dlng = '';
	itradeplace = '';
	document.querySelector('.selectedAddress span').innerHTML = ''

	document.getElementById("outputMapField").style.display = "none";
	document.getElementById("outputMapField2").style.display = "block";
	document.getElementById("outputMapField2").style.height = "450px";

	
	itrade = 3;
	
	
	// DB에 저장된 중개거래소 불러오기
		// 각 중개거래소별 마커 추가하기
	$.ajax({
		url:"/Ezen_teamB/ItemController",
		method: 'get',
		async: false,
		data: { type : 'getEmediation' },
		success : result => {
			var markers
			// 데이터에서 좌표 값을 가지고 마커를 표시합니다
			// 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
			markers = result.map( s => {
				
				// 중개거래소 1개 당 마커 1개 '객체' 선언
				let maker2 = new kakao.maps.Marker({
					// 마커 1개의 위치 지정
					position: new kakao.maps.LatLng(s.elat, s.elng),
					clickable: true
				});
				// 해당 마커에 클릭이벤트 지정
				kakao.maps.event.addListener( maker2, 'click', function() {
					document.querySelector('.emediationName').innerHTML = s.ename
					document.querySelector('.emediationAdress').innerHTML = s.eadress
					
					emediationInfo = { eno : s.eno, ename : s.ename, eadress : s.eadress }
					
				});
				
				// return 을 통해 중개거래소 마커 1개를 markers에 대입 후 다음 중개거래소 마커들 선언
				return maker2;
				
			});
			
			// 클러스터러에 마커들을 추가합니다
			clusterer2.addMarkers(markers);

		},
		error: e => {
			console.log('에러발생')
		}

	})
	
	
}	


// 지도를 생성합니다    
var map2 = new kakao.maps.Map(document.getElementById('map2'), { // 지도를 표시할 div
    center : new kakao.maps.LatLng(37.5663, 126.9779), // 지도의 중심좌표
    level : 7 // 지도의 확대 레벨
});

/* 카카오맵 클러스터 [ 마커 여러개일때 집합모양 ] */
var clusterer2 = new kakao.maps.MarkerClusterer({
    map: map2, // 마커들을 클러스터로 관리하고 표시할 지도 객체
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
    minLevel: 10, // 클러스터 할 최소 지도 레벨
    disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
});

kakao.maps.event.addListener(clusterer2, 'clusterclick', function(cluster) {
    var level = map2.getLevel()-1;
    map2.setLevel(level, {anchor: cluster.getCenter()});
});


brokerage()
delivery()

// 주소-좌표 변환 객체를 생성합니다
var geocoder2 = new kakao.maps.services.Geocoder();

function sample5_execDaumPostcode2() {
	new daum.Postcode({
		oncomplete: function(data) {
			var addr = data.address; // 최종 주소 변수


			// 주소 정보를 해당 필드에 넣는다.
			document.getElementById("sample5_address").value = addr;
			// 주소로 상세 정보를 검색
			geocoder2.addressSearch(data.address, function(results, status) {
				// 정상적으로 검색이 완료됐으면
				if (status === daum.maps.services.Status.OK) {

					var result = results[0]; //첫번째 결과의 값을 활용
					
					// 해당 주소에 대한 좌표를 받아서
					var coords = new daum.maps.LatLng(result.y, result.x);
					
					// 지도를 보여준다.
					mapContainer.style.display = "block";
					map2.relayout();
					// 지도 중심을 변경한다.
					map2.setCenter(coords);

				}
			});
		}
	}).open();
}

// 카카오지도에서 드래그를 하고 끝났을 때 1번 함수 재실행
kakao.maps.event.addListener(map2, 'dragend', function(){
	brokerage();
});

// 카카오지도에서 스크롤확대/축소 하고 끝났을 때 1번 함수 재실행
kakao.maps.event.addListener(map2, 'idle', function(){
	brokerage();
});



// 거래방식 - 대면거래 방식 클릭하였을 때 end







/* ============================= 거래방식 end */



/* ============================= 이미지 출력/삭제 */

// input 타입 file 1~10출력구역
for(let i=1; i<=10; i++){
	document.querySelector('.inpuImgBox').innerHTML += `
		<div class="hiddenBox${i}"><input onchange="fileUpload(this, ${i})" type="file" id="uploadFile${i}" name="file" style="display: none"></div>
	`
}


// 1-1 이미지 파일 업로드
function fileUpload( mimg, idNum ){
	

	// 파일을 읽는 객체 생성
	let fileReader = new FileReader();
	// 파일의 정보 읽기
	fileReader.readAsDataURL( mimg.files[0] );	
	
	// 이미지를 출력할 구역 생성
	document.querySelector('.outputImg').innerHTML +=	`
			<div class="outBoxFiled${idNum}">
				<img class="img${idNum}" alt="" src=""/>
				<button onclick="fileDelete(${idNum})" type="button">x</button>
			</div>
		` 
		
		//각 출력되는 이미지에 대한 식별자는 이미지를 담는 배열의 길이로 함

	// 읽어온 파일을 불러옴
	fileReader.onload = e => {
		document.querySelector(`.img${idNum}`).src = e.target.result;	
	}
	
	
	// 파일 라벨의 for 포인터 변경
	let checkImg = false;
		// 10개의 input 파일 타입에 파일 객체가 존재하는지 탐색
	for( let i=1; i<=10; i++ ){
		var fileCheck = document.getElementById(`uploadFile${i}`).value;
		
		// 파일객체가 존재하지 않는 경우
			// 라벨이 가리키는 id식별자( =for )를 변경
		if(!fileCheck){
			document.querySelector('.labelBox').innerHTML = `
					<label class="input-file-button" for="uploadFile${i}">
											
						<img class="labelImg" src="/Ezen_teamB/jsp/item/img/uploadImgLogo.png"/>
											
					</label>
				`;
			checkImg = true;
			break;
		}
	}
	// 등록된 이미지 파일이 10개가 넘었을 경우 
	// 이후 라벨을 클릭할 시 접근을 금지하기 위해
	// 접근 금지 메서드 기능 삽입
	if( !checkImg ){
		document.querySelector('.labelBox').innerHTML = `
				<label onclick='forbidden()' class="input-file-button"">
										
					<img class="labelImg" src="/Ezen_teamB/jsp/item/img/uploadImgLogo.png"/>
										
				</label>
			`;
	}
	
	
}
// 1-2 등록된 이미지 파일이 10개가 넘었을 경우 라벨 클릭 금지
function forbidden(){
	alert('이미지는 최대 10개까지 등록할 수 있습니다')
}

// 2 선택된 이미지 파일 삭제
function fileDelete( idNum ){
	
	// 삭제할 파일 구역을 초기화
	$(`#uploadFile${idNum}`).val('');
	document.querySelector(`.outBoxFiled${idNum}`).innerHTML = ``
	
	
	// 파일 라벨의 for 포인터 변경
	for( let i=1; i<=10; i++ ){
		var fileCheck = document.getElementById(`uploadFile${i}`).value;
		
		// 파일객체가 존재하지 않는 경우
			// 라벨이 가리키는 id식별자( =for )를 변경
		if(!fileCheck){
			document.querySelector('.labelBox').innerHTML = `
					<label class="input-file-button" for="uploadFile${i}">
											
						<img class="labelImg" src="/Ezen_teamB/jsp/item/img/uploadImgLogo.png"/>
											
					</label>
				`;

			break;
		}	
	}
			
}


/* ============================= 이미지 출력/삭제 end */


/* ============================= 제품등록 */

function registerItems(){
	
	/* -------- 유효성 검사 -------- */
	
	// 각 입력 구역에 value 확인
	let ititle = document.querySelector('.ititle').value	// 입력된 제목
	let icontent = document.querySelector('.icontent').value	// 입력된 내용
	let dno = document.querySelector('.dno').value			// 선택된 소분류 카테고리
	let iprice = document.querySelector('.iprice').value	// 입력된 가격
	
	if( ititle == '' || ititle == null ){
		alert('제목을 입력하여 주십시오')
		return;
	}
	if( ititle.length > 50 ){
		alert('제목은 최대 50글자까지 가능합니다')
		return;
	}
	if( icontent == '' || icontent == null ){
		alert('제품설명을 입력하여 주십시오')
		return;
	}
	if( dno == '' || dno == null ){
		alert('카테고리를 선택하여 주십시오')
		return;
	}
	if( itrade < 1 || itrade > 3 ){
		alert('거래방식을 선택하여 주십시오')
		return;
	}
	if( iprice == '' || iprice==null ){
		alert('가격을 입력하여 주십시오')
		return;
	}
	
	// 거래방식이 대면거래임에도 거래위치를 지정하지 않을 경우
	if( itrade==2 && (dlat == '' || dlat == '' || itradeplace=='') ){
		alert('대면거래는 거래위치를 지정하여야 합니다')
		return;
	}
	// 거래방식이 중개거래임에도 중개거래소를 지정하지 않을 경우
	if( itrade==3 && emediationInfo.eno == '' ){
		alert('중개거래는 중개거래소를 지정하여야 합니다')
		return;
	}
	
	/* -------- 물품등록 전 form 데이터 setting -------- */
	
	// 10개의 인풋박스 중 파일이 첨부되어있지 않은 input 삭제 form데이터 초기화
		// 해당 input박스의 부모요소(div)를 공백으로 초기화
	for( let i=1; i<=10; i++ ){
		var fileCheck = document.getElementById(`uploadFile${i}`).value;
		if( !fileCheck ){
			document.querySelector(`.hiddenBox${i}`).innerHTML = ``;
		}
	}
	
	
	// 1. form dom객체 호출
	let registerForm = document.querySelectorAll('.registerForm')[0];
	let formData = new FormData( registerForm );
	
	
	// name식별자에 해당되지 않는 데이터를 폼데이터에 별도로 추가 
	formData.set('itrade', itrade )				// 거래방식 : 1 배송, 2 대면거래, 3 중개거래

	// 서블릿에서 doPost에서 기능 구분을 위해 타입 생성 후 폼데이터에 별도로 추가
	let type = "registerItems"
	formData.set('type', type )
	
	// 거래방식이 '대면거래'일 시 대면거래에 대한 위경도, 주소값 저장
	if( itrade == 2 ){
		formData.set('dlat', dlat)					// 대면거래 이용 시 위도 저장
		formData.set('dlng', dlng )					// 대면거래 이용 시 경도 저장
		formData.set('itradeplace', itradeplace )	// 대면거래 이용 시 주소값 저장
	}
	
	if( itrade == 3 ){
		formData.set('eno', emediationInfo.eno )				// 중개거래소 pk 저장
		formData.set('itradeplace', emediationInfo.eadress )	// 중개거래소 이용 시 주소값 저장
	}

	/* -------- ajax 통신 -------- */
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "post",
		data: formData,
		contentType: false,
		processData: false,
		success: result =>{
			
			if(result){
				alert('등록이 완료되었습니다')
				location.href = "/Ezen_teamB/jsp/item/checkitems.jsp"
			}
			
		},
		error: e =>{
			console.log(e)
			console.log('제품등록 에러발생')
		}
	})
} 

/* ============================= 제품등록 end */

/* ============================= 제품상세페이지 조회 */














