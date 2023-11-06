console.log('마이메뉴 JS');

if (loginState == false) {
	alert('로그인이 필요한 서비스 입니다.')
	location.href = "/Ezen_teamB/jsp/member/login.jsp";
}

/* 네비게이션 이벤트 함수 */
document.querySelector('.smenu1').addEventListener("click", (e) => {
	console.log('1번클릭');

	document.getElementById('smenu1').classList.add('active');
	document.getElementById('smenu2').classList.remove('active');
	document.getElementById('smenu3').classList.remove('active');
	document.getElementById('smenu4').classList.remove('active');
	document.getElementById('smenu5').classList.remove('active');
})

document.querySelector('.smenu2').addEventListener("click", (e) => {
	console.log('2번클릭');

	document.getElementById('smenu1').classList.remove('active');
	document.getElementById('smenu2').classList.add('active');
	document.getElementById('smenu3').classList.remove('active');
	document.getElementById('smenu4').classList.remove('active');
	document.getElementById('smenu5').classList.remove('active');
})

document.querySelector('.smenu3').addEventListener("click", (e) => {
	console.log('3번클릭');

	document.getElementById('smenu1').classList.remove('active');
	document.getElementById('smenu2').classList.remove('active');
	document.getElementById('smenu3').classList.add('active');
	document.getElementById('smenu4').classList.remove('active');
	document.getElementById('smenu5').classList.remove('active');
})

document.querySelector('.smenu4').addEventListener("click", (e) => {
	console.log('4번클릭');

	document.getElementById('smenu1').classList.remove('active');
	document.getElementById('smenu2').classList.remove('active');
	document.getElementById('smenu3').classList.remove('active');
	document.getElementById('smenu4').classList.add('active');
	document.getElementById('smenu5').classList.remove('active');
})

document.querySelector('.smenu5').addEventListener("click", (e) => {
	console.log('5번클릭');

	document.getElementById('smenu1').classList.remove('active');
	document.getElementById('smenu2').classList.remove('active');
	document.getElementById('smenu3').classList.remove('active');
	document.getElementById('smenu4').classList.remove('active');
	document.getElementById('smenu5').classList.add('active');
})






// 회원정보 출력 함수   ---> 기능 완성후 html 출력기능 함수로 합칠것

let mno = loginMno;

infoPrint();
function infoPrint() {
	console.log('회원정보 출력 함수')
	console.log(loginMno)
	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		data: { type: '2', mno: loginMno },         // 보내는 데이터
		method: "get",
		async: false,
		success: r => {
			console.log(r);
			let infoContent = document.querySelector('.infoContent');
			let html = ``;

			html +=
				`
					 <div id="customPopup" class="custom-popup">
				        <div class="custom-popup-content">
				            <span id="closeCustomPopup" class="close-custom-popup">×</span>
				            <div class="customPopInfo"> 포인트 결제 </div>
				            <div> 
								<div class="outputAmountField">
									<button onclick="onAmount(1000)" type="button"> 1000포인트 </button>
									<button onclick="onAmount(5000)" type="button"> 5000포인트 </button>
									<button onclick="onAmount(10000)" type="button"> 10000포인트 </button>
									<button onclick="onAmount(50000)" type="button"> 50000포인트 </button>
									<button onclick="onAmount(100000)" type="button"> 100000포인트 </button>
									<button onclick="onAmount(500000)" type="button"> 500000포인트 </button>
								</div>
								
								<div class="outputPayMethod">
									<button onclick="onPayMethod('card', '카드결제')" type="button"> 카드결제 </button>
									<button onclick="onPayMethod('trans', '실시간 계좌이체')" type="button"> 실시간 계좌이체 </button>
									<button onclick="onPayMethod('vbank', '가상계좌')" type="button"> 가상계좌 </button>
									<button onclick="onPayMethod('phone', '휴대폰 소액결제')" type="button"> 휴대폰 소액결제 </button>
										<!-- HTML에서 매개변수 전달시 문자타입일 경우 '' 문자처리 -->
								</div>
							</div>
							<div class="bottomOutputField">
								<div>
						            <div class="pointBox"> 충전할 포인트 : <span class="selectedPoint">  </span>원 </div>
						            <div class="methodBox"> 결제방식 : <span class="selectedMethod">  </span> </div>
					            </div>
					            <div> <button class="liquidateBtn" onclick="requestPay()"> 결제하기 </button> </div>
					        </div>    
				        </div>
				    </div>	
				
					<h3 class="infoTitletxt"> 마이페이지 </h3>
					<div class="contentHeader">${r.mid} <span>회원님</span></div>
					<div class="contentMain">
						<div class="titleTop">처음온손님</div>
						<div class="saleTop">거래활동 <span>${r.saleProduct}</span></div>
						<div class="pointTop">포인트 <span>${r.mpoint}</span></div>
						<div class="chargePoint"> <button id="openCustomPopup" class="openCustomPopup"> 충전 </button> </div>
					</div>
					<div class="infoDetail">
						<div class="name">이름 ${r.mname}</div>
						<div class="address"> 주소 ${r.madress}</div>
						<div class="email"> 이메일 ${r.memail}</div>
					</div>
					<button onclick="updateModal()" class="btn btn-outline-info btn-sm updateBtn"
					data-bs-toggle="modal" data-bs-target="#exampleModal">정보수정</button>
					<button onclick="deleteModal()" class="btn btn-outline-danger btn-sm deleteBtn"
					data-bs-toggle="modal" data-bs-target="#exampleModal2">회원탈퇴</button>
				`




			infoContent.innerHTML = html;

		},
		error: e => { console.log('통신실패') }
	});


}

