package contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ManagerDao;
import model.dto.Emediation;


@WebServlet("/EmediationController")
public class EmediationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EmediationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Emediation> result = ManagerDao.getInstance().printEmediation();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = objectMapper.writeValueAsString(result);
		
		response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(jsonArray);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eType = request.getParameter("eType");
		String itradeplace = request.getParameter("itradeplace");
		String dlat = request.getParameter("dlat");
		String dlng = request.getParameter("dlng");
		
		boolean result = ManagerDao.getInstance().insertEmediation(eType, itradeplace, dlat, dlng);
		response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(result);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int eno = Integer.parseInt(request.getParameter("eno"));
		String eType = request.getParameter("eType");
		String itradeplace = request.getParameter("itradeplace");
		String dlat = request.getParameter("dlat");
		String dlng = request.getParameter("dlng");
		
		boolean result = ManagerDao.getInstance().updateEmediation(eno, eType, itradeplace, dlat, dlng);
		response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(result);	
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int eno = Integer.parseInt(request.getParameter("eno"));
		
		boolean result = ManagerDao.getInstance().deleteEmediation(eno);
		response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(result);	
	}

}
