/* 썸머노트 실행 */
$(document).ready(function() {
	
	let option ={
		lang : 'ko-KR' , 
		height : 500 ,
		placeholder : '여기에 내용작성'
	}
	$('#summernote').summernote(option);
  
});
if (loginMid == 'admin') {
	document.querySelector('.cno').innerHTML = `<option value="1"> 공지사항 </option>
	  	 											 <option value="2">건의사항</option> `
} else { document.querySelector('.cno').innerHTML = `<option value="2">건의사항</option>` }
// 글등록
function writeBtn() {
	
	
	console.log( '글등록 버튼' )
	let form = document.querySelectorAll('.writeForm')[0];
	
	let formData = new FormData(form)
	
	$.ajax({
		url : "/Ezen_teamB/BoardController" , 
		method : "post" ,
		data : formData  ,
		contentType : false,
		processData : false,
		success : r => { console.log(r) 
			if(r){
				alert('글등록성공')
				location.href="/Ezen_teamB/jsp/board/mainboard.jsp";
			}else{ alert('글등록실패') }
		},
		error : e => { 
			console.log('에러')
			console.log(e )
		}

	})
}