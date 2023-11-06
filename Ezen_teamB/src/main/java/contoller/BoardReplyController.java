package contoller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.BoardDao;
import model.dto.MemberList;
import model.dto.ReplyDto;

/**
 * Servlet implementation class BoardReplyController
 */
@WebServlet("/BoardReplyController")
public class BoardReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	// 개별 답글 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rcontent =request.getParameter("rcontent");
		int mno = ((MemberList)request.getSession().getAttribute("loginSession")).getMno(); //로그인 세션에서 호출
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		System.out.println(rcontent);
		System.out.println(mno);
		System.out.println(bno);
		
		ReplyDto replyDto = new ReplyDto(mno,bno,rcontent);
		boolean result = BoardDao.getInstance().bReply(replyDto);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(result);
		
	}
	
	// 개별게시물 답글 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(111);
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println(111);
		ArrayList<ReplyDto> list = BoardDao.getInstance().getReply(bno);
		System.out.println(111);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);
		
		System.out.println(111);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(json);
		System.out.println(111);
	}


	// 개별 답글 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno = Integer.parseInt(request.getParameter("rno"));
		String rcontent = request.getParameter("rcontent"); 
		
		ReplyDto replyDto = new ReplyDto(rno, rcontent);
		
		boolean result = BoardDao.getInstance().rUpdate(replyDto);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(result);
		
	}

	// 개별 답글 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		boolean result = BoardDao.getInstance().rDelete(rno);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(result);
	}

}
