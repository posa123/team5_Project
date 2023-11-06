<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	
	<link href="/Ezen_teamB/css/manager/memberList.css" rel="stylesheet">

</head>
<body>	

	<div class="memberListTopField">
		<div class="searchMember">	<!-- 검색구역 -->
			<select id="selectMemberFilter">
				<option value="mid"> 아이디 </option>
				<option value="mname"> 이름 </option>
				<option value="msno1"> 생년월일 </option>
				<option value="memail"> 이메일 </option>
				<option value="mphone"> 전화번호 </option>
			</select>
			<input class="searchMemberKeyword" type="text">	<button class="onSearchMemberBtn" onclick="onSearchMember()" type="button"> 검색 </button>
		</div>
		<div class="totalMember">	<!-- 현재 조회된 회원수 출력 -->
			<div class="totalInnerBox"> 조회된 회원 : <span class="totalMemeberCount"> </span>명 </div>
		</div>
	</div>
	
	<div class="memberTableBox">	<!-- 멤버 테이블 출력구역 -->
		<table class="memberListTable">
			<tr>
				<th> 성명 </th>
				<th> 생년월일 </th>
				<th> 전화번호 </th>
				<th> 이메일 </th>
				<th> 아이디 </th>
				<th> 포인트 </th>
			</tr>

			
			
		</table>
	</div>
	
	<div class="pagingBtnField">
		
	</div>

	

	<script src="/Ezen_teamB/js/manager/memberList.js" type="text/javascript"></script>

</body>
</html>