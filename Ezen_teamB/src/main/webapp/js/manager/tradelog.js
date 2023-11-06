console.log('거래내역 js')

/* 게시물 조회 조건 객체 */
let pageObject3 = { key : '', keyword : '', pDate : '', nDate : '' }


// 검색버튼을 눌렀을때 실행되는 함수
function printTradelog(){
	let priDate = document.querySelector('.priDate');
	let nextDate = document.querySelector('.nextDate');
	
	console.log(priDate.value);	console.log(nextDate.value);
	
	pageObject3.pDate = priDate.value;
	pageObject3.nDate = nextDate.value;
	
	$.ajax({
		url: "/Ezen_teamB/TradelogManagement",
		method: "get",
		async: false,
		data: pageObject3,
		success: r => {console.log(r)
			
			let memberListTable = document.querySelector('.memberListTable');
			
			let html = `
				<tr>
					<th> 거래번호 </th>
					<th> 아이디 </th>
					<th> 이름 </th>
					<th> 거래위치 </th>
					<th> 게시물제목 </th>
					<th> 거래방법 </th>
					<th> 거래일시 </th>
				</tr>
			`;
			
			r.forEach( p => {
				
				if(p.itrade == 1){p.itrade = '배송'}
				else if(p.itrade == 2){p.itrade = '대면거래'}
				else{p.itrade = '안전거래'}
				
				p.tradedate = p.tradedate.substr(0,10);
				
				html += `
					<tr>
						<td> ${ p.tno } </td>
						<td> ${ p.mid } </td>
						<td> ${ p.mname } </td>
						<td> ${ p.itradeplace } </td>
						<td> ${ p.ititle } </td>
						<td> ${ p.itrade } </td>
						<td> ${ p.tradedate } </td>
					</tr>
				`
			});
			
			let totalMemeberCount = document.querySelector('.totalMemeberCount');
			totalMemeberCount.innerHTML = `${r.length}`
			
			
			memberListTable.innerHTML = html;
		
			
		},
		error : e => {console.log(e)}
	})		
	
}



// 검색
function onSearchTradelog(){
	
	let select = document.getElementById('selectMemberFilter');
	pageObject3.key = select.options[select.selectedIndex].value
	pageObject3.keyword = document.querySelector('.searchMemberKeyword').value;
	console.log(pageObject3.key);	console.log(pageObject3.keyword);
	
	printTradelog();
	
}


















