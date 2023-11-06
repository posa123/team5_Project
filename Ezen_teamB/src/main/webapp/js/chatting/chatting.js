console.log('채팅페이지')


let ino = new URL( location.href ).searchParams.get("ino");
let rno = new URL( location.href ).searchParams.get("rno");
console.log(rno)


// 서버소켓 접속
let clientSocket = new WebSocket(`ws://192.168.17.17:80/Ezen_teamB/serversocket/${loginMid}/${ino}/${rno}`);

clientSocket.onerror = e=>{console.log('서버와 오류발생 ' + e)};
clientSocket.onclose = e=>{console.log('서버와 연결끊김 ' + e)};
clientSocket.onmessage = e =>onMsg(e);

// 전송버튼 눌렀을때 함수(메시지 전송기능)
function onSend(){ console.log('전송함수');

	let msgvalue = document.querySelector('.msg').value;
	if(msgvalue == '' || msgvalue == '\n'){return;}	// 공백이나 엔터키 입력시 메시지 전송 x
	
	let msg = {type : 'message', content : msgvalue}
	
	clientSocket.send(JSON.stringify(msg));
	document.querySelector('.msg').value = ``;
	
}	// onSend() f end

// 메세지 받았을때 함수
function onMsg(e){
	console.log(e.data);
	
	let msg = JSON.parse(e.data);

	console.log(msg);
	
	msg.jcontent = JSON.parse(msg.jcontent)
	console.log(msg.jcontent)
	
	msg.jcontent.content = msg.jcontent.content.replace(/\n/g,'<br>');
	  
	
	roadChat();
}

roadChat();
// 채팅방입장시 예전 채팅 불러오는 함수
function roadChat(){
	
	$.ajax({
      url :  "/Ezen_teamB/ChattingController",
      method : "get" ,
      async : false, 
      data : {rno : rno} ,
      success : r => {console.log(r)
	  	let chatcont = document.querySelector('.chatcont')
	  	let html = ``;
      	for(let i = 0; i < r.length; i++){
			  r[i].jcontent = JSON.parse(r[i].jcontent)
			  if(r[i].jcontent.type == 'alram'){
				  html = `${typeHTML(r[i].jcontent)}`;
			  }
			  else if(r[i].caller == loginMid){
				  html +=
				  `
				  	<div class="rcont">
						<div class="subcont">
							<div class="date">${r[i].jchatdate}</div>
							${typeHTML(r[i].jcontent)}
						</div>
					</div>
					`;
				  
			  }
			  
			  else{
				  html += 
				  
				  `
				  	<div class="lcont">
						<div class="tocont">
							<div class="name">${r[i].caller}</div>
							<div class="subcont">
								${typeHTML(r[i].jcontent)}
								<div class="date">${r[i].jchatdate}</div>
							</div>
						</div>
					</div>
				  `
				  
			  }
		  }
		  chatcont.innerHTML = html;
		  
		  chatcont.scrollTop = chatcont.scrollHeight;
	  },
	  error : e => {console.log('오류내용' + e)}
	})
	
}




// 메시지 타입 구분 함수
function typeHTML(msg){
	
	let html = ``;
	console.log(msg);
	
	// 1. 메시지 타입 일때는 <div> 반환
	if(msg.type == 'message'){
		html += `<div class="content">${msg.content}</div>`;
	}
	
	// 2. 이모티콘 타입 일때는 <img> 반환
	else if(msg.type == 'emo'){
		html += `<img src="/jspweb/img/emo${msg.content}.gif"/>`;
	}
	// 3. 만약 알람 타입일때는 <div> 반환
	else if(msg.type == 'alram'){
		html += `<div class="alram">${msg.content}</div>`
	}
	
	return html;
	
}
























