package contoller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.BoardDao;
import model.dto.Board;
import model.dto.MemberList;
import model.dto.PageDto;
import service.FileService;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }
    
	// 1. 게시판 등록
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	MultipartRequest multi = new MultipartRequest(
    			request, 
    			request.getServletContext().getRealPath("/jsp/board/upload"),
    			1024*1024*1024 ,
    			"UTF-8",
    			new DefaultFileRenamePolicy());
    	// 경로 확인
    	System.out.println(request.getServletContext().getRealPath("/jsp/board/upload"));
    	
    	String btitle = multi.getParameter("btitle");
    	String bcontent = multi.getParameter("bcontent");
    	String bfile = multi.getFilesystemName("bfile");
    	
    	int mno = ( (MemberList)request.getSession().getAttribute("loginSession") ).getMno(); System.out.println(mno);
    	int cno = Integer.parseInt(multi.getParameter("cno")); 
    	
    	
    	Board boardDto = new Board(cno, btitle, bcontent, bfile, mno); System.out.println(boardDto);
    	
    	boolean result = BoardDao.getInstance().bwrite(boardDto);
    	
    	response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(result);
    	
	}
    
	// 2. 게시판 조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.
		String type = request.getParameter("type");
		ObjectMapper objectMapper = new ObjectMapper();
		String json="";
		
		// 전체 조회
		if(type.equals("1")) {
			
			String key = request.getParameter("key");
			String keyword = request.getParameter("keyword");
			int cno = Integer.parseInt(request.getParameter("cno")); // 카테고리
			int listsize = Integer.parseInt(request.getParameter("listsize")); // 출력할 게시물 최대 게시물수 
			int page = Integer.parseInt(request.getParameter("page")); // 페이지
			
			int starrow = (page-1)*listsize; // 페이지번호*최대게시물수
			
			
			int totalsize = BoardDao.getInstance().getTotalSize(cno,key,keyword);
			int totalpage = totalsize%listsize == 0  ? totalsize/listsize : totalsize/listsize+1;
			int btnsize = 5;
			int starbtn =((page-1)/btnsize)*btnsize+1; 
			int endbtn = starbtn+(btnsize-1); 
			if( endbtn >= totalpage ) endbtn = totalpage;
			
			ArrayList<Board> result = BoardDao.getInstance().getList(cno, listsize, starrow, key, keyword);
			PageDto pageDto = new PageDto(page, listsize,btnsize, totalsize, totalpage, starbtn, endbtn, result);
			
			json = objectMapper.writeValueAsString(pageDto);
		
		
		
		}else if( type.equals("2")) { // 개별 조회 로직
			
			int bno = Integer.parseInt(request.getParameter("bno"));
		
			Board result = BoardDao.getInstance().getBoard(bno);
			
			Object object = request.getSession().getAttribute("loginSession");
			
			if(object == null ) {
				//result.setIshost(false);
			} else {
				MemberList login = (MemberList)object;
				
				if( login.getMno() == result.getMno()) { result.setIshost(true);}
				else {result.setIshost(false);}
			}
			json = objectMapper.writeValueAsString(result);
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
	}


	// 3. 게시판 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = new MultipartRequest(
				request, 
				request.getServletContext().getRealPath("/jsp/board/upload"),
				1024*1024*1024,"UTF-8", 
				new DefaultFileRenamePolicy());
		int cno = Integer.parseInt(multi.getParameter("cno"));
		String btitle = multi.getParameter("btitle");
		String bcontent = multi.getParameter("bcontent");
		String bfile = multi.getFilesystemName("bfile");
		
		int bno = Integer.parseInt(multi.getParameter("bno"));
		Board updateDto = new Board(cno,bno,btitle,bcontent,bfile);
		if(updateDto.getBfile() == null ) {
			// 
			updateDto.setBfile(BoardDao.getInstance().getBoard(bno).getBfile());
		}else {
			String filename = BoardDao.getInstance().getBoard(bno).getBfile();
			filename = request.getServletContext().getRealPath("/jsp/board/upload")+"/"+filename;
			FileService.fileDelete(filename);
		}
		boolean result = BoardDao.getInstance().bUpdate(updateDto);
		
		response.setContentType("application/json; charset=UTF-8"); 
		response.getWriter().print(result);
	}
	// 4. 게시판 삭제
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청
		int bno = Integer.parseInt(request.getParameter("bno"));
		
			String filename = BoardDao.getInstance().getBoard(bno).getBfile();
		
			boolean result = BoardDao.getInstance().bDelete(bno);
			
			if(result) {
				filename = request.getServletContext().getRealPath("/jsp/board/upload")+"/"+filename;
				FileService.fileDelete(filename);
			}
			response.setContentType("application/json; charset=UTF-8"); 
			response.getWriter().print(result);
	}

}
