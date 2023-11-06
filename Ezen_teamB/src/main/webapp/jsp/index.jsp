<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
			
	<link href="/Ezen_teamB/css/index.css" rel="stylesheet">
		
	<!-- 부트스트랩 css -->
	<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
	
<!-- 페이지 시작 시 메인페이지 -->
</head>
<body>
	<%@include file = "header.jsp" %>
	
	<!-- index 전체구역 -->
	<div class="indexWrap mapfull">
		
		<div class="mainField"> <!-- 본문 지도출력 구역 -->
			
			<div id="map" ></div>
		
		</div>
			
		<div class="topField">	<!-- 상단 제품추천 구역 -->
			<div>	<!-- 카테고리 출력구역 -->	
				<div class="topCategory">	<!-- 대분류 카테고리 설정구역 -->
				
				<div class="categorybox">
					<span></span> <span class="selCategory1">  </span> <span class="selCategory2">  </span>
				</div>
				<div class="guideLabel"><button class="searchAllBtn" onclick="searchAll()" type="button"> 전체보기 </button> </div>
				
				
				
				<table class="outputUmaincategory">
					<!-- 대분류 카테고리 출력구역 -->
				</table>
				
			</div>

			<div class="bottomCategory">	
				<!-- 소분류 카테고리 출력구역 -->
			</div>
			
			
			</div>
		</div>
	
	</div>
	
	<!-- 카카오지도 api -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c51f4ebd6d93bf1f15d0f4ba2809fea5&libraries=services,clusterer"></script>
	
	<!-- 부트스트랩에서 만든 JS 적용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	
	<script src="/Ezen_teamB/js/index.js" type="text/javascript"></script>
	
</body>
</html>	