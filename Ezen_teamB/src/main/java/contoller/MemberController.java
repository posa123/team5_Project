package contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.MemberDao;
import model.dto.MemberList;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }


    // 회원 가입 컨트롤러
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String uploadpath = request.getServletContext().getRealPath("/jsp/img");
    	
    	MultipartRequest multi = new MultipartRequest(request, 
    			uploadpath,
    			1024*1024,
    			"UTF-8",
    			new DefaultFileRenamePolicy());
     	
    	String signName = multi.getParameter("signName");

    	String signResidentNumF = multi.getParameter("signResidentNumF");

    	String signResidentNumS = multi.getParameter("signResidentNumS");

    	String signPhone = multi.getParameter("signPhone");

    	String signEamil = multi.getParameter("signEmail");

    	String signAddress = multi.getParameter("addr2");
 
    	String signAddress2 = multi.getParameter("addr3");

    	signAddress= signAddress+" "+signAddress2;
    	System.out.println("주소" +signAddress);

    	signAddress= signAddress+""+signAddress2;
 

    	String signId = multi.getParameter("signId");

    	String signPwd1 = multi.getParameter("signPwd");
   	
    	MemberList memberList = new MemberList(
    			signName , signResidentNumF , signResidentNumS , signPhone ,
    			signEamil , signAddress , signId , signPwd1
    			);
    	boolean result = MemberDao.getInstance().signup(memberList);

    	response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(result);

    }
    
    
    // 회원정보를 불러오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		
		if(type.equals("1")) {
			
			int mno = Integer.parseInt(request.getParameter("mno"));
			
			MemberList result = MemberDao.getInstance().memberInfo(mno);
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(result);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().print(json);
		}
		
	}

	// 회원정보 수정
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int mno = Integer.parseInt(request.getParameter("mno"));
		String meamil = request.getParameter("memail");
		String madress = request.getParameter("adress1") + " "
				+ request.getParameter("adress2");
		String mpwd = request.getParameter("mpwd");
		
		boolean result = MemberDao.getInstance().updateInfo(mno, meamil, madress, mpwd);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
	}
	
	
	// 회원탈퇴
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mno = Integer.parseInt(request.getParameter("mno"));
		String mpwd = request.getParameter("mpwd");
		
		boolean result = MemberDao.getInstance().deleteMember(mno, mpwd);
		System.out.println(result);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(result);
		
	}

}












