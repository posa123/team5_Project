

// 인풋 박스 최대 글자수 이상 입력 불가
function maxlength(object){
    if (object.value.length > object.maxLength){
      object.value = object.value.slice(0, object.maxLength);
  }    
}


function login(){

let signId = document.getElementById('signId').value;
let signPwd = document.getElementById('signPwd').value;


	$.ajax({
		url: "/Ezen_teamB/MemberFindController",
		method: "post",   // get 메소드는 정송하는 data 노출=보안에 취약 // 
		data: { signId: signId, signPwd: signPwd },
		success: r => {
			if (r) {
				
				alert('로그인성공')
				location.href = "/Ezen_teamB/jsp/index.jsp";
			}
			else {
				alert('회원정보가 일치하지 않습니다.')

			}
		},
		error: e => { e }
	});

}



















