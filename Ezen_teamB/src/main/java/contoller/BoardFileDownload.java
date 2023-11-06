package contoller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownload
 */
@WebServlet("/BoardFileDownload")
public class BoardFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardFileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	// 게시물 파일 다운로드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 1. 파일명	
		String filename = request.getParameter("filename");
	
			// 2. 첨부파일 경로
		String uploadpath = request.getServletContext().getRealPath("/jsp/board/upload");
			// 3. 다운로드 할 파일의 전체 경로
		String filepath = uploadpath+"/"+filename;
	
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filepath,"UTF-8"));
		
			File file = new File(filepath);
			// 1. 
		
			
			FileInputStream fin = new FileInputStream(file);
			// 2. 
		
			byte[] bytes = new byte[(int)file.length()];
			// 3. 
			fin.read(bytes);
			
		
			BufferedOutputStream oin = new BufferedOutputStream(response.getOutputStream());
			oin.write(bytes);
		fin.close(); oin.flush(); oin.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
