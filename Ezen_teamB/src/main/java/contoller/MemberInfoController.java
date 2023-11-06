package contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


import model.dto.MemberList;

/**
 * Servlet implementation class MemberInfoController
 */
@WebServlet("/MemberInfoController")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInfoController() {
        super();

    }
    
    
    // 로그인정보 호출 // 로그아웃 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type.equals("info")) {
			//세션에 저장된 로그인객체 꺼내기

		Object session = request.getSession().getAttribute("loginSession");
			// 타입변환[부모 -> 자식으로 객체타입변환(캐스팅)]
		MemberList loginDto = (MemberList)session;		
			
		ObjectMapper objectMapper = new ObjectMapper();
			// ObjectMapper 객체 만들고 objectMapper.writeValueAsString 이걸로 json에 대입
		
		String json = objectMapper.writeValueAsString(loginDto);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);								
		}
		else if (type.equals("logout")){
			//세션에 저장된 로그인 객체 없애기, 초기화 
			request.getSession().setAttribute("loginSession", null);
		}
	}

	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
