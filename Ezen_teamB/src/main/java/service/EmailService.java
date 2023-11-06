package service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	
	// 필드
	private String fromEmail = "gaji4948@naver.com";
	private String fromEmailPwd = "!rkwlek1212";
	
	public EmailService() {}
	
	// 이메일 보내기 함수
	
	public boolean send(String email ,String contentHTML) {
		
		// 호스팅 설정 // 네이버
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", "smtp.naver.com");		
		properties.put("mail.smtp.port", 587);					
		properties.put("mail.smtp.auth", true);					
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");		
		
		Authenticator authenticator = new Authenticator() {
			
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
			
				return new PasswordAuthentication(fromEmail, fromEmailPwd);
			}			

		};
		
		// 인증처리
		
		Session session = Session.getDefaultInstance(properties,authenticator);
		
		
		try {
			
			// 객체 생성
			MimeMessage message = new MimeMessage(session);
			// 보내는 사람 이메일 주소
			message.setFrom(new InternetAddress(fromEmail));
			// 받는 사람 이메일
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			// 이메일 제목
			message.setSubject("가지가지 커뮤니티 회원가입 인증 코드 입니다.");
			
			message.setText("회원가입 인증 코드 : " + contentHTML);
			
			Transport.send(message);
			return true;
			
			
		} catch (Exception e) {
			System.out.println("이메일보내기 서비스 오류 : " + e);
		}
		return false;
		
	}

}
