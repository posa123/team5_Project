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
import model.dto.TradeLog;


@WebServlet("/TradelogManagement")
public class TradelogManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TradelogManagement() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		String pDate = request.getParameter("pDate");
		String nDate = request.getParameter("nDate");
		
		List<TradeLog> result = ManagerDao.getInstance().getTradeLog(key, keyword, pDate, nDate);
		System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = objectMapper.writeValueAsString(result);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonArray);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
