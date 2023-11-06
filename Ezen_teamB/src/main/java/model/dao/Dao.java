package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dao {
	
	public Connection conn;			// DB연동 객체
	public PreparedStatement ps; 	// 연동된 DB에서 SQL조작( SQL매개변수대입, SQL실행/취소 ) 하는 객체 
	public ResultSet rs;			// SQL 조작 결과(검색결과)를 가져오는 객체 
	
	public Dao() {
		try { 


			/*
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  
			  
			  
			  this.conn = DriverManager.getConnection(
			 "jdbc:mysql://database-1.ccmmlpcfyswe.us-east-1.rds.amazonaws.com:3306/usedtrade"
			  , "admin", "12341234a");
			*/
			  



			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			
			
			this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/usedtrade"
					, "root", "1234");
			

	
			
			System.out.println("안내] DB연동성공");
		}catch (Exception e) {System.out.println("경고] DB연동성공실패 : "+e);}
	}
	
	
}