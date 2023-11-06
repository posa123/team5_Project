<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="/Ezen_teamB/css/signup/signup.css" rel="stylesheet">


</head>
<body >
	<%@include file = "../header.jsp" %>	
		<div class="container"><!-- container -->
			<form class="signupform">
			<h3 class="signInfo">회원가입 페이지</h3>
				
			<div> <!-- 회원가입 구역 -->
			
			
				<div><span class="signfont">이름</span></div>
				<input class="form-floating mb-3 px-3" id="signName" name="signName" maxlength="30" oninput="maxlength(this)" type="text" placeholder="이름" ><br/>
				
				<div><span class="signfont">주민번호</span></div>
				<input class="form-floating mb-3 px-3" id="signResidentNumF" name="signResidentNumF" type="number" maxlength="6" oninput="maxlength(this)" placeholder="생년월일"  > - 
				<input class="form-floating mb-3 px-3" id="signResidentNumS" name="signResidentNumS" class="signResidentNumP" maxlength="7" oninput="maxlength(this)" type="number" placeholder="뒤 7자리" ><br/>
				
				<div><span class="signfont">전화번호</span></div>
				<div ><span style="color:gray; font-size:12px;">'-' 제외 숫자만 입력 해주세요.</span></div>
				<input class="form-floating mb-3 px-3" id="signPhone" name="signPhone" maxlength="11" oninput="maxlength(this)"  type="number" placeholder="전화번호" >

				<div><span class="signfont">이메일</span></div>
				<input class="form-floating mb-1 px-3" onkeyup="emailCheck()" id="signEmail" name="signEmail" maxlength="30" oninput="maxlength(this)"  type="text" placeholder="이메일" >
				<button onclick="authReq()" class="btn btn-outline-dark mb-1 authBtn" disabled type="button">인증</button>
				
				<!-- 이메일 인증 통과 -->
				<div class="emailchechbox"></div>			
				
				<!-- 이메일 인증 확인 구역 -->
				<div class="authbox"></div>
				
		
				
				

				<div class="emailCheck px-2"></div><br/>		<!-- 이메일 양식 맞춰서 -->
								
				<div><span class="signfont">주소</span></div>
						<!-- 우편번호 찾기 -->
				<div class="addressDisplay">	
					<div class="form-group">
						<input class="form-control" style="width: 70%; display: inline;"
							placeholder="우편번호" name="addr1" id="addr1" type="text"
							readonly="readonly">
					</div>
					<div>
						<button type="button" class="btn btn-default" onclick="execPostCode();" style="border:1px solid #DBDBDB; font-weight: bold;">
							우편번호 찾기
						</button>
					</div>
				</div>
				<!-- 도로명 주소 -->
				<div class="form-group">
					<input class="form-control" style="top: 5px; margin : 3px 0px;" placeholder="도로명 주소"
						name="addr2" id="addr2" type="text" readonly="readonly" />
				</div>
				
				<div class="form-group">
					<input class="form-control detail" placeholder="상세주소" name="addr3" id="addr3"
						type="text" />
				</div><br/>	
					
				
				
				<div><span class="signfont">아이디</span></div> <!-- onkeyup="idcheck()" -->
				<input class="form-floating mb-1 px-3" onkeyup="idcheck()" id="signId" name="signId" maxlength="20" oninput="maxlength(this)"  type="text" placeholder="아이디"><br/>
				<div class="idCheck px-2"></div><br/>		<!-- 중복검사, 영문+숫자 조합 최소 5글자 이상 -->


				<div><span class="signfont">비밀번호</span></div>
				<div ><span style="color:gray; font-size:12px;">영문 + 숫자 조합 8자 이상 입력해주세요</span></div>
				<input class="form-floating mb-1 px-3" onkeyup="signPwdTest()"  id="signPwd" name="signPwd" maxlength="20" oninput="maxlength(this)"  type="password" placeholder="비밀번호">
				<div class="signPwdCheck1 px-2"></div><br/>
								
				<div><span class="signfont">비밀번호 확인</span></div>
				<input class="form-floating mb-1 px-3"  onkeyup="signPwdTest()" id="signPwdCheck" name="signPwdCheck" maxlength="20" oninput="maxlength(this)"  type="password" placeholder="비밀번호 확인">
				<div class="signPwdCheck2 px-2"></div><br/>

				<div>
					<button class="signbtn btn btn-outline-dark" type="button" onclick="signup()">회원가입</button>
				</div>

				
				<!-- 휴대폰 인증 잠시 보류		
				<div>휴대폰 인증</div>	네이버 클라우드 무료 50건 문자 인증 
				
				<div><button><a href="#">회원 가입</a></button></div>
				 -->
					
			</div><!-- 회원가입 구역 end -->
			</form><!-- signupform -->
		</div> <!-- container end -->



 





	<!-- 부트 스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

	<!-- 주소검색 api -->	
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


	<!-- jquery 호출 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

	<!-- js -->
	<script src="/Ezen_teamB/js/signup/signup.js" type="text/javascript"></script>
</body>
</html>