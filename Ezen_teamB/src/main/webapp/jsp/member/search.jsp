<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="/Ezen_teamB/css/signup/signup.css" rel="stylesheet">
	<link href="/Ezen_teamB/css/signup/search.css" rel="stylesheet">
	
	
<title>Insert title here</title>
</head>
<body>

		<%@include file = "../header.jsp" %>
		<div class="container">
			<div class="IdPwSearch">
				<div class="signupform">
					<h3 class="loginInfo">아이디 찾기</h3>
				<div>
					<span class="signfont">이름</span>
				</div>
		
				<input class="form-floating mb-4 px-3 " id="searchName" name="signId"
					maxlength="20" oninput="maxlength(this)" type="text"
					placeholder="이름"><br/>
		
				<div><span class="signfont">전화번호</span></div>
				<input class="form-floating mb-3 px-3" id="searcPhone" name="signPhone" maxlength="11" oninput="maxlength(this)"  type="number" placeholder="'-' 제외 숫자만 입력 해주세요." ><br/>
					<!-- 미구현 -->
<!-- 					<span class="signfont">인증번호 입력</span>	
				<div class="idCertified">인증번호 입력
					<input class="form-floating mb-3 px-3" id="findId" name="signPhone" maxlength="6" oninput="maxlength(this)"  type="number" placeholder="인증번호" >			
				</div> -->
		
				<div>
					<button class="loginbtn btn btn-outline-dark" type="button"
						onclick="idSearch()">확인</button>
				</div>
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
				</div>	
				
				<!---------------------- 비밀번호 찾기 ------------------------->
							
				<div class="signupform">
					<h3 class="loginInfo">비밀번호 찾기</h3>
				<div>
					<span class="signfont">아이디</span>
				</div>
		
				<input class="form-floating mb-4 px-3 " id="searchId" name="signId"
					maxlength="20" oninput="maxlength(this)" type="text"
					placeholder="아이디"><br />
		
				<div>
					<span class="signfont">이메일</span>
				</div>
				<input class="form-floating mb-3 px-3" onkeyup="emailCheck()" id="searchEmail" name="signPwd"
					maxlength="20" oninput="maxlength(this)" type="email"
					placeholder="이메일">
				<button onclick="authReq()" class="btn btn-outline-dark mb-1 authBtn" disabled type="button">인증</button>
							
				<!-- 이메일 인증 통과 -->
				<div class="emailchechbox"></div>			
				
				<!-- 이메일 인증 확인 구역 -->
				<div class="authbox"></div>
				
				<div>
					<button class="loginbtn btn btn-outline-dark" type="button"
						onclick="pwdSearch()">확인</button>
				</div>
			
				</div><!-- signupform -->
			</div><!-- IdPwSearch -->
		
		</div><!-- container -->









	<!-- 부트 스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

	<!-- jquery 호출 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<!-- js -->
	<script src="/Ezen_teamB/js/search/search.js" type="text/javascript"></script>
</body>
</html>