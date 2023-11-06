<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/Ezen_teamB/css/manager/tradelog.css" rel = "stylesheet">
<!-- 전체 거래내역 페이지 -->
</head>
<body>

	<div class="dateInput">
		<div class="inputBox">
			<input class="priDate" type="date">
			<p>~</p>
			<input class="nextDate" type="date">
		</div>
	</div>
	<div class="memberListTopField">
		<div class="searchMember">	<!-- 검색구역 -->
			<select id="selectMemberFilter">
				<option value="mid"> 판매자아이디 </option>
				<option value="mname"> 판매자이름 </option>
				<option value="ititle"> 게시물제목 </option>
			</select>
			<input class="searchMemberKeyword" type="text">	<button class="onSearchMemberBtn" onclick="onSearchTradelog()" type="button"> 검색 </button>
		</div>
		<div class="totalMember">	<!-- 현재 조회된 회원수 출력 -->
			<div class="totalInnerBox"> 조회된 거래내역 : <span class="totalMemeberCount"> </span> </div>
		</div>
	</div>
	
	<div class="memberTableBox" style="overflow-y: scroll;">	<!-- 멤버 테이블 출력구역 -->
		<table class="memberListTable">
			

			
			
		</table>
	</div>

	
</body>
</html>