// 아임포트 관리자 식별키
IMP.init('imp74472512') // 아임포트 콘솔에서 확인

// 포인트 충전
function requestPay(){
	let now = new Date();
	let time = now.getTime();
	let merchant_uid = time+"-p-"+loginMid;	// 가급적 회원번호 조합 권장 / 로그인세션에서 아이디 가져옴
	
	IMP.request_pay({
		pg: "html5_inicis.INIBillTst",		// 아임포트 콘솔에서 확인
		pay_method: pay_method,				// 결제방식
		amount: amount,                     // 숫자 타입
		vbank_due : 1						// 가상계좌 입금마감일시 : 미기입시 default값 30일
	}, function(rsp) { // callback
		//rsp.imp_uid 값으로 결제 단건조회 API를 호출하여 결제결과를 판단합니다.

		if (rsp.success) {	// 결제 성공

		} else {	// 결제실패 : 사업자번호가 없기 때문에 실패가 성공이라는 가정으로 진행
			
			$.ajax({
				url: "/Ezen_teamB/pointPayment",
				method: "post",
				async: false,
				data: { amount : amount },
				success: s =>{
					alert('포인트가 지급되었습니다')
					console.log(s)
				},
				error: e =>{
					console.log('에러 발생')
				}
			})
		}
		popup.style.display = "none";
		location.href = `/Ezen_teamB/jsp/mymenu/mymenu.jsp`;
		
	});
}

// 팝업 열기 버튼
const openButton = document.getElementById("openCustomPopup");

// 팝업 창
const popup = document.getElementById("customPopup");

// 팝업 닫기 버튼
const closeButton = document.getElementById("closeCustomPopup");

// 팝업 열기 이벤트 처리
openButton.addEventListener("click", () => {
    popup.style.display = "block"; // 팝업 창 보이기
});

// 팝업 닫기 이벤트 처리
closeButton.addEventListener("click", () => {
    popup.style.display = "none"; // 팝업 창 숨기기
});




// 3. 결제 방식 선택 함수
let pay_method = ''		// 결제방식
function onPayMethod( method, methodName ){
	
	document.querySelector('.selectedMethod').innerHTML = `${methodName}`
	pay_method = method;
	
}

// 4. 결제 금액 선택 함수
let amount = 0;
function onAmount( value ){
	
	document.querySelector('.selectedPoint').innerHTML = `${value}`;
	amount = value;
	
} 


