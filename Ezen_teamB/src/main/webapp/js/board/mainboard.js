
function onWriteBtn(){
	console.log('글쓰기버튼')
	if(loginState){
		location.href="/Ezen_teamB/jsp/board/writeboard.jsp"
	}else{alert('회원전용입니다.')
		location.href="/Ezen_teamB/jsp/member/login.jsp"
	}
	
}
/* 게시물 조회 */
let pageObject = { type : 1 , cno : 0 , listsize : 10 , page : 1 , key : '' , keyword : ''}

// 검색
function boardSearch(){
	console.log('검색버튼')
	pageObject.key = document.querySelector('.key').value
	pageObject.keyword = document.querySelector('.keyword').value
	getList(1)
}
// 카테고리 선택
function onCategory(cno){
	console.log(cno);
	pageObject.cno = cno;
	pageObject.key = ''; pageObject.keyword = '';
	getList(1);
}
// 게시물 출력 수
function onListSize(){
	pageObject.listsize = document.querySelector('.listsize').value
	getList(1)
}

// 모든 게시글 출력
getList(1);
function getList(page){
	
	pageObject.page = page;
	$.ajax({
		url :"/Ezen_teamB/BoardController" ,
		method : "get",
		data : pageObject ,
		success : pageDto => { console.log(pageDto)
			
			let boardTable = document.querySelector('.boardTable');
			
			let html = `
				<tr class="boardpoint">
					<th> 번호 </th>
					<th> 카테고리 </th>
					<th> 제목 </th>
					<th> 작성자 </th>
					<th> 작성일 </th>
				</tr>
			`;
			pageDto.boardList.forEach( b=>{
				html +=`
					
					<tr class="boardpoint">
						<td>${b.bno}</td>
						<td>${b.cname}</td>
						<td><a href="/Ezen_teamB/jsp/board/detailedboard.jsp?bno=${b.bno}&mid=${b.mid}">${b.btitle} </a> </td>
						<td>${b.mid}</td>
						<td>${b.bdate}</td>
					</tr>
				
				
				`;
			})
			boardTable.innerHTML = html;
			html = ``;
				// 페이징처리
				html +=`<button onclick="getList(${page <= 1 ? page : page-1})" type="button"> < </button>`
				for( let i = pageDto.starbtn; i<=pageDto.endbtn; i++){
					html +=`<button class="${page == i ? 'selectpage' : ''}" onclick="getList(${i})" type="button">${i}</button>`
				}
				html +=`<button onclick="getList(${page >= pageDto.totalpage ? page : page+1})" type="button"> > </button>`
			document.querySelector('.pagebox').innerHTML=html;
			
			let boardcount = document.querySelector('.boardcount');
			// 1. 검색이 있을때 
			if(pageObject.key == '' && pageObject.keyword == ''){
				boardcount.innerHTML = ` 총 게시물 수 : ${ pageDto.totalsize }`
			}else {
				boardcount.innerHTML = ` 검색된 게시물 수 : ${ pageDto.totalsize }`
			}
		
		} ,
		error : e => {console.log(e)}
	})
}