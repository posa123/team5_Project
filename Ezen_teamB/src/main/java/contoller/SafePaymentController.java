package contoller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ItemDao;
import model.dao.SafePaymentDao;
import model.dto.MemberList;
import model.dto.SafePaymentDto;
import model.dto.SafepayPageDto;

// 안전결제 Controller
@WebServlet("/SafePaymentController")
public class SafePaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public SafePaymentController() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		String type = request.getParameter("type");
		
		// 안전결제 구매관리 조회
		if( type.equals("getBuyerManage") || type.equals("getSellerManage") ) {
			
			int vrequester = ((MemberList)request.getSession().getAttribute("loginSession")).getMno();
			int maxSize = Integer.parseInt( request.getParameter("maxSize") );
			int page = Integer.parseInt( request.getParameter("page") );
			int vstateFilter = Integer.parseInt(request.getParameter("vstateFilter"));
			
			
			// 페이지 레코드 시작번호 ( 0, 11, 22 ... )
			int startRow = (page-1) * maxSize;
			// 안전거래 총 진행현황 건수
			int totalPaymentCount = 0;
			
			// 구매관리일 경우
			if( type.equals("getBuyerManage") ) {
				totalPaymentCount = SafePaymentDao.getInstance().getBuyerTotalCount(vstateFilter, vrequester);
			}
			// 판매관리일 경우
			if( type.equals("getSellerManage") ) {
				totalPaymentCount = SafePaymentDao.getInstance().getSellerTotalCount(vstateFilter, vrequester);
			}
			
			
			// 마지막 페이지 번호 == 총 페이지 수
			int totalPageCount = 
					totalPaymentCount%maxSize == 0 
					? totalPaymentCount/maxSize 
					: totalPaymentCount/maxSize+1;
			
			// 페이지버튼 번호의 최대개수
			int btnsize = 5;
			// 페이지버튼 번호의 시작번호
			int startBtn = ((page-1)/btnsize)*btnsize+1;
			// 페이지버튼 번호의 마지막 번호
			int endBtn = startBtn+btnsize;
			if( endBtn >= totalPageCount ) endBtn = totalPageCount;
			
			
			ArrayList<SafePaymentDto> result = null;
			
			// 구매관리일 경우
			if( type.equals("getBuyerManage") ) {
				result = SafePaymentDao.getInstance().getBuyerPaymentList(maxSize, startRow, vstateFilter, vrequester);
			}
			// 판매관리일 경우
			if( type.equals("getSellerManage") ) {
				result = SafePaymentDao.getInstance().getSellerPaymentList(maxSize, startRow, vstateFilter, vrequester);
			}
			
			
			SafepayPageDto safepayPageDto = new SafepayPageDto(
					page, maxSize, startRow, totalPaymentCount, totalPageCount, startBtn, endBtn, result
			);
			
			json = mapper.writeValueAsString( safepayPageDto );
			
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		
		String type = request.getParameter("type");
		
		// 구매자 pk mno
		int vrequester = ((MemberList)request.getSession().getAttribute("loginSession")).getMno();
		
		// 안전결제 요청 단계
		if( type.equals("responseSafepay") ) {
			
			int ino = Integer.parseInt( request.getParameter("ino") );
			int result = SafePaymentDao.getInstance().responseSafepay( vrequester, ino );
			
			json = mapper.writeValueAsString(result);
			
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );
		
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		
		boolean result = false;
		
		// 판매자 요청 수락
		if( type.equals("acceptSafepay") ) {
			
			int vno = Integer.parseInt( request.getParameter("vno") );
			int ino = Integer.parseInt( request.getParameter("ino") );
			int vrequester = Integer.parseInt( request.getParameter("vrequester") );
			
			result = SafePaymentDao.getInstance().acceptSafepay( vno, ino, vrequester );
			
		}
		// 판매자 물품 전달
		if (type.equals("deliverySafepay")) {
			int vno = Integer.parseInt( request.getParameter("vno") );
			
			result = SafePaymentDao.getInstance().deliverySafepay( vno );
		}
		// 구매자 수령확정
		if (type.equals("checkItem")) {
			
			int vno = Integer.parseInt( request.getParameter("vno") );
			int ino = Integer.parseInt( request.getParameter("ino") );
			int vrequester = Integer.parseInt( request.getParameter("vrequester") );
			
			result = SafePaymentDao.getInstance().checkItem( vno, ino, vrequester );
			
		}
		if (type.equals("completeItem")) {
			
			int ino = Integer.parseInt( request.getParameter("ino") );
			
			result = ItemDao.getInstance().changeItemState(ino);
			
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( result );
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 안전결제 취소
		int vno = Integer.parseInt( request.getParameter("vno") );
		int ino = Integer.parseInt( request.getParameter("ino") );
		int vrequester = Integer.parseInt( request.getParameter("vrequester") );
		int vstate = Integer.parseInt( request.getParameter("vstate") );
		
		boolean result = SafePaymentDao.getInstance().deleteSafepay( vno, ino, vrequester, vstate );
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( result );
		
	}

}






















