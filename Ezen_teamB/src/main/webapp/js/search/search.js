console.log('search js 정상')

// 인풋 박스 최대 글자수 이상 입력 불가
function maxlength(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
  }    
}



// 아이디 찾기 함수
function idSearch(){ console.log('아이디 찾기 함수 실행')
	
	let mname = document.getElementById('searchName').value;
	console.log('아이디찾기 가져온 아이디 ' + mname)
	let mphone = document.getElementById('searcPhone').value;
		console.log('아이디찾기 가져온 전화번호 ' + mphone)

	$.ajax({
		
		url:"/Ezen_teamB/SearchController",
		method : "get",
		data :{type : "id" , mname : mname , mphone : mphone},
		success : result => {console.log(result)
			if(result=='null'){
				alert('정보가 일치하지 않습니다')
			}else if(result !='null'){
				alert('고객님의 아이디는 '+result+' 입니다')
				document.getElementById('searchName').value = "";
				document.getElementById('searcPhone').value = "";

			}

			
		},
		error : e =>{console.log(e)}
	});

		
}




// 비밀번호 찾기 함수
function pwdSearch(){ console.log('비밀번호 찾기 함수 실행')
	let mid = document.getElementById('searchId').value;
	console.log('비밀번호찾기 가져온 아이디 ' + mid)	
	let memail = document.getElementById('searchEmail').value;
	console.log('비밀번호찾기 가져온 아이디 ' + memail)	


	$.ajax({
		
		url:"/Ezen_teamB/SearchController",
		method : "get",
		sasync:false,
		data :{type : "pwd" , mid : mid , memail : memail},
		success : result => {console.log(result)

			if(result=='null'){
				alert('정보가 일치하지 않습니다')
			}else if(result !='null'){
				
				alert('고객님의 임시 비밀번호는 '+result+' 입니다\n비밀번호를 변경 하세요' )
				location.href='/Ezen_teamB/jsp/member/login.jsp'
			}
			
			
		},
		error : e =>{console.log(e)}
	});
}	
	

let authcode = '';
let timer = 0;
let timerInter;




function emailCheck(){
	// 이메일 정보 호출
	let searchEmail = document.getElementById('searchEmail').value;

	let emailregular = /^[a-zA-Z\d_-]+@[a-zA-Z\d_-]+\.[a-zA-Z]+$/
	
	if(searchEmail==""){//이메일 공란이면 알림창 비우기
		document.querySelector('.authBtn').disabled=true;
	}else if(emailregular.test(searchEmail)){//정규표현식 정상일때

		document.querySelector('.authBtn').disabled=false;
	}else{//정규표현식 비정상일때
		document.querySelector('.authBtn').disabled=true;
	}
	
	
	
}










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
	
	let email = document.getElementById('searchEmail').value;
	
	
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
		// 박스 초기화
		document.querySelector('.authbox').innerHTML=``;
		// 인증 버튼 비활성화
		document.querySelector('.authBtn').disabled=true;

	}
	else{// 인증 실패
		alert('인증에 실패 하였습니다')
	}	
	
}
	
	

	
