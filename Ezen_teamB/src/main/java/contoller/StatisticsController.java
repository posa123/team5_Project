package contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import model.dao.ManagerDao;
import model.dto.AgeStatistic;
import model.dto.AreaStatistic;
import model.dto.CateStatistics;
import model.dto.TradeStatistic;

@WebServlet("/StatisticsController")
public class StatisticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StatisticsController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category = request.getParameter("category");	System.out.println("category : " +  category);
		int maxSize = Integer.parseInt(request.getParameter("maxSize"));
		int page = Integer.parseInt(request.getParameter("page"));
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = "";
		String pDate = request.getParameter("pDate");	System.out.println("pDate : " + pDate);
		String nDate = request.getParameter("nDate");	System.out.println("nDate : " + nDate);
		
		// 카테고리별 통계
		if(category.equals("1")) {
			
			List<CateStatistics> result = ManagerDao.getInstance().getCateStatistics(pDate, nDate);
			jsonArray = objectMapper.writeValueAsString(result);
		}
		// 거래방식별 통계
		else if(category.equals("2")) {
			
			List<TradeStatistic> result =  ManagerDao.getInstance().getTradeStatistics(pDate, nDate);
			jsonArray = objectMapper.writeValueAsString(result);
		}
		// 연령대별 통계
		else if(category.equals("3")) {
			
			List<AgeStatistic> result = ManagerDao.getInstance().getAgeStatistics(pDate, nDate);
			jsonArray = objectMapper.writeValueAsString(result);
		}
		// 지역별 통계
		else if(category.equals("4")) {
			
			List<AreaStatistic> result = ManagerDao.getInstance().getAreaStatistics(pDate, nDate);
			System.out.println(result);
			jsonArray = objectMapper.writeValueAsString(result);
		}
		
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(jsonArray);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}