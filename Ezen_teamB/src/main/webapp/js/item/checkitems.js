
// 검색 필터를 위한 대분류 소분류 번호
let filterNum = -1
// 대분류 소분류 식별자
let filterCategory = ''
// 검색 기능을 위한 검색어
let searchWord = ''


// 전체 물품 출력
getItemList()
function getItemList(  ){
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data: { type : "getItemList",
				filterCategory : filterCategory,	// 대분류 소분류 식별자
				filterNum : filterNum, 				// 검색 필터를 위한 대분류 소분류 번호
				searchWord : searchWord 			// 검색 기능을 위한 검색어
		},
		success: s => {
			
			console.log('성공')
			console.log(s)
			
			let html = ``;
			s.forEach( p => {
				
				html += `
					<div class="col">
						<a href="/Ezen_teamB/jsp/item/detaileditems.jsp?ino=${p.ino}&itrade=${p.itrade}">
							<div class="card">
								
								<img src="/Ezen_teamB/jsp/item/img/${ Object.values(p.imgList)[0] == null ? 'defaultImg.png' : Object.values(p.imgList)[0] }" class="card-img-top" alt="...">
								
								<div  class="card-body">
									<div class="itemCardTitle"><span class="tfont1">${p.ititle}</span> </div>
									
									<div class="itemCardAdress"><span class="tfont4"> ${p.itrade == 1 ? '' : p.itrade == 2 ? '대면거래' : '중개거래' }</span> </div>
									<div class="itemCardAdress"><span class="tfont3"> ${ p.isafepayment == 1 ? '안전결제사용' : '' }</span> </div>
									<div class="itemCardAdress"> ${ p.itrade == 1 ? '배송' : p.itradeplace } </div>
									<div class="itemCardPrice"><span class="tfont2">${p.iprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}원</span> </div>
								</div>
								
							</div>
						</a>
					</div>
				`
				
			})
						
			document.querySelector('.row').innerHTML = html
			
			// 검색 완료 후 검색 필드 초기화
			searchWord = '';
			
		},
		error: e => {
			
			console.log('실패')
			console.log(e)
		}
	})
	
}


// 대분류 카테고리 출력
getMainCategory()
function getMainCategory(){
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		async: false,
		data: { type : "getMainCategory" },
		success: s => {
			
			let html = `<tr>`
			for( let i=0; i<s.length; i++ ){
				
				if( i==5 || ( i>4 && i%5==0 ) ){
					html += `</tr><tr>`
				}

				html += `<td onclick="outputSubCategory(${s[i].uno}, '${s[i].uname}')"> ${s[i].uname} </td>`
			}
			html += `</tr>`
			
			document.querySelector('.outputUmaincategory').innerHTML = html 
		},
		error: e => {
			console.log('에러발생')
		}
	})
	
}

// 대분류 카테고리 필터
function outputSubCategory( uno, uname ){
	
	// 상세 카테고리 필터기능 수행
	filterNum = uno
	filterCategory = 'uno';
	document.querySelector('.selCategory1').innerHTML = ` ${uname}`
	document.querySelector('.selCategory2').innerHTML = ``
	getItemList();
	
	$.ajax({
		url: "/Ezen_teamB/ItemController",
		method: "get",
		data: { type : "getSubCategory", uno : uno },
		success: s => {
			
			// 소분류 카테고리 출력
			let html = `
				<div class="guideLabel"> 소분류 </div>
					<table class="outputDsubcategory">
						<tr>`
			for( let i=0; i<s.length; i++ ){
				
				if( i==5 || ( i>4 && i%5==0 ) ){
					html += `</tr><tr>`
				}
				html += `<td onclick="subCategoryFilter(${s[i].dno}, '${s[i].dname}' )"> ${s[i].dname} </td>`
			}
			
			// 테이블의 간격을 일정하게 유지하기 위해 
			// 소분류의 길이가 5이하일 경우 5까지 부족한 td의 갯수만큼 추가 
			if( s.length < 5 ){
				
				for(let i=0; i<5-s.length; i++){
					html += `<td></td>`	
				}
			}
			
			html += `</table></tr>`
			
			document.querySelector('.bottomCategory').innerHTML = html 
			
		},
		error: e => {
			console.log('에러발생')
		}
		
	})
}

// 소분류 카테고리 필터
function subCategoryFilter( dno, dname ){
	
	// 상세 카테고리 필터기능 수행
	document.querySelector('.selCategory2').innerHTML = ` > ${dname}`
	filterNum = dno
	filterCategory = 'dno'
	getItemList();
	
}

// 검색어를 통한 검색
function searchByWord(){
	
	let searchValue = document.querySelector('.searchValue').value;
	
	if( searchValue == '' ){
		alert('검색어를 입력하십시오')
		return
	}
	
	// 검색 기능 수행
	searchWord = searchValue
	getItemList();
	
}

// 카테고리 전체보기
function searchAll(){
	
	filterCategory = ''
	searchWord = ''
	document.querySelector('.selCategory1').innerHTML = ``
	document.querySelector('.selCategory2').innerHTML = ``
	
	getItemList();
}