// 마이메뉴 페이지 출력시 함수 실행
saleList();
// 판매중인상품 리스트 출력 함수
function saleList() {
	
	let cardInfo = document.querySelector('.cardInfo');
	cardInfo.innerHTML = ``;
	console.log('판매중인상품 리스트')

	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		data: { type: '1', mno: loginMno, estate: 1 },         // 보내는 데이터
		method: "get",
		async: false,
		success: jsonArray => {

			cardInfo = document.querySelector('.cardInfo');
			let html = ``;

			// 판매중인 상품 숫자 출력
			let saleProduct = jsonArray.length
			let productCount = document.querySelector('.productCount');
			html = `<h4>상품 <span class="countText">${saleProduct}</span></h4>`;
			productCount.innerHTML = html

			html =
				`
	       			<table class="table table-hover align-middle text-center">
						  <tbody class="tableBody">
	       		`;

			if (jsonArray.length < 1) {
				tableBody.innerHTML = `판매중인 상품이 없습니다.`;
				return;
			}

			// 회원번호에 따른 판매중인 상품 출력
			jsonArray.forEach((p, i) => {
				let itrade;
				console.log(p)
				if (p.itrade == 1) {
					itrade = 1;
					p.itrade = '배송'
				} else if (p.itrade == 2) {
					itrade = 2
					p.itrade = '직거래'
				} else if (p.itrade == 3) {
					itrade = 3
					p.itrade = '중개소거래'
				}
				
				let outputBtn = `<td width="108px;"></td>`
				// 안전결제거래로 등록한 물품은 따로 안전결제가 진행 중일 경우 완료 처리가 불가함
					// 즉, vstate(1~4단계 중 )가 1을 초과할 경우 불가
				if( p.isafepayment == 0 ){
					outputBtn = `<td><button onclick="completeItem(${p.ino})" type="button" class="btn btn-primary">판매완료</button></td>`
				}
				
				
				p.idate = (p.idate).substr(0, 10);
				html +=
					`
							   		<tr class="tableContent">
								      <th scope="row">${i + 1}</th>

								      <td width="10%"><img src="/Ezen_teamB/jsp/item/img/${Object.values(p.imgList)[0] == null ? 'defaultImg.png' : Object.values(p.imgList)[0]}"></td>
								      <td class="goItemBtn" onclick="goItem(${p.ino}, ${itrade})" width="30%">${p.ititle}</td>
								      <td width="10%">${p.itrade}</td>
								      <td width="16%">${p.uname}/${p.dname}</td>
								      <td>${p.idate}</td>
								      ${outputBtn}
								      <td><button onclick="updateItem(${p.ino}, ${itrade})" type="button" class="btn btn-success">수정</button></td>
								      <td><button onclick="deleteItem(${p.ino})" type="button" class="btn btn-danger">삭제</button></td>
								    </tr>
					   `

			})

			html +=
				`
				   			</tbody>
						</table>
				   `
			cardInfo.innerHTML = html;
			
		},
		error: e => { console.log(e) }
	});

}		// function end

// 판매물품 판매완료 버튼 함수
	// 안전결제거래로 등록한 물품은 따로 안전결제가 진행 중일 경우 완료 처리가 불가함
	// 즉, vstate(1~4단계 중 )가 1을 초과할 경우 불가
function completeItem( ino ){
	
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: 'put',
		async: false,
		data: {
			type : 'completeItem',
			ino : ino
		},
		success: s =>{
			
			if(s){
				alert('물품 판매가 완료되었습니다')
			}
			
		},
		error: e =>{
			console.log('에러발생')
		}
	})
	location.href = `/Ezen_teamB/jsp/mymenu/mymenu.jsp`
	
}

// 판매물품 수정 버튼 함수
function updateItem(ino, itrade) {

	location.href = `/Ezen_teamB/jsp/item/updateitems.jsp?ino=${ino}&itrade=${itrade}`;

}


// 판매물품 삭제 버튼 함수
function deleteItem( ino ){
	
	let promptObj = confirm('해당 물품을 삭제하시겠습니까?')
	
	if( !promptObj ) return;
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method : 'delete',
		async: false,
		data : {
			type : 'deleteItem',
			ino : ino
		},
		success: s =>{
			console.log(s)
			if(s){
				alert('해당 물품이 삭제되었습니다')
				location.href = `/Ezen_teamB/jsp/mymenu/mymenu.jsp`
				
			}
		},
		error: e =>{
			console.log('에러발생')
		}
	})
	
}

// 물품상세보기페이지 이동함수
function goItem(ino, itrade){
	
	location.href = `/Ezen_teamB/jsp/item/detaileditems.jsp?ino=${ino}&itrade=${itrade}`;
	
}




// 거래내역 출력함수
function transHistory() {
	console.log('거래내역 리스트')

	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		data: { type: '1', mno: loginMno, estate: 2 },         // 보내는 데이터
		method: "get",
		async: false,
		success: jsonArray => {
			console.log(jsonArray);

			let cardInfo = document.querySelector('.cardInfo');
			let html =
				`
	       			<table class="table table-hover align-middle text-center">
						  <tbody class="tableBody">
	       		`;


			// 회원번호에 따른 거래내역 출력
			jsonArray.forEach((p, i) => {
				console.log(Object.values(p.imgList)[0]);
				if (p.itrade == 1) {
					p.itrade = '배송'
				} else if (p.itrade == 2) {
					p.itrade = '직거래'
				} else if (p.itrade == 3) {
					p.itrade = '중개소거래'
				}
				p.idate = (p.idate).substr(0, 10);
				console.log(Object.values(p.imgList)[0])
				html +=
					`
					   		<tr class="tableContent">
						      <th scope="row">${i + 1}</th>
						      <td width="5%"><img src="/Ezen_teamB/jsp/item/img/${Object.values(p.imgList)[0] == null ? 'defaultImg.png' : Object.values(p.imgList)[0] }"></td>
						      <td>${p.ititle}</td>
						      <td>${p.itrade}</td>
						      <td>${p.uname}/${p.dname}</td>
						      <td>${p.idate}</td>
						      <td><div class="saleComplete">거래완료<div></td>
						    </tr>
					   `

			})

			html +=
				`
				   			</tbody>
						</table>
				   `

			cardInfo.innerHTML = html;

			let saleProduct = jsonArray.length
			let productCount = document.querySelector('.productCount');
			html = `<h4>상품 <span class="countText">${saleProduct}</span></h4>`;
			productCount.innerHTML = html
		},
		error: e => { }

	});
}

