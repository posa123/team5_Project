	
	let urlParams = new URL(location.href).searchParams
		console.log(urlParams)
	let bno = urlParams.get("bno")
		console.log(bno)
/* 개별 조회 */
getBoard()
function getBoard(){

		
	$.ajax({
		url : "/Ezen_teamB/BoardController" ,
		method : "get" ,
		async: false,
		data : { type : 2 , bno : bno } ,
		success : r =>{ console.log('통신 성공')
			let outputFileName = document.querySelector('.outputFileName')
			let boardBox = document.querySelector('.boardBox')
			let bottomBtn = document.querySelector('.bottomBtn')
			
			let html =`
				<div class="detailedTop">
				<div> 
					제목 : ${r.btitle} </br>
				 	아이디 :  ${r.mid} 
				 </div>
				<div> 
					${r.cname} </br>
					${r.bdate}
				</div>
				</div>
				
				<div class="detailedMid">
				
					<div class="detailedContent"> ${r.bcontent} </div>
			
				
				</div>
		

			`;
			
			boardBox.innerHTML = html
				
			if(r.ishost){
			
				html =`
					<div class="detailbtn">
						<button onclick="bUpdate(${r.bno})" type="button">수정</button>
						<button onclick="bDelete(${r.bno})" type="button">삭제</button>
					</div>
					`;
				
				html +=`<div class="rlist"><a href="mainboard.jsp"><button type="button">목록보기</button></a></div>`
				bottomBtn.innerHTML = html;
				
				
			}
			html = `<div class="detiledFile">첨부파일 : <a href="/Ezen_teamB/BoardFileDownload?filename=${r.bfile}">${r.bfile}</a></div>`;
			outputFileName.innerHTML = html
			
			
			
		} ,
		error : e => { console.log('통신 실패')}

	})
	
	
}
// 게시물 수정
function bUpdate(bno){
	location.href=`/Ezen_teamB/jsp/board/updateboard.jsp?bno=${bno}`;
}
// 게시물 삭제
function bDelete(bno) {
	$.ajax({
		url : "/Ezen_teamB/BoardController",
		method : "delete" ,
		async: false,
		data : { bno : bno } ,
		success : r => { console.log("통신성공")
			if(r) {
				alert('삭제 성공')
				location.href="/Ezen_teamB/jsp/board/mainboard.jsp"
			}else { alert('삭제 실패')}
		} ,
		error : e => { console.log("통신실패") }
	})
}
// 개별 답글 등록
function replyWrite(){
	console.log("답글")
	let rcontent = document.querySelector('.rcontent').value
	console.log(rcontent);
	
	$.ajax({
		url : "/Ezen_teamB/BoardReplyController" ,
		method : "post" ,
		async: false,
		data : { bno : bno , rcontent : rcontent } ,
		success : r =>{ console.log('통신 성공')
			if(r) {
				alert('등록 성공')
				getReply();
			}else{ alert('등록 실패')}
		
		} ,
		error : e => { console.log('통신 실패')}
		
	})
	
}
let mid = urlParams.get("mid")
// 개별 답글 출력

getReply();

function getReply(){
	
	if( loginMid == 'admin' || loginMid == mid ){
		document.querySelector('.boardReply').innerHTML =`<div class="replyTextarea">
						<textarea cols="100%" rows="" class="rcontent" name="rcontent"></textarea>
						<button class="replyBtn" onclick="replyWrite()"type="button">답글등록</button>
					</div>`
			
		$.ajax({
			url : "/Ezen_teamB/BoardReplyController",
			method : "get" ,
			async: false,
			data : { bno : bno },
			success : r => {console.log('통신성공')
			
				let boardReplyContent = document.querySelector('.boardReplyContent')
				let html = ``
				
				r.forEach( p => {
					
					html += `<div class="replyContent"> 
						<div>
							ID : ${p.mid}
							내용 : ${p.rcontent}
							날짜 : ${p.rdate}
						</div>
						<div class="replyBottom"> <button onclick="replyUpdate(${p.rno})"type="button">수정</button> <button onclick="replyDelete(${p.rno})" type="button">삭제</button></div>
					</div>`	
				})
				
			
			boardReplyContent.innerHTML = html
			},
			error : e => { console.log('통신실패')} 
			
		}) // ajax end
	} // if end

}// f end

// 개별 답글 수정
function replyUpdate(rno){
	console.log(rno)
	console.log('답글 수정')
	let rcontent = prompt('수정할 내용을 작성해주세요.','');
	$.ajax({
		url : "/Ezen_teamB/BoardReplyController" ,
		method : "put" ,
		data : {rno : rno , rcontent : rcontent} ,
		success : r => { console.log('통신성공') 
			if(r){
				alert('답글 수정.')
				getReply();
			}else{ alert('수정 실패')}
		},
		error : e => {console.log('통신실패')}
	})	

}

// 개별 답글 삭제
function replyDelete(rno){
	console.log('답글 삭제')
	$.ajax({
		url : "/Ezen_teamB/BoardReplyController" ,
		method : "delete" ,
		data : { rno } ,
		success : r => {console.log('통신 성공')
			if(r) {
				alert('답글이 삭제되었습니다.')
				getReply();
			}else{ alert('삭제 실패')}
		},
		error : e => { console.log('통신 실패')}
		
	})

}







