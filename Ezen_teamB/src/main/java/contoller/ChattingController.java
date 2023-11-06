package contoller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ChattingDao;
import model.dto.MsgDto;


@WebServlet("/ChattingController")
public class ChattingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChattingController() {
        super();
    }
    
    // 채팅내용을 불러오는 함수
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rno = request.getParameter("rno");
		System.out.println("방번호 : " + rno);
		
		SimpleDateFormat sdf = new SimpleDateFormat("mm/dd HH:mm");
		
		List<MsgDto> result = ChattingDao.getInstance().getMsgs(rno);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(result);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String rno = request.getParameter("rno");
		
		boolean result = ChattingDao.getInstance().outMsgRoom(rno);
		System.out.println(result);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
	}

}
