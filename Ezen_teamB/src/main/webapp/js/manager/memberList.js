
/* 게시물 조회 조건 객체 */
let pageObject = { maxSize : 13, page: 1, key : '', keyword : '' }
	// * maxSize 	: 하나의 페이지에 최대표시할 멤버수
	// * page		: 현재 출력되는 페이지의 하단 번호
	// * key 		: select 내 옵션 목록
	// * keyword 	: 검색할 데이터(키워드)

getMemberList(1);	// 기본 페이지 번호 1
function getMemberList( page ){
	
	$.ajax({
		url: "/Ezen_teamB/MemberManagement",
		method: "get",
		async: false,
		data: pageObject,
		success: r => {
			console.log('성공')
			console.log(r)
			
			/* 테이블 생성 */
			let memberListTable = document.querySelector('.memberListTable');
			
			let html = `
				<tr>
					<th> 성명 </th>
					<th> 생년월일 </th>
					<th> 전화번호 </th>
					<th> 이메일 </th>
					<th> 아이디 </th>
					<th> 포인트 </th>
				</tr>
			`;
			
			r.memberList.forEach( m => {
				html += `
					<tr>
						<td> ${ m.mname } </td>
						<td> ${ m.msno1 } </td>
						<td> ${ m.mphone } </td>
						<td> ${ m.memail } </td>
						<td> ${ m.mid } </td>
						<td> ${ m.mpoint } </td>
						<td> <button onclick="exileMemeber( ${m.mno} )" class="secessionBtn" type="button">탈퇴</button> </td>
					</tr>
				`
			});
			
			memberListTable.innerHTML = html;
			
			
			/* 페이징 버튼 */
			html = ``
			
			// 페이지 개수만큼 페이징번호 구성
				// 이전버튼
			html += `<button onclick="getMemberList(${page-1})" class="beforePageBtn" type="button"> < </button>`;
				// 페이지 숫자버튼
			for( let i=r.startBtn; i<=r.endBtn; i++ ){
					// 만일 현재페이지(page)와 i가 같으면 페이지와 일치하면 버튼태그에 class="selectpage"
				html += `<button class="${ page == i ? 'selectpage' : '' } centerPageBtn" onclick="getMemberList(${i})" type="button"> ${i} </button>`
			}
				// 다음버튼 
			html += `<button onclick="getMemberList(${page >= r.totalPageCount ? page : page+1})"  class="afterPageBtn" type="button"> > </button>`;
				
			document.querySelector('.pagingBtnField').innerHTML = html;
				
			/* 조회된 회원수 출력 */
			let totalMemeberCount = document.querySelector('.totalMemeberCount');
			
			// 1. 검색이 있을 때
			if( pageObject.key == '' && pageObject.keyword == '' ){
				totalMemeberCount.innerHTML = ` ${ r.totalMemeberCount }`
			} 
			// 2. 없을 때
			else {	
				totalMemeberCount.innerHTML = ` ${ r.totalMemeberCount }`
			}
			
		},
		error: e => {
			console.log('에러발생')
		}
		
		
	})
	
}

// 검색
function onSearchMember(){
	
	let select = document.getElementById('selectMemberFilter');
	pageObject.key = select.options[select.selectedIndex].value
	pageObject.keyword = document.querySelector('.searchMemberKeyword').value;
	getMemberList(1)
	
}

// 회원 강제 탈퇴
function exileMemeber( mno ){
	
	let promptObj = prompt(`해당 회원의 회원번호 '${mno}'을(를) 입력 후 확인버튼을 눌러주십시오`)
	
	if( promptObj == null ) return;
	if( promptObj != mno ){
		alert('입력하신 정보가 회원번호와 일치하지 않습니다')
		return;
	}
	
	$.ajax({
		url: "/Ezen_teamB/MemberManagement",
		method: "delete",
		data: { mno : mno },
		success: ( s=> {
			if( s ){
				alert('해당 회원을 강제탈퇴하였습니다')
			}
			
		}),
		error: e =>{
			console.log('에러발생')
		}
		
	})
	getMemberList( 1 )
	
}


