/* ==================== 안전결제 관리 */


/* 안전결제내역 조회 조건 객체 */
let safepayPageObject = {
	type: 'getBuyerManage',
	maxSize: 11,		// * maxSize 	: 하나의 페이지에 최대표시할 안전결제건수
	page: 1,			// * page		: 현재 출력되는 페이지의 하단 번호
	vstateFilter: 0
};


// 안전결제 관리 함수
// 1-1 안전결제 관리 페이지 
function safePayManagement() {
	
	let cardInfo = document.querySelector('.cardInfo');
	let productCount = document.querySelector('.productCount');
	let html = ``;
	productCount.innerHTML = html;
	
	html = ` 
			<!-- 팝업 컨테이너 -->
			<div class="popup-container" id="myPopup">
			    <div class="popup-content">
			        
			        <div class="detailedLog">
			        </div>
			        
			        <button id="closePopup">닫기</button>
			    </div>
			</div>
	
			<div class="safepayStateBox">

				<div class="safepayBtnBox">
					<button onclick="buyManagement(1)" class="buyLogBtn" type="button">구매관리</button>
					<button onclick="sellManagement(1)" class="sellLogBtn" type="button">판매관리</button>
				</div>

				<div class="safepaySearchField">
					<div class="selectDateBox">
						<select id="selectFilterBox2" class="selectFilterBox">
							<option class="stateInfo" value="0">진행상태</option>
							<option value="1">요청</option>
							<option value="2">수락</option>
							<option value="3">전달</option>
							<option value="4">완료</option>
						</select>
					</div>
				</div>
				
				<table class="safepayStateTable">
				</table>
				
				<div class="pagingBtnField">
		
				</div>
				
		`
	cardInfo.innerHTML = html
	
	// select 요소를 가져옵니다.
	var selectFilterBox2 = document.getElementById("selectFilterBox2");
	
	// select 필터가 변경될 때마다 실행
	selectFilterBox2.addEventListener("change", onFilterChange);
	
	// "닫기" 버튼 클릭 시 팝업 닫기
	document.getElementById("closePopup").addEventListener("click", closePopup);
	
	// 기본 구매관리 버튼 클릭
	buyManagement(1)
}



// 1-2 안전결제 구매관리
function buyManagement(page) {

	safepayPageObject.type = 'getBuyerManage'
	safepayPageObject.page = page
	
	document.getElementsByClassName("buyLogBtn")[0].style.backgroundColor = "#9767EB";
	document.getElementsByClassName("sellLogBtn")[0].style.backgroundColor = "#A2A7E8";

	let safepayStateTable = document.querySelector('.safepayStateTable')
	safepayStateTable.innerHTML = ``;
	
	let html = `
			<tr>
				<th>진행상태</th>
				<th>물품제목</th>
				<th>판매자ID</th>
				<th>일시</th>
				<th></th>
			</tr>
		`
	
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "get",
		async: false,
		data: safepayPageObject,
		success: s => {
			console.log('성공')
			console.log(s)
			console.log(s.paymentList)
			
			s.paymentList.forEach( p => {

				// 진행상태
				let outputTr = `<tr>`
				let outputState = ``
				let outputDate = ``
				let outputBtn = ``

				switch ( p.vstate ) {
					case 1: outputState = '요청'; 
							outputDate = p.vrespdate;
							outputBtn = `
								<button onclick="deleteSafepay(${p.vno}, ${p.ino}, ${p.vrequester}, ${p.vstate})">취소</button>
							`
							break;
					case 2: outputState = '수락';
							outputDate = p.vreqsdate;
							outputBtn = `
								<button onclick="deleteSafepay(${p.vno}, ${p.ino}, ${p.vrequester}, ${p.vstate})">취소</button>
							`
							break;
					case 3: outputState = '전달'; 
							outputDate = p.vgivedate;
							outputBtn = `
								<button onclick="checkItem(${p.vno}, ${p.ino}, ${[p.vrequester]})" style="background-color:#9767EB;">수령확정</button>
							`
							break;
					case 4: outputTr = `<tr style="color:#8F918E !important;">`;
							outputState = '완료'; 
							outputDate = `완료된 거래`
							break;
				}
				

				html += `
					${outputTr} 
						<td>${ outputState }</td>
						<td onclick="checkTradeLog( ${p.vno}, ${p.vstate}, '${p.vrespdate}', '${p.vreqsdate}', '${p.vgivedate}' )">${ p.ititle }</td>
						<td>${ p.traderId }</td>
						<td>${ outputDate.substring(0, 16) }</td>
						<td>
							${ outputBtn }
						</td>
					</tr>
				`
			});
			
			safepayStateTable.innerHTML += html;
			document.querySelector('.pagingBtnField').innerHTML = ``
			
			/* 페이징 버튼 */
			html = ``
			
			// 페이지 개수만큼 페이징번호 구성
				// 이전버튼
			html += `<button onclick="buyManagement(${page-1})" class="beforePageBtn" type="button"> < </button>`;
				// 페이지 숫자버튼
			for( let i=s.startBtn; i<=s.endBtn; i++ ){
				
					// 만일 현재페이지(page)와 i가 같으면 페이지와 일치하면 버튼태그에 class="selectpage"
				html += `<button class="${ page == i ? 'selectpage' : '' } centerPageBtn" onclick="buyManagement(${i})" type="button"> ${i} </button>`
			}
				// 다음버튼 
			html += `<button onclick="buyManagement(${page >= s.totalPageCount ? page : page+1})"  class="afterPageBtn" type="button"> > </button>`;
				
			document.querySelector('.pagingBtnField').innerHTML = html;
				
			if( s.paymentList.length == 0 ){
				document.querySelector('.pagingBtnField').innerHTML = ``
			}
			
			
			
			
			
			
		},
		error: e => {
			console.log('에러발생')
		}

	})

}


// 1-3 안전결제 판매관리
function sellManagement(page) {

	safepayPageObject.type = 'getSellerManage'
	safepayPageObject.page = page


	document.getElementsByClassName("buyLogBtn")[0].style.backgroundColor = "#A2A7E8";
	document.getElementsByClassName("sellLogBtn")[0].style.backgroundColor = "#9767EB";

	let safepayStateTable = document.querySelector('.safepayStateTable')
	safepayStateTable.innerHTML = ``;
	
	let html = `
			<tr>
				<th>진행상태</th>
				<th>물품제목</th>
				<th>구매자ID</th>
				<th>일시</th>
				<th></th>
			</tr>
		`
	
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "get",
		async: false,
		data: safepayPageObject,
		success: s => {
			console.log('성공')
			console.log(s)
			console.log(s.paymentList)
			
			s.paymentList.forEach( p => {

				// 진행상태
				let outputTr = `<tr>`
				let outputState = ``
				let outputDate = ``
				let outputBtn = ``

				switch ( p.vstate ) {
					case 1: outputState = '요청'; 
							outputDate = p.vrespdate;
							outputBtn = `
								<button onclick="deleteSafepay(${p.vno}, ${p.ino}, ${p.vrequester}, ${p.vstate})">거절</button>
								<button onclick="acceptSafepay(${p.vno}, ${p.ino}, ${p.vrequester}, ${p.vstate})">수락</button>
							`
							break;
					case 2: outputState = '수락';
							outputDate = p.vreqsdate;
							outputBtn = `
								<button onclick="deleteSafepay(${p.vno}, ${p.ino}, ${p.vrequester}, ${p.vstate})">거절</button>
								<button onclick="deliverySafepay(${p.vno})">전달</button>
							`
							break;
					case 3: outputState = '전달'; 
							outputDate = p.vgivedate;
							break;
					case 4: outputTr = `<tr style="color:#8F918E !important;">`;
							outputState = '완료'; 
							outputDate = `완료된 거래`
							break;
				}
				

				html += `
					${outputTr} 
						<td>${ outputState }</td>
						<td onclick="checkTradeLog( ${p.vno}, ${p.vstate}, '${p.vrespdate}', '${p.vreqsdate}', '${p.vgivedate}' )">${ p.ititle }</td>
						<td>${ p.traderId }</td>
						<td>${ outputDate.substring(0, 16) }</td>
						<td>
							${ outputBtn }
						</td>
					</tr>
				`
			});
			
			safepayStateTable.innerHTML += html;
			document.querySelector('.pagingBtnField').innerHTML = ``
			
			/* 페이징 버튼 */
			html = ``
			
			// 페이지 개수만큼 페이징번호 구성
				// 이전버튼
			html += `<button onclick="buyManagement(${page-1})" class="beforePageBtn" type="button"> < </button>`;
				// 페이지 숫자버튼
			for( let i=s.startBtn; i<=s.endBtn; i++ ){
				
					// 만일 현재페이지(page)와 i가 같으면 페이지와 일치하면 버튼태그에 class="selectpage"
				html += `<button class="${ page == i ? 'selectpage' : '' } centerPageBtn" onclick="buyManagement(${i})" type="button"> ${i} </button>`
			}
				// 다음버튼 
			html += `<button onclick="buyManagement(${page >= s.totalPageCount ? page : page+1})"  class="afterPageBtn" type="button"> > </button>`;
				
			document.querySelector('.pagingBtnField').innerHTML = html;
				
			if( s.paymentList.length == 0 ){
				document.querySelector('.pagingBtnField').innerHTML = ``
			}
			
			
		},
		error: e => {
			console.log('에러발생')
		}

	})

}

// Select 필터가 변경될 때마다 필터기능 즉시 실행
function onFilterChange() {

	var selectElement = document.getElementById("selectFilterBox2");
	safepayPageObject.vstateFilter = selectElement.value; 
	
	if( safepayPageObject.type == 'getBuyerManage' ){
		buyManagement(1)
	}
	if( safepayPageObject.type == 'getSellerManage' ){
		sellManagement(1)
	}
}


// [팝업] 해당 tr을 클릭할 시 이전 상세 기록이 출력
function checkTradeLog( vno, vstate, vrespdate, vreqsdate, vgivedate ){
	
	document.getElementById("myPopup").style.display = "block";
	
	/*
	
	<div class="popup-container" id="myPopup">
	    <div class="popup-content">
		        
	    <button id="closePopup">닫기</button>
	    </div>
	</div>
	
	*/

	
	document.querySelector('.detailedLog').innerHTML = `
		<h2 class="popupTitle"> 안전결제 상세현황 </h2>
		
		<table class="detailedTable">
			<tr>
				<td class="dateinfo">요청일시</td>
				<td class="dateValue">${vrespdate}</td>
			</tr>
			<tr>
				<td class="dateinfo">수락일시</td>
				<td class="dateValue">${vreqsdate=='null' ? '-' : vreqsdate }</td>
			</tr>
			<tr>
				<td class="dateinfo">전달일시</td>
				<td class="dateValue">${vgivedate=='null' ? '-' : vgivedate}</td>
			</tr>
		</table>
	` 
	
	
	
	
}
// 팝업 닫기 함수
function closePopup() {
    document.getElementById("myPopup").style.display = "none";
}


let deleteSafepayComment = `해당 안전결제 진행 건을 취소하시겠습니까?`;


// 안전결제 취소 (필드 삭제) 기능
function deleteSafepay( vno, ino, vrequester, vstate ){
	
	let promptObj = confirm(`${deleteSafepayComment}`)
	console.log(promptObj)
	
	if( !promptObj ) return;
	
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: 'delete',
		async: false,
		data: {
			vno : vno,
			ino : ino,
			vrequester : vrequester,
			vstate : vstate
		},
		success: s => {
			
			if(s)	alert('안전결제를 취소하였습니다')
			
		},
		error: e =>{
			console.log('에러발생')
		}
		
	})
	
	if( safepayPageObject.type == 'getBuyerManage' ){
		buyManagement(1)
	}
	if( safepayPageObject.type == 'getSellerManage' ){
		sellManagement(1)
	}
	
}

// 판매자 요청 수락 기능
function acceptSafepay( vno, ino, vrequester, vstate ){
	
	let promptObj = confirm(`안전결제를 수락할 시 동일한 물품에 대한 안전요청 건은 자동삭제됩니다 수락하시겠습니까?`)
	
	if( !promptObj ){
		return
	}
	
	let checkBuyerPoint = false
	// 포인트 조회
	$.ajax({
		url: "/Ezen_teamB/pointPayment",
		method: "get",
		async: false,
		data: { ino : ino, vrequester : vrequester },
		success: s =>{
			checkBuyerPoint = s;
		},
		error: e =>{
			console.log('에러발생')
		}
		
	})
	
	if (!checkBuyerPoint) {
		deleteSafepayComment = `구매자의 포인트가 부족합니다 해당 건을 취소하시겠습니까?`;
		deleteSafepay(vno, ino, vrequester, vstate)
		return;
	}
	
	
	// 포인트 차감
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "put",
		async: false,
		data: { 
			type : 'acceptSafepay', 
			vno : vno, 
			ino : ino,
			vrequester : vrequester			
		},
		success: s =>{
			
		},
		error: e =>{
			console.log('에러발생')
		}
		
	})
	
	if( safepayPageObject.type == 'getBuyerManage' ){
		buyManagement(1)
	}
	if( safepayPageObject.type == 'getSellerManage' ){
		sellManagement(1)
	}
	
}

