package contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.tomcat.util.json.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ChattingDao;
import model.dto.ClientDto;
import model.dto.MsgDto;

@ServerEndpoint("/serversocket/{mid}/{ino}/{rno}")
public class ServerSocket {
	
		// 0. 접속된 클라이언트소켓들의 저장소[ 세션, 아이디 저장 => ClientDto ]
		public static List<ClientDto> clientList = new Vector<>();
		
		// 1. 클라이언트 소켓으로 부터 접속받았을때. JS(new WebSocket)
		@OnOpen		// 매개변수 : 1. 접속된 클라이언트소켓(세션) 2. 접속된 회원아이디
		public void onOpen(Session session,@PathParam("mid") String cmid,@PathParam("ino") int ino,
				@PathParam("rno") String rno) {
			System.out.println("클라이언트 소켓이 입장했습니다." + session);
			System.out.println("접속한 회원 아이디 : " + cmid);
			System.out.println("연결된 물품 번호 : " + ino);
			System.out.println("채팅방 번호 : " + rno);
			String rmid = ChattingDao.getInstance().findRMid("1", ino);
			if(rmid.equals(cmid)) {
				rmid = ChattingDao.getInstance().findRMid("2", ino);
			}
			System.out.println(rmid);
			// 1-1 : 접속된 클라이언트소켓을 리스트에 저장
			ClientDto clientDto = new ClientDto(session, cmid, rmid, ino,rno);
			clientList.add(clientDto);
			System.out.println("접속한 클라이언트들 : " + clientDto);
		}
		
		// 2.
		@OnError
		public void onError(Session session, Throwable throwable) {}
		
		
		// 3. 클라이언트소켓과 서버소켓이 연결이 끊겼을때 
		@OnClose
		public void onClose(Session session) {
			//	* 접속목록에서 서버세션 제거
			for(ClientDto clientDto : clientList) {
				//	접속목록에서 연결이 끊긴 세션 찾기
				if(clientDto.getSession() == session) {
					// 클라이언트 소켓의 세션과 연결이 끊긴 세션이 같으면 리스트에서 해당 dto 제거
					clientList.remove(clientDto);
					break;
				}
			}
			
		}
		
		
		// 4.
		@OnMessage	// 매개변수 : 1. 메시지를 보낸 클라이언트소켓(세션), 2. 메시지 내용[문자열]
		public void onMessage(Session session, String msg) throws IOException {
			
			System.out.println(msg);
			String modMsg = msg.replace("\n", "<br>");
			
			System.out.println("보낸 클라이언트 : " + session + "		보낸 내용 : " + msg); 
			
			
			
				// 2-2 메시지를 보낼 내용 구성[ 보낸사람, 보낸내용 ]
			MsgDto dto = null;
					// 보낸사람 찾기[ 보낸 세션을 이용한 보낸 mid 찾기 ]
					for(ClientDto clientDto : clientList) {
						if(clientDto.getSession() == session) {
							// 회원목록중에 보낸세션과 일치하면 보낸사람 mid와 내용으로 dto 구성
							int cmno = ChattingDao.getInstance().findMno(clientDto.getCmid());
							int rmno = ChattingDao.getInstance().findMno(clientDto.getRmid());
							boolean result = ChattingDao.getInstance().recordMsg(cmno, rmno, msg,
									clientDto.getIno(), clientDto.getRno());
							System.out.println(result);
							dto = new MsgDto(clientDto.getCmid(), msg);
							break;
						}
					}
					// msgDto를 JSON으로 사용할수 있도록 형변환
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonMsg = objectMapper.writeValueAsString(dto);
					
				
			// 2-1 받은 메시지를 접속된 회원들에게 모두 전송
			for(ClientDto clientDto : clientList ) {	// 회원목록에서 하나씩 회원 꺼내기
				clientDto.getSession().getBasicRemote().sendText(jsonMsg);	// 예외처리 필수!
				System.out.println("메세지 전송");
			}
			
		}
		
		
		

}
