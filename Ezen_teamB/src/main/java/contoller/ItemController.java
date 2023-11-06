package contoller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.ChattingDao;
import model.dao.ItemDao;
import model.dto.CategoryDto;
import model.dto.DetailedItems;
import model.dto.DpointDto;
import model.dto.Emediation;
import model.dto.FaceToFaceDto;
import model.dto.ItemsInfo;
import model.dto.MemberList;


@WebServlet("/ItemController")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ItemController() {
        super();
    }

    // 제품 정보 가져오기
    // 중개거래소 가져오기
    // 카테고리 가져오기
    // 채팅방번호 가져오기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		
		// 1. 물품 등록 시 카테고리 대분류 반환
		if( type.equals("getMainCategory") ) {
			
			ArrayList<CategoryDto> categoryList = ItemDao.getInstance().getMainCategory();
			json = mapper.writeValueAsString(categoryList);
			
		}
		// 2. 대분류 선택 시 대분류에 대한 카테고리 소분류 반환 
		else if ( type.equals("getSubCategory") ) {
			
			int uno = Integer.parseInt(request.getParameter("uno"));
			ArrayList<CategoryDto> categoryList = ItemDao.getInstance().getSubCategory( uno );
			json = mapper.writeValueAsString(categoryList);
			
		}
		// 3. 중개거래소 가져오기
		else if( type.equals("getEmediation") ) {
			
			ArrayList<Emediation> emediation = ItemDao.getInstance().getEmediation();
			json = mapper.writeValueAsString(emediation);
			
		}
		// 4. 전체 물품 조회
		else if( type.equals("getItemList") ) {
			
			String filterCategory = request.getParameter("filterCategory");
			int filterNum = Integer.parseInt( request.getParameter("filterNum") );
			String searchWord = request.getParameter("searchWord");

			ArrayList<ItemsInfo> itemsInfo = ItemDao.getInstance().getItemList( filterCategory, filterNum, searchWord );
			
			json = mapper.writeValueAsString(itemsInfo);
			
		}
		// 5. 개별 물품 조회
		else if( type.equals("getDetailedItems") ) {
			
			int ino = Integer.parseInt( request.getParameter("ino") );
			int itrade = Integer.parseInt( request.getParameter("itrade") );
			
			DetailedItems detailedItems = ItemDao.getInstance().getDetailedItems( ino, itrade );
			
			json = mapper.writeValueAsString( detailedItems );
			
		}
		// 6. 기등록된 물품에 대한 정보 불러오기
		else if( type.equals("getDetailedItems") ) {
			
			int ino = Integer.parseInt( request.getParameter("ino") );

		}
	 	
		// 7. 채팅방번호 가져오기
		else if(type.equals("getChatRno")) {
			
			int cmno = Integer.parseInt( request.getParameter("mno") );
			int ino = Integer.parseInt( request.getParameter("ino") );
			int rmno = ChattingDao.getInstance().ifindMno(ino);
			String rno = UUID.randomUUID().toString();
			
			String result = ChattingDao.getInstance().findRno(cmno, rmno);
			String result2 = ChattingDao.getInstance().findRno(rmno, cmno);
			
			if(result == null && result2 == null) {
				json = mapper.writeValueAsString(rno);
			}else {
				if(result == null) {
					json = mapper.writeValueAsString(result2);
				}else {
					json = mapper.writeValueAsString(result);
				}

			}
		}
		
		// 8. 인덱스 대면거래 아이템리스트 조회
		else if(type.equals("getIndexItemList")) {
			
			String filterCategory = request.getParameter("filterCategory");
			int filterNum = Integer.parseInt( request.getParameter("filterNum") );

			ArrayList<FaceToFaceDto> itemsInfo = ItemDao.getInstance().getIndexItemList( filterCategory, filterNum );
			
			json = mapper.writeValueAsString(itemsInfo);
			
		}
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );
		
	}	

	// 제품 등록하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/* 로그인 기능 구현 전 임시적으로 로그인 세션저장 */

		/* ---------------------------------- */
		
		
		/* ------------ 파일 업로드 setting ------------ */
			
		// 1. 저장경로 [ 첨부파일이 저장될 폴더 위치 ] 
		String uploadPath = request.getServletContext().getRealPath("/jsp/item/img");
		
		// 2. 파일아이템저장소 객체 : 업로드할 옵션  [ import org.apache.commons.fileupload.FileItem; ]
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		itemFactory.setRepository( new File( uploadPath ) );	//  2.저장위치 [ File타입 ] 
		itemFactory.setSizeThreshold( 1024 * 1024 * 1024 ); 	//  3.용량
		itemFactory.setDefaultCharset("UTF-8");					//  4.한글인코딩
		 
		/* ------------------------------------------ */
			
			
		// 3. 파일 업로드 객체 [ import org.apache.commons.fileupload.servlet.ServletFileUpload; ] 
		ServletFileUpload fileUpload = new ServletFileUpload( itemFactory );
		 
		// 입력받은 물품 등록 정보
		int ino = -1;				// 판매물품번호 pk	( dao에서 대면거래 장소를 DB에 저장하기 위해 필요 )
		int mno = ((MemberList)request.getSession().getAttribute("loginSession")).getMno();
		int iprice = -1;			// 가격
		String ititle = "";			// 제목
		String icontent = "";		// 내용
		int itrade = -1;			// 거래방식	( 1 배송, 2 대면거래, 3 중개거래 )
		String itradeplace = "";	// 거래장소
		int eno = -1;				// 중개거래소 pk
		int isafepayment = 0;		// 안전결제 사용여부 [ 안전결제 여부 미사용일시 form객체가 생성되지 않기에 기본값 '0'으로 설정 ]
		int dno = -1;				// 소분류 카테고리 pk
		String dlat = "";			// 대면거래 위도
		String dlng = "";			// 대면거래 경도
		
		Map< Integer , String > imgList = new HashMap<>(); // 업로드된 파일명들을 저장하기 위한 map컬렉션
		
		try {
			
			List< FileItem > fileList = fileUpload.parseRequest( request );
			
			int i=0;	// imgList에 key값으로 활용 ( 증감 연산 )
			for( FileItem item : fileList ) {

				if( item.isFormField() ) { // 일반 form객체일 경우
					
					switch( item.getFieldName() ) {
						
						case "iprice" 		: iprice = Integer.parseInt(item.getString()); break;
						case "ititle" 		: ititle = item.getString(); break;
						case "icontent" 	: icontent = item.getString(); break;
						case "itrade" 		: itrade = Integer.parseInt(item.getString()); break;
						case "itradeplace" 	: itradeplace = item.getString(); break;
						case "eno" 			: eno = Integer.parseInt(item.getString()); break;
						case "isafepayment" : isafepayment = 1; break;
						case "dno" 			: dno = Integer.parseInt(item.getString()); break;
						case "dlat" 		: dlat = item.getString(); break;
						case "dlng" 		: dlng = item.getString(); break;
						
					}
					
				} else {	// 파일 객체일 경우
										
					UUID uuid = UUID.randomUUID();
					String filename = uuid+"-"+item.getName().replaceAll("-", "_");

					File fileUploadPath = new File( uploadPath +"/"+filename );

					// .write("저장할경로/파일명포함") 파일 업로드할 경로를 file타입으로 제공 
					item.write( fileUploadPath );

					// 업로드된 파일명을 Map에 저장
					imgList.put( i++ , filename ); // 저장시에는 이미지번호가 필요 없음

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// icontent 상 줄바꿈 처리를 위해 치환
		icontent = icontent.replaceAll( "\n", "<br>" );
		
		// 거래방식에 따라 별도 데이터 처리
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		boolean result = false;
		
		// 물품정보 생성자
		ItemsInfo itemsInfo = new ItemsInfo(
			ino, iprice, mno, ititle, icontent, itrade, itradeplace, eno, dno, isafepayment, imgList
		);
		
		// 거래방식이 대면거래일 경우 대면거래 위치생성자 매개값으로 전달
		if( itrade == 2 ) {
			DpointDto dpointDto = new DpointDto( dlat, dlng );
			result = ItemDao.getInstance().uploadItem( itemsInfo, dpointDto );
		} else {
			result = ItemDao.getInstance().uploadItem( itemsInfo, null );
		}
		
		json = mapper.writeValueAsString(result);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( json );
		
	} 

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/* ------------ 파일 업로드 setting ------------ */
		// 이미지 파일은 수정이 아닌 추가 대상이므로 insert
		
		// 1. 저장경로 [ 첨부파일이 저장될 폴더 위치 ] 
		String uploadPath = request.getServletContext().getRealPath("/jsp/item/img");
		
		// 2. 파일아이템저장소 객체 : 업로드할 옵션  [ import org.apache.commons.fileupload.FileItem; ]
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		itemFactory.setRepository( new File( uploadPath ) );	//  2.저장위치 [ File타입 ] 
		itemFactory.setSizeThreshold( 1024 * 1024 * 1024 ); 	//  3.용량
		itemFactory.setDefaultCharset("UTF-8");					//  4.한글인코딩
		 
		/* ------------------------------------------ */
		
		
		// 3. 파일 업로드 객체 [ import
		// org.apache.commons.fileupload.servlet.ServletFileUpload; ]
		ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);

		// 입력받은 물품 등록 정보
		int ino = -1; // 판매물품번호 pk ( dao에서 대면거래 장소를 DB에 저장하기 위해 필요 )
		int mno = ((MemberList) request.getSession().getAttribute("loginSession")).getMno();
		int iprice = -1; // 가격
		String ititle = ""; // 제목
		String icontent = ""; // 내용
		int itrade = -1; // 거래방식 ( 1 배송, 2 대면거래, 3 중개거래 )
		String itradeplace = ""; // 거래장소
		int eno = -1; // 중개거래소 pk
		int isafepayment = 0; // 안전결제 사용여부 [ 안전결제 여부 미사용일시 form객체가 생성되지 않기에 기본값 '0'으로 설정 ]
		int dno = -1; // 소분류 카테고리 pk
		String dlat = ""; // 대면거래 위도
		String dlng = ""; // 대면거래 경도

		Map<Integer, String> imgList = new HashMap<>(); // 업로드된 파일명들을 저장하기 위한 map컬렉션

		try {

			List<FileItem> fileList = fileUpload.parseRequest(request);

			int i = 0; // imgList에 key값으로 활용 ( 증감 연산 )
			for (FileItem item : fileList) {

				if (item.isFormField()) { // 일반 form객체일 경우

					switch (item.getFieldName()) {
						
						case "ino" 			: ino = Integer.parseInt(item.getString()); 	break;
						case "iprice" 		: iprice = Integer.parseInt(item.getString()); 	break;
						case "ititle" 		: ititle = item.getString(); 					break;
						case "icontent" 	: icontent = item.getString(); 					break;
						case "itrade" 		: itrade = Integer.parseInt(item.getString()); 	break;
						case "itradeplace" 	: itradeplace = item.getString(); 				break;
						case "eno" 			: eno = Integer.parseInt(item.getString()); 	break;
						case "isafepayment" : isafepayment = 1; 							break;
						case "dno" 			: dno = Integer.parseInt(item.getString()); 	break;
						case "dlat" 		: dlat = item.getString(); 						break;
						case "dlng" 		: dlng = item.getString(); 						break;

					}

				} else { // 파일 객체일 경우

					UUID uuid = UUID.randomUUID();
					String filename = uuid + "-" + item.getName().replaceAll("-", "_");

					File fileUploadPath = new File(uploadPath + "/" + filename);

					// .write("저장할경로/파일명포함") 파일 업로드할 경로를 file타입으로 제공
					item.write(fileUploadPath);

					// 업로드된 파일명을 Map에 저장
					imgList.put(i++, filename); // 저장시에는 이미지번호가 필요 없음

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		// icontent 상 줄바꿈 처리를 위해 치환
		icontent = icontent.replaceAll( "\n", "<br>" );
		
		// 거래방식에 따라 별도 데이터 처리
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		boolean result = false;

		// 물품정보 생성자
		ItemsInfo itemsInfo = new ItemsInfo(
			ino, iprice, mno, ititle, 
			icontent, itrade, itradeplace, 
			eno, dno, isafepayment, imgList
		);

		
		// 거래방식이 대면거래일 경우 대면거래 위치생성자 매개값으로 전달
		if (itrade == 2) {
			DpointDto dpointDto = new DpointDto(dlat, dlng);
			result = ItemDao.getInstance().updateItem(itemsInfo, dpointDto);
		} else {
			result = ItemDao.getInstance().updateItem(itemsInfo, null);
		}

		json = mapper.writeValueAsString(result);

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
		
		
		
		
		
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		boolean result = false;
		
		// 판매 물품 삭제
		if( type.equals("deleteItem") ) {
		
			int ino = Integer.parseInt(request.getParameter("ino"));
			
			result = ItemDao.getInstance().deleteItem( ino );
			
		}
		// 물품 수정시 기존 이미지 DB에서 삭제하기		
		if( type.equals("deleteExistingImg") ) {
			
			int ino = Integer.parseInt(request.getParameter("ino"));
			String pimg = request.getParameter("pimg");
			
			result = ItemDao.getInstance().deleteExistingImg( ino, pimg );
			
		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print( result );
		
		
		
	}

}














