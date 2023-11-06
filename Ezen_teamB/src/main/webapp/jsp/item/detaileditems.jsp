<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 물품상세조회 jsp -->


<link href="/Ezen_teamB/css/item/detaileditems.css" rel="stylesheet">

<!-- 부트스트랩 css -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

</head>
<body>
	<%@ include file="../header.jsp"%>


	<div class="detailedItemsWrap">
		<!-- detailedItems 전체구역 -->
		<div class="basicItemInfo">
			<div class="outputImg">
				<!-- 이미지 출력구역 -->
				<div id="carouselExampleIndicators" class="carousel slide">
					<div class="carousel-indicators slideBtn"></div>
					<div class="carousel-inner infoImgBox"></div>
					<button class="carousel-control-prev" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
	
		
			<!-- 캐러셀 우측 -->
			<div class="rightBasicInfo">
				<div class="basicInfoLeft">
					<!-- 좌측 id 구역 -->
					<span class="buyerInfo"> 판매자 : </span> <span class="outputBuyerId">
					</span>
				</div>
				<div class="basicInfoRight">
					<!-- 우측 등록일자 구역 -->
					<div class="uploadDate"></div>
				</div>
				<div class="tradeInfo">
					<!-- 거래방식 -->
					<div class="itemTrade">거래방식</div>
					<div class="itemTradeWord"></div>
				</div>
	
				<div class="priceInfo">
					<!-- 판매가격 -->
					<div class="itemPrice">
						판매가격 <span> </span>
					</div>
					<div class="itemPriceWord"></div>
				</div>
	
				<div class="safePaymentInfo">
					<!-- 안전결제 사용 여부 -->
					<div class="itemSafePayment">
						안전결제사용여부 <span> </span>
					</div>
					<div class="itemSafePaymentWord"></div>
				</div>
				<div class="caution">
					<!-- 주의사항 -->
					<div class="cautionComment">
						“가지가지” 상점의 거래상품들에 대하여<br> 통신판매중개자로서 거래 당사자가 아니며 판매 회원과 구매 회원
						간의 상품거래 정보 및 거래에 관여하지 않고,<br> 어떠한 의무와 책임도 부담하지 않습니다.
					</div>
				</div>
			</div>
		</div>


		<div class="outputTitle">
			<!-- 판매 제목 -->
			<div class="itemTitle"></div>
		</div>


		<div class="outputContent">	<!-- 판매 내용 -->
		판매 내용
		
		</div>

		<div class="mainInfoRight">
			<div class="outputPlace">
				<!-- 거래방식에 따른 위치정보 출력 구역 -->
				<div class="adressInfo">
					<!-- 거래방식에 따른 주소 출력 -->
				</div>
				<!-- 지도 출력 구역 -->
				<div class="mapBox">
					<div id="map" style="width: 100%; height: 400px;"></div>
				</div>
			</div>

		</div>


		<div class="buttonField">	<!-- 버튼 구역 -->
			<button onclick="insertFav()" class="watchItemBtn" type="button"> 찜 <span class="wish"></span></button>
			<button onclick="chatStart()" class="chatBtn" type="button"> 가지톡 </button>
		</div>



	</div>
	<!-- detailedItems 전체구역 end -->



	<!-- 카카오지도 api -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c51f4ebd6d93bf1f15d0f4ba2809fea5&libraries=services,clusterer"></script>

	<!-- 부트스트랩에서 만든 JS 적용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>



	<script src="/Ezen_teamB/js/item/detaileditems.js"
		type="text/javascript"></script>

</body>
</html>