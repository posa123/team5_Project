console.log('회원가입 스크립트 실행')

// 인풋 박스 최대 글자수 이상 입력 불가
function maxlength(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
  }    
}

// 아이디 유효성 검사
function idcheck(){
	
	// 입력한 id 값 호출
	let signId = document.getElementById('signId').value;
	
	// 아이디 유효성 검사 출력 구역 
	let idcheck = document.querySelector('.idCheck');	
	
	// 아이디 유효성 검사 / 영문 소,숫자 필수 // 최소5~20글자 까지
	let idregular = /^(?=.*[a-z])(?=.*\d)[a-z\d]{5,20}$/
	
	if(idregular.test(signId)){		
		
		$.ajax({
			
			url : "/Ezen_teamB/MemberFindController",
			method : "get",
			async:false,
			data : {type : "mid" , data : signId},
			success : result => {
				if(result){idcheck.innerHTML = `<span style="color:red; font-size:12px;">사용중인 아이디 입니다</span>`
					 	checkList [0] = false; }
				else{idcheck.innerHTML = `<span style="color:blue; font-size:12px;">사용 가능한 아이이디 입니다</span>`
					 	checkList [0] = true; }
			},
			error : error=>{ console.log(error)}
		})
	}else{
		idcheck.innerHTML = `<span style="color:gray; font-size:12px;">영문(대,소문자) + 숫자 조합의 5~20 글자만 입력 가능 합니다</span>`
	} // else end 
}//idcheck end


// 비밀번호 유효성 검사

function signPwdTest(){
	let signPwd = document.getElementById('signPwd').value;
	let signPwdCheck = document.getElementById('signPwdCheck').value;

	//비밀번호 정규식 표현
	let pwregular = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,20}$/

	
	if(pwregular.test(signPwd)){//정규식 표현이 정상 이라면
		document.querySelector('.signPwdCheck1').innerHTML=`<span style="color:blue; font-size:12px;">사용 가능한 비밀번호</span>`;

	}else if(signPwd == ""){//비밀번호가 공란이라면 알림 메세지 지우기
		document.querySelector('.signPwdCheck1').innerHTML=``;
		document.querySelector('.signPwdCheck2').innerHTML=``;				
	}
	
	
	if(signPwdCheck == ""){//비밀번호 확인이 공란이라면 알림 메세지 지우기
		document.querySelector('.signPwdCheck2').innerHTML=``;

		
	}else if(signPwd != signPwdCheck){//비밀번호와 비밀번호 확인이 불일치시
		document.querySelector('.signPwdCheck2').innerHTML=`<span style="color:red; font-size:12px;">비밀번호 불일치</span>`;
		checkList [1] = false;
	}
	else if(signPwd == signPwdCheck && signPwdCheck != ""){// 비밀번호, 비밀번호 확인 일치, 공란 아니면
		document.querySelector('.signPwdCheck2').innerHTML=`<span style="color:blue; font-size:12px;">비밀번호 일치</span>`;
	 	checkList [1] = true; 
	}

}

// 이메일 유효성 검사

function emailCheck(){
	// 이메일 정보 호출
	let signEmail = document.getElementById('signEmail').value;
	//출력 구역
	let emailCheck = document.querySelector('.emailCheck')

	let emailregular = /^[a-zA-Z\d_-]+@[a-zA-Z\d_-]+\.[a-zA-Z]+$/
	
	if(signEmail==""){//이메일 공란이면 알림창 비우기
		emailCheck.innerHTML=``;
	}else if(emailregular.test(signEmail)){//정규표현식 정상일때

		emailCheck.innerHTML=`<span style="color:blue; font-size:12px;">이메일 사용가능</span>`
		document.querySelector('.authBtn').disabled=false;
	}else{//정규표현식 비정상일때
		emailCheck.innerHTML=`<span style="color:red; font-size:12px;">이메일 사용불가</span>`
		document.querySelector('.authBtn').disabled=true;
	}
	
	
	
}


let authcode = '';
let timer = 0;
let timerInter;


// 이메일 인증 발송
function authReq(){
	console.log('인증요청 발송 정상')
	
	let authbox = document.querySelector('.authbox');
	let html = `
			<input class="form-floating mb-1 px-3" id="ecode" type="text">
			<button class="btn btn-outline-dark mb-1 authBtn" type="button" onclick="auth()">확인</button><br/>
			<span class="timebox"> 남은시간 05:00</span>
	`
	authbox.innerHTML=html;
	// 인증 정상 메세지 초기화
	document.querySelector('.emailCheck').innerHTML=``;
	
	let email = document.getElementById('signEmail').value;
	
	
	$.ajax({
		
		url:"/Ezen_teamB/SendEmailController",
		method:"get",
		data : {email : email},
		success : r =>{console.log(r)
		authcode = r;},
		error : e=>{console.log(e)}		
	})
	
	

	timer = 300;
	setTimer();
	
	
	
}


// 타이머
function setTimer(){
	timerInter = setInterval(()=> {
		

			let m = parseInt(timer/60)
			let s = parseInt(timer%60);

			m = m < 10 ? "0" + m : m; // 만약 m이 10보다 작으면(1~9) 앞에 0 붙이고 아니면(10이상) 그대로
			s = s < 10 ? "0" + s : s;
		document.querySelector('.timebox').innerHTML = `남은시간 ${m}:${s}`; // 현재 인증 시간 html 대입
		timer--;
		
		//만약에 타이머가 0 이면 종료
		if(timer < 0) {
			// 1. setInterval 종료 [누구를 종료할건지 변수 선언 = timerInter]
			clearInterval(timerInter);
			// 2. 인증 실패
			document.querySelector('.emailchechbox').innerHTML=`<span style="color:red; font-size:12px;">인증실패</span>`;
			// 3. authbox 숨기기
			document.querySelector('.authbox').innerHTML=``;
		}		
	}, 1000)	
	
}


// 이메일 인증 완료
function auth(){
	
	let ecode = document.getElementById('ecode').value;
	
	if(authcode == ecode){
		// interval 종료 시키기
		clearInterval(timerInter);
		// 인증 성공		
		document.querySelector('.emailchechbox').innerHTML=`<span style="color:blue; font-size:12px;">인증 성공</span>`;		
		// 박스 초기화
		document.querySelector('.authbox').innerHTML=``;
		// 인증 버튼 비활성화
		document.querySelector('.authBtn').disabled=true;
	 	checkList [2] = true; 
	}
	else{// 인증 실패
		document.querySelector('.emailchechbox').innerHTML=`<span style="color:red; font-size:12px;">인증 실패</span>`
	 	checkList [2] = false; 		
	}
	
	
}



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
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
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

let checkList = [false, false, false]


function signup(){
	// 회원 가입 폼 호출
	if(checkList[0] && checkList[1] && checkList[2]){
		let signupform = document.querySelectorAll('.signupform')[0];
		// 폼 데이터화
		let signupData = new FormData(signupform);
		
	
		
		$.ajax({
			
			url : "/Ezen_teamB/MemberController",
			method : "post",
			data : signupData,
		    contentType : false,
		    processData : false,		
			success : result => { 
				console.log(result)
				if(result==true){
					alert('회원가입에 성공 하였습니다')
					location.href="/Ezen_teamB/jsp/member/login.jsp"}
				else{alert('회원가입 실패')}			
			},
			error : error =>{console.log(error)}
			
		})// ajax
	}else{alert('회원가입 불가')}
	
}// signup end






