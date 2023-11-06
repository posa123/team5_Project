<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>Insert title here</title>

	<link href="/Ezen_teamB/css/manager/emediation.css" rel = "stylesheet">
<!-- 거래중개업체 관리 페이지 -->
</head>	
<body>	

	 <!-- 대면거래 지도 출력 구역 -->
		<!-- 거래방식 - 대면거래일 시 지도출력하여 대면거래 위치 지정 -->
	<div id="outputMapField" style="height: 0px;"><!-- 지도 출력되는 구역 -->
		<div class="insertBox">
			<input type="text" id="sample5_address" placeholder="주소">
			<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
			<select class="eType">
				<option value="CU">CU</option>
				<option value="GS25">GS25</option>
				<option value="7-ELEVEN">7-ELEVEN</option>
				<option value="미니스톱">미니스톱</option>
			</select>
			<button onclick="insertEmediaion()" type="button"> 안전거래소등록</button>
		</div>
		
		
		<!-- 대면거래 이용 map -->
		<div id="map" style="width:100%;height:350px;"></div>
		<div id="clickLatlng"> </div>
	</div>
	
	<div class="eTableBox">
		<table class="eTable">
			<tr>
				<th> 거래소번호 </th>
				<th> 거래소타입 </th>
				<th> 거래소주소 </th>
				<th> 비고 </th>
			</tr>

			
			
		</table>
	</div>
	
	
	
	
	<!-- 카카오지도 api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c51f4ebd6d93bf1f15d0f4ba2809fea5&libraries=services,clusterer"></script>
	
	<!-- 카카오 우편번호 서비스 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<!-- jquery -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<script src="/Ezen_teamB/js/manager/emediation.js" type="text/javascript"></script>

</body>
</html>