// 판매자 물품 전달 기능
function deliverySafepay( vno ){
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "put",
		async: false,
		data: { type : 'deliverySafepay' , vno : vno },
		success: s =>{
			
		},
		error: e =>{
			console.log('에러발생')
		}
		
	})
	
	if( safepayPageObject.type == 'getBuyerManage' ){
		buyManagement(1)
	}
	if( safepayPageObject.type == 'getSellerManage' ){
		sellManagement(1)
	}
}				

// 구매자 수령확정 기능
function checkItem(vno, ino, vrequester){
	$.ajax({
		url: "/Ezen_teamB/SafePaymentController",
		method: "put",
		async: false,
		data: { 
			type : 'checkItem',
			vno : vno,
			ino : ino,
			vrequester : vrequester
		},
		success: s =>{
			
		},
		error: e =>{
			console.log('에러발생')
		}
		
	})
	
	if( safepayPageObject.type == 'getBuyerManage' ){
		buyManagement(1)
	}
	if( safepayPageObject.type == 'getSellerManage' ){
		sellManagement(1)
	}
}



/* ==================== 안전결제 관리 end */

// 찜목록 출력함수
function PrintWishList() {
	console.log('찜목록 리스트')

	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		async: false,
		data: { type: '3', mno: loginMno },
		method: "get",
		success: jsonArray => {
			console.log(jsonArray);

			let cardInfo = document.querySelector('.cardInfo');
			let html =
				`
	       			<table class="table table-hover align-middle text-center">
						  <tbody class="tableBody">
	       		`;


			// 회원번호에 따른 거래내역 출력
			jsonArray.forEach((p, i) => {
				let itrade;
				console.log(Object.values(p.imgList)[0]);
				if (p.itrade == 1) {
					itrade = 1;
					p.itrade = '배송'
				} else if (p.itrade == 2) {
					itrade = 2;
					p.itrade = '직거래'
				} else if (p.itrade == 3) {
					itrade = 3;
					p.itrade = '중개소거래'
				}
				p.idate = (p.idate).substr(0, 10);
				
					
				html +=	
					`
					   		<tr class="tableContent">
						      <th scope="row">${i + 1}</th>
						      <td width="5%"><img src="/Ezen_teamB/jsp/item/img/${Object.values(p.imgList)[0] == null ? 'defaultImg.png' : Object.values(p.imgList)[0]}"></td>
						      <td class="goItemBtn" onclick="goItem(${p.ino}, ${itrade})">${p.ititle}</td>
						      <td>${p.itrade}</td>
						      <td>${p.uname}/${p.dname}</td>
						      <td><div class="saleContinue">판매중<div></td>
						      <td><button onclick="deleteWishList(${p.ino})" class="btn btn-danger wdbtn" type="button">삭제</button></td>
						    </tr>
					   `

			})

			html +=
				`
				   			</tbody>
						</table>
				   `

			cardInfo.innerHTML = html;

			let saleProduct = jsonArray.length
			let productCount = document.querySelector('.productCount');
			html = `<h4>상품 <span class="countText">${saleProduct}</span></h4>`;
			productCount.innerHTML = html

		},
		error: e => { console.log('통신실패') }
	})

}	// f end


// 회원정보 수정 모달창 함수
function updateModal() {
	console.log('회원정보 변경 함수')

	$.ajax({
		url: "/Ezen_teamB/MemberController",
		data: { type: '1', mno: loginMno },         // 보내는 데이터
		method: "get",
		success: r => {
			console.log(r);

			let emailInput = document.querySelector('.emailInput');
			let pwd = document.querySelector('.pwd');
			let pwdCk = document.querySelector('.pwdCk')
			emailInput.value = `${r.memail}`
			pwd.value = `	${r.mpwd}`
			pwdCk.value = `${r.mpwd}`

		}
	});

}	// updateInfo function end

// 회원정보 수정 함수
function updateInfo() {

	let emailInput = document.querySelector('.emailInput').value;
	let adrInput2 = document.querySelector('.adrInput2').value;
	let adrInput3 = document.querySelector('.adrInput3').value;
	let pwd = document.querySelector('.pwd').value;

	$.ajax({
		url: "/Ezen_teamB/MemberController",
		data: {
			mno: loginMno, memail: emailInput,
			adress1: adrInput2, adress2: adrInput3, mpwd: pwd
		},
		method: "put",
		success: r => {
			console.log(r);
			if (r) {
				alert('회원정보가 수정되었습니다.')
				location.href = "/Ezen_teamB/jsp/member/login.jsp"
			} else { alert('수정 실패') }

		}
	});
}	// updateInfo f end


