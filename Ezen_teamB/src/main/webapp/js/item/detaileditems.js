let ino = new URL( location.href ).searchParams.get("ino");
let itrade = new URL( location.href ).searchParams.get("itrade");



getDetailedItems();
function getDetailedItems(){
	
	$.ajax({
		
		url: "/Ezen_teamB/ItemController",
		method: "get",
		data: { 
			type : "getDetailedItems", 
			ino : ino,
			itrade : itrade
		},
		success: s => {
			
			// 1. 이미지의 개수만큼 이미지 출력구역 생성 후 이미지 출력
				// * 메인 이미지 출력공간은 이미지 존재유무와 관계없이 생성되어야함
					// 슬라이드별 버튼 구역
			let html1 = `<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>`
					// 이미지 출력구역
			let html2 = `<div class="carousel-item active imgInfo">
							<img src="/Ezen_teamB/jsp/item/img/${ Object.values(s.imgList)[0] == null ? 'defaultDetailedImg.png' : s.imgList[0]}" 
							class="itemImg" alt="...">
						</div>`
			
			for(let i=1; i<Object.values(s.imgList).length; i++){
				
				html1 += `
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="${i}" aria-label="Slide ${i+1}">
					</button>`
					
				html2 += `
					<div class="carousel-item">
						<img src="/Ezen_teamB/jsp/item/img/${s.imgList[i]}" class="d-block w-100" alt="...">
					</div>`
			}
			
			document.querySelector('.slideBtn').innerHTML = html1;
			document.querySelector('.infoImgBox').innerHTML = html2;

			// 2. 판매자 id 출력
			document.querySelector('.outputBuyerId').innerHTML = s.mid;
			// 3. 등록일자 출력
			document.querySelector('.uploadDate').innerHTML = s.idate;
			// 4. 제목 출력
			document.querySelector('.itemTitle').innerHTML = s.ititle;
			// 5. 거래방식 출력
			document.querySelector('.itemTradeWord').innerHTML = s.itrade==1 ? '배송' : s.itrade==2 ? '대면거래' : '중개거래';
			// 6. 판매가격 출력
			document.querySelector('.itemPriceWord').innerHTML = s.iprice;
			// 7. 안전결제사용여부 출력
			document.querySelector('.itemSafePaymentWord').innerHTML = s.isafepayment==0 ? '미사용' : '사용';
				// 안전결제 버튼 출력
			if( s.isafepayment == 1 ){
				document.querySelector('.buttonField').innerHTML +=
				`<button onclick="responseSafepay()" class="safepayResponseBtn" type="button"> 안전결제요청 </button>`;
			}
			// 8. 판매 내용 출력
			document.querySelector('.outputContent').innerHTML = s.icontent;
			
			// 9. 주소 / 지도출력
			if( s.itradeplace==null ){
				
				document.querySelector('.adressInfo').innerHTML = 
				'※ 거래방식이 \'배송\'일 경우 위치는 출력되지 않습니다'
				
				document.querySelector('.mapBox').innerHTML = ``
				
			} else {
				
				document.querySelector('.adressInfo').innerHTML = '거래위치 : '+s.itradeplace
				
				/* 지도출력 */
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = { 
				        center: new kakao.maps.LatLng(s.lat, s.lng), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };
				
				var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 마커가 표시될 위치입니다 
				var markerPosition  = new kakao.maps.LatLng(s.lat, s.lng); 
				
				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
				    position: markerPosition
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);
			}

		},
		error: e => {
			console.log('에러발생')
		}
		
		
	})
	
}	// getDetailedItems function end


// 안전결제요청 버튼을 클릭하였을 때
function responseSafepay(){
	
	if( !loginState ){
		alert('로그인 후 이용하시기 바랍니다')
		return
	}
	
	let result;
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "post",
		async: false,
		data: {
			type : "responseSafepay",
			ino : ino
		},
		success: r => {
			result = r
		},
		error: e => {
			console.log('에러발생')
			console.log(e)
		}
	})
	
	if (result == 1) {
		alert('이미 해당 판매자에게 안전결제를 요청하였습니다');
		return;
	}
	if (result >= 2) {
		alert('이미 해당 판매자와 안전결제를 진행 중입니다')
		return
	}
	if (result == -1) {
		alert('안전결제 요청에 실패하였습니다 [ 관리자 문의 ]')
		return
	}
	if (result == 0) {
		alert('해당 판매자에게 안전결제를 요청하였습니다')
		location.href = `/Ezen_teamB/jsp/mymenu/mymenu.jsp#`;
	}
}


// 채팅버튼을 눌렀을때 실행되는 함수
function chatStart(){
	
	if(loginState == false){
		alert('로그인이후 가능한 기능입니다.');
		return;
	}
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async:false,
		data: { 
			type : "getChatRno", 
			mno : loginMno,
			ino : ino
		},
		success: r => {console.log('통신성공')
				console.log(r);
				rno = r;
		},
		error : e =>{console.log(e)}
	})	
	
	location.href = `/Ezen_teamB/jsp/chatting/chatting.jsp?ino=${ino}&rno=${rno}`;
	
}	// f end

// 찜목록 추가 함수
function insertFav(){
	
	if(loginState == false){
		alert('로그인 후 사용가능 합니다.');
		return;
	}
	
	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		method: "post",
		async:false,
		data: { 
			mno : loginMno,
			ino : ino
		},
		success: r => {console.log(r)
				if(r){getWish();}
				else{}
		},
		error : e =>{console.log(e)}
	})	
	
}

// 3. 찜하기 상태 호출
getWish();
function getWish(){
	let wish = document.querySelector('.wish');
	
	// 1. 비회원이면
	if(loginState == false){
		wish.innerHTML = '♡';
	}
	
	$.ajax({
		url : "/Ezen_teamB/MyMenuController",
		method : "get",
		data : {type: "5", ino : ino, mno : loginMno},
		success : result =>{console.log(result);
			if(result){wish.innerHTML = '♥';}
			else{wish.innerHTML = '♡';}
		}
	});
}



















			
