


let loginState = false;

let loginMid = '';
let loginMno = 0;

getMemberInfo()

function getMemberInfo() {
	$.ajax({
		url: "/Ezen_teamB/MemberInfoController",
		method: "get",
		async: false,
		data: { type: "info" },
		success: r => {

			let submenu = document.querySelector('.submenu')
			let html = ``;
			if (r == null) {


				loginState = false; loginMid = '';
				html += `
						<li><a href="/Ezen_teamB/jsp/member/login.jsp">로그인</a></li>
						<li><a href="/Ezen_teamB/jsp/member/signup.jsp">회원가입</a></li>		  
					`
			}
			else if( r.mid == 'admin' ){
				
				loginState = true;
				loginMid = r.mid;
				
				html = `
						<li><a href="/Ezen_teamB/jsp/manager/managerPage.jsp">관리자페이지</a></li>
						<li> <a onclick="logout()" href="/Ezen_teamB/jsp/index.jsp">로그아웃</a> </li>
					`
			}
			else {

				loginState = true; loginMid = r.mid; loginMno = r.mno;

				html += `
						<li> ${loginMid}님 </li>
						<li> <a onclick="logout()" href="/Ezen_teamB/jsp/index.jsp">로그아웃</a> </li>			
					`
			}
			submenu.innerHTML = html;

		},
		error: e => { e }
	});
}
		



// 로그아웃
function logout() {

	$.ajax({
		url: "/Ezen_teamB/MemberInfoController",
		method: "get",
		data: { type: "logout" },
		success: r => {
			alert('로그인이 필요합니다')
			location.href = "/Ezen_teamB/jsp/index.jsp"
		},
		error: e => { e }
	});


}