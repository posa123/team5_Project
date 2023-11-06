	//  위도 저장
let dlat = '';
	//  경도 저장
let dlng = '';
	//  주소값 저장
let itradeplace = ''

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

					// 위도 경도 좌표 저장
					dlat = result.y;
					dlng = result.x;
					console.log(dlat); console.log(dlng);
					
					
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
}	// f end




// 안전거래소 등록함수
function insertEmediaion(){
	
	let eType = document.querySelector('.eType').value;
	
	console.log(eType); console.log(dlat); console.log(dlng); console.log(itradeplace);
	
	$.ajax({
		url: "/Ezen_teamB/EmediationController",
		method: "post",
		async: false,
		data: {eType : eType, dlat : dlat, dlng : dlng, itradeplace : itradeplace},
		success: r => {console.log(r)
			if(r){
				alert('등록성공')
				// 동록성공후 리랜더링
				printEmediation();
			}
			
		},
		error : e => {console.log(e)}
	})
	
}
printEmediation();


// 안전거래소 출력함수
function printEmediation(){
	
	$.ajax({
		url: "/Ezen_teamB/EmediationController",
		method: "get",
		async: false,
		data: "",
		success: r => {console.log(r)
			let eTable = document.querySelector('.eTable');
			let html =
			
			`
				<tr>
					<th> 거래소번호 </th>
					<th> 거래소타입 </th>
					<th> 거래소주소 </th>
					<th> 비고 </th>
				</tr>
			`;
			
			r.forEach( p => {
				html += `
					<tr>
						<td> ${ p.eno } </td>
						<td> ${ p.ename } </td>
						<td> ${ p.eadress } </td>
						<td class="udBtn">
						 <button onclick="eUpdate(${p.eno})" type="button">수정</button>
						 <button onclick="eDelete(${p.eno})" type="button">삭제</button>
						</td>
					</tr>
				`
			});
			
			eTable.innerHTML = html;
			
			
		},
		error : e => {console.log(e)}
	})
	
}	// f end


// 중개거래소 수정함수
function eUpdate(eno){console.log('수정함수 ' + eno)
	
	let eType = document.querySelector('.eType').value;
	
	$.ajax({
		url: "/Ezen_teamB/EmediationController",
		method: "put",
		async: false,
		data: {eno : eno, eType : eType, dlat : dlat, dlng : dlng, itradeplace : itradeplace},
		success: r => {console.log(r)
			if(r){
				alert('수정성공')
				// 수정완료후 리랜더링
				printEmediation();
			}
			
		},
		error : e => {console.log(e)}
	})
	
	
}


// 중개거래소 삭제함수
function eDelete(eno){console.log('삭제함수 ' + eno)
	
	let eType = document.querySelector('.eType').value;
	
	$.ajax({
		url: "/Ezen_teamB/EmediationController",
		method: "delete",
		async: false,
		data: {eno : eno},
		success: r => {console.log(r)
			if(r){
				alert('삭제성공')
				// 삭제완료후 리랜더링
				printEmediation();
			}
			
		},
		error : e => {console.log(e)}
	})
	
}









































