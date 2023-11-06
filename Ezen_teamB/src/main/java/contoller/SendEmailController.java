package contoller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmailService;

/**
 * Servlet implementation class SendEmailController
 */
@WebServlet("/SendEmailController")
public class SendEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email"); System.out.println(email);

		//인증번호 생성		
		String Auth = "";
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			Auth += random.nextInt(10);		//0부터 9 까지	
		}System.out.println(Auth);

		//이메일보내기
		EmailService emailService = new EmailService();
		emailService.send(email,Auth);
		
		// 인증번호 반환
		response.getWriter().print(Auth);		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
