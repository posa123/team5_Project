package contoller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ManagerDao;
import model.dto.MemberList;
import model.dto.MemberManageDto;


@WebServlet("/MemberManagement")
public class MemberManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberManagement() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		
		
		int maxSize = Integer.parseInt( request.getParameter("maxSize") );
		int page = Integer.parseInt( request.getParameter("page") );
		
		// 페이지 레코드 시작번호 ( 0, 13, 26 ... )
		int startRow = (page-1) * maxSize;
		// 총 회원수
		int totalMemeberCount = ManagerDao.getInstance().getTotalPageCount(key, keyword);
		
		// 마지막 페이지 번호 == 총 페이지 수
		int totalPageCount = 
				totalMemeberCount%maxSize == 0 
				? totalMemeberCount/maxSize 
				: totalMemeberCount/maxSize+1;
		
		// 페이지버튼 번호의 최대개수
		int btnsize = 5;
		// 페이지버튼 번호의 시작번호
		int startBtn = ((page-1)/btnsize)*btnsize+1;
		// 페이지버튼 번호의 마지막 번호
		int endBtn = startBtn+btnsize;
		if( endBtn >= totalPageCount ) endBtn = totalPageCount;
		
		ArrayList<MemberList> result = ManagerDao.getInstance().getList(maxSize, startRow, key, keyword);
		MemberManageDto memberManageDto = new MemberManageDto(
				page, maxSize, startRow, totalMemeberCount, totalPageCount, startBtn, endBtn, result
		);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString( memberManageDto );
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// 회원 강제 탈퇴
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mno = Integer.parseInt( request.getParameter("mno") );
		
		boolean result = ManagerDao.getInstance().exileMemeber( mno );
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
	}

}
