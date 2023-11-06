<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="../../css/chatting/chatting.css" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
</head>
<body>

	<%@include file = "../header.jsp" %>
	
	<div class="webcontainer">
		<!-- 채팅전체구역 -->
		<div class="chatbox">
			<!-- 채팅내용 목록 -->
			<div class="chatcont">
				<!-- 채팅출력구역(js에서 출력) -->
			</div>
			<!-- 채팅입력창, 전송버튼 -->
			<div class="chatbottom">
				<button class="emobtn" type="button" data-bs-toggle="dropdown">
			    	<i class="far fa-smile"></i>
			  	</button>
			  	<ul class="dropdown-menu emolistbox"></ul>
				<textarea onkeydown="onEnterKey()" class="msg" name="msg"></textarea>
				<button onclick="onSend()" class="sendbtn" type="button">전송</button>
			</div>
			
		</div>
		
		
		
	</div>
	
	
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	<script type="text/javascript" src="../../js/chatting/chatting.js"></script>

</body>
</html>