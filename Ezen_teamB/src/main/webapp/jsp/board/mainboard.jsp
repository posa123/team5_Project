<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 전체 게시판 jsp -->
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<link href="/Ezen_teamB/css/board/mainboard.css" rel="stylesheet">
	
</head>
<body>
	<%@include file ="../header.jsp" %>
	
	<div class="webcontainer"> <!-- 게시물 전체 구역 -->
	
	
			<div class="boardTop"> <!-- 상단 메뉴 -->
			<h3> 가지가지마켓 게시판 </h3>
			</div> <!-- 상단 메뉴 end -->
			
			<div class="boardmid">
				<div class="boardSearch">
					<select class="listsize" onchange="onListSize()">
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>	
					</select>
					<span class="boardcount"> </span>
				</div>
				<div class="mainBoardBtn">
					<button onclick="onWriteBtn()" type="button">글쓰기</button>
					<button onclick="onCategory(0)" type="button">전체보기</button>
					<button onclick="onCategory(1)" type="button">공지사항</button>
					<button onclick="onCategory(2)" type="button">건의사항</button>
				</div>
			</div>
		
		
		
		<div class="boardcontent"> <!-- 게시물이 출력 -->
			<!-- <table class="boardTable">
				
				게시물이 출력되는 구역 
				
			</table> -->
			<table class="table table-striped table-hover boardTable">
 
			</table>

		</div> <!-- 게시물 출력 end -->
		
		
		<div class="boardBottom"> <!--하단 메뉴 -->
			<!-- 페이징 처리 구역 -->
			<div class="pagebox">
			
			</div>
			<!-- 페이징 처리 구역 end-->
			
		</div> <!--하단 메뉴 end -->
		<!-- 검색구역 -->
			<div class="search">
				<select class="key">
					<option value="b.btitle">제목</option>
					<option value="m.mid">작성자</option>
				</select>
				<input class="keyword" type="text" placeholder="검색할내용"> <!-- 검색내용 -->
				<button onclick="boardSearch()" type="button" class="searchBtn">검색</button>
			</div>
			<!-- 검색구역 END -->
		
	</div> <!-- 게시물 전체구역 end -->
	<%@include file = "../footer.jsp" %>
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	<script  src="/Ezen_teamB/js/board/mainboard.js" type="text/javascript"></script>
</body>
</html>