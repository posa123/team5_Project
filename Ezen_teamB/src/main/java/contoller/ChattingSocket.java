package contoller;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChattingSocket")
public class ChattingSocket {
	
	// import websocket
	
		public static ArrayList<Session> joinList = new ArrayList<>();
			
		// 1.
		@OnOpen		// 클라이언트가 서버소켓에 접속했을대 매핑/연결
		public void onOpen(Session session) {
			System.out.println("클라이언트소켓 접속 : " + session);
			System.out.println(session.getId());
			System.out.println(session.getRequestURI());
			
			// 들어온 클라이언트소켓들을 서버소켓에 조장
			joinList.add(session);
			System.out.println("접속명단 : " + joinList);
			
			
			
		}
		// 2. 클라이언트가 서버소켓과 연결이 닫혔을때 매핑/연결(JS에서 웹소켓 객체를 초기화할때[새로고침, 페이지전환...])
		@OnClose
		public void onClose(Session session) {
			joinList.remove(session);		// 접속명단 리스트에서 제외
		}
		
		// 3.
		@OnMessage	// 클라이언트가 서버소켓의 메시지를 보냈을때 연결/매핑
		public void onMessage(Session session, String msg) {
			System.out.println(msg);
				// 현재 접속된 명단(클라이언트소켓) 들에게 메시지 전달
			for(int i = 0; i < joinList.size(); i++) {Session s = joinList.get(i);}
			for(Session s : joinList) {}
			joinList.forEach(s -> {
				try {s.getBasicRemote().sendText(msg);} catch (IOException e) {System.out.println(e);}
				// s : 접속명단에 잇는 클라이언트소켓 중 하나
					// getBasicRemote() : 메시지 전송 메소드 제공
							// .sendText : 메시지를 String 타입으로 전송
			});
			
		}

}
