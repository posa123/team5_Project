package contoller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberDao;
import model.dto.MemberList;
import model.dto.TempPwd;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SearchController() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		String mname = request.getParameter("mname");
		String mphone = request.getParameter("mphone");
		
		String mid = request.getParameter("mid");
		String memail = request.getParameter("memail");
		
		
		String json="";
		ObjectMapper mapper = new ObjectMapper();
		
		if(type.equals("id")) {
			
			MemberList dto = new MemberList(mname, mphone);
			MemberList result = MemberDao.getInstance().idSearch(dto);
			
			if (result == null) {
				String mid2 = "null";
				json = mapper.writeValueAsString(mid2);
			}else if(result != null) {
				
				
				String mid2 = String.valueOf(result.getMid());
				json = mapper.writeValueAsString(mid2);
		
			}
			
		}else if(type.equals("pwd")) {
			
			TempPwd dto = new TempPwd(mid , memail);
			System.out.println("비밀번호찾기 mid : " + mid);
			System.out.println("비밀번호찾기 memail : " + memail);
			System.out.println("비밀번호찾기 dto : " + dto);
			TempPwd result = MemberDao.getInstance().pwdSearch(dto);
				
			System.out.println("비밀번호 찾기 컨트롤러 가져온 비밀번호 : " + result);
			
			if (result == null) {
				String mpwd = "null";
				json = mapper.writeValueAsString(mpwd);
			}else if(result != null) {
				
	
				
				int ranpwd = (int) (Math.random() * 10000) + 99999;
				System.out.println("임시비밀번호 만들고 :" +ranpwd);
				String temp = Integer.toString(ranpwd);
				
				Boolean temppwd = MemberDao.getInstance().tempPwd(temp , mid);
				
				if (temppwd == true) {					
					int tpwd = ranpwd;
					System.out.println("임시비밀번호 : " +temppwd);
					json = mapper.writeValueAsString(tpwd);
				}
				
				
		
			}
		
		}
		System.out.println("반환할때 json " +json);		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
	
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