// 찜목록 삭제함수
function deleteWishList(ino) {
	console.log('찜목록 삭제함수')

	$.ajax({
		url: "/Ezen_teamB//MyMenuController",
		data: { ino: ino, mno: loginMno },         // 보내는 데이터
		method: "delete",
		success: r => {
			console.log(r);
			if (r) {
				console.log('찜목록 삭제')
				PrintWishList();
			} else { console.log('찜목록 삭제 실패') }

		},
		error: e => { console.log(e) }
	});

}


// 회원탈퇴 함수
function deleteInfo() {

	let deletepwd = document.querySelector('.deletepwd').value;
	console.log(deletepwd);

	$.ajax({
		url: "/Ezen_teamB//MemberController",
		data: { mno: loginMno, mpwd: deletepwd },         // 보내는 데이터
		method: "delete",
		success: r => {
			console.log('통신성공');
			if (r) {
				alert('회원탈퇴가 완료되었습니다.')
				location.href = "/Ezen_teamB/jsp/index.jsp"
			} else {
				alert('비밀번호가 틀렸습니다.')
			}

		},
		error: e => { console.log(e) }
	});

}

// 채팅목록 출력함수
function chattingList() {
	console.log('채팅목록 출력함수 실행')
	
	let cardInfo = document.querySelector('.cardInfo');
	let productCount = document.querySelector('.productCount');
	let html = ``;
	productCount.innerHTML = html;
	cardInfo.innerHTML = html;

	$.ajax({
		url: "/Ezen_teamB/MyMenuController",
		async: false,
		data: { type: '4', mno: loginMno },
		method: "get",
		success: jsonArray => {
			console.log(jsonArray)

			html =
				`
	       			<table class="table table-hover align-middle text-center">
						  <tbody class="tableBody">
	       		`;


			if (jsonArray.length < 1) {
				tableBody.innerHTML = `텅...`;
				return;
			}

			jsonArray.forEach((p, i) => {
				p.jchatdate = (p.jchatdate).substr(0, 10);
				p.jcontent = JSON.parse(p.jcontent)
				html +=

					`
					<tr class="tableContent">
				      <th scope="row">${i + 1}</th>
				      <td class="chatJoinBtn" onclick="goChat(${p.ino}, '${p.rno}')">${p.jcontent.content}</td>
				      <td>${p.jchatdate}</td>
				      <td><button onclick="deleteChat('${p.rno}')" type="button" class="btn btn-danger">나가기</button></td>
				    </tr>
				`

			})

			html +=
				`
				   			</tbody>
						</table>
				   `
		},
		error: e => { console.log(e) }
	})

	cardInfo.innerHTML = html;
}

// 채팅방 나가기 함수
function deleteChat(rno){
	
	let conf = confirm('채팅방을 나가시겠습니까?');
	if(conf){}
	else{return;}
	
	$.ajax({
		url: "/Ezen_teamB/ChattingController",
		async: false,
		data: { rno : rno },
		method: "delete",
		success: r => {console.log(r)
				chattingList();
		},
		error : e => {console.log(e)}
	})	
	
}

// 채팅방 이동함수
function goChat(ino, rno) {
	console.log('채팅방 이동함수')
	location.href = `/Ezen_teamB/jsp/chatting/chatting.jsp?ino=${ino}&rno=${rno}`

}




/* 주소 검색 api */				// 추후 header에서 통일 가능

//팝업 위치를 지정(화면의 가운데 정렬)
var width = 500; //팝업의 너비
var height = 600; //팝업의 높이

// 주소 입력 api
function execPostCode() {
	new daum.Postcode({
		width: width, //생성자에 크기 값을 명시적으로 지정해야 합니다.
		height: height,

		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 도로명 조합형 주소 변수

			// 법정동명이 있을 경우 추가한다. (법정리는 제외)
			// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			// 건물명이 있고, 공동주택일 경우 추가한다.
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
			}
			// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
			// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
			if (fullRoadAddr !== '') {
				fullRoadAddr += extraRoadAddr;
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			console.log(data.zonecode);
			console.log(fullRoadAddr);


			$("[name=addr1]").val(data.zonecode);
			$("[name=addr2]").val(fullRoadAddr);


			/* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
			document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
			document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
		}
	}).open({
		left: (window.screen.width / 2) - (width / 2),
		top: (window.screen.height / 2) - (height / 2)
	});
}


























