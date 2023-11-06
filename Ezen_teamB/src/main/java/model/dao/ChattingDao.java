package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dto.MsgDto;
import model.dto.MsgRoom;

// 채팅기능 클래스
public class ChattingDao extends Dao{
	
	private static ChattingDao chattingDao = new ChattingDao();
	public static ChattingDao getInstance() {
		return chattingDao;
	}
	private ChattingDao() {}
	
	// 메시지 받는 사람의 mid또는 mno를 구하는 메소드(첫 메시지 수신자 mid)
	public String findRMid(String type, int ino) {
		String sql = "";
		
		try {
			if(type.equals("1")) {
				sql = "select m.mid from itemsinfo i, memberlist m where i.mno = m.mno and i.ino = " + ino;	
			}
			else if(type.equals("2")){
				sql = "select m.mid from jchatting j, memberlist m where j.caller = m.mno and ino = " + ino;
			}
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {System.out.println("findRMid 오류 : " + e);}
		
		return null;
	}
	
	// 아이디로 회원넘버를 구하는 메소드
	public int findMno(String mid) {
		
		try {
			String sql = "select mno from memberlist where mid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {System.out.println("findMno 오류 : " + e);}
		
		return 0;
	}
	
	// 메시지를 보낼때마다 DB에 저장
	public boolean recordMsg(int cmno, int rmno, String msg, int ino, String rno) {
		
		try {
			String sql = "insert into jchatting(caller, receiver,jcontent,ino,rno) values(?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cmno);	ps.setInt(2, rmno);
			ps.setString(3, msg);	ps.setInt(4, ino);
			ps.setString(5, rno);
			int count = ps.executeUpdate();
			if(count == 1) {return true;}
		
		} catch (Exception e) {System.out.println("recordMsg 오류" + e);}

		return false;
	}
	
	// 판매자의 회원번호를 가져오는 메소드
	public int ifindMno(int ino) {
		
		try {
			String sql = "select mno from itemsinfo where ino = " + ino;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {System.out.println("ifindMno 오류 : " + e);}
		
		return 0;
	}
	
	
	// 채팅방 번호를 가져오는 메소드
	public String findRno(int cmno, int rmno) {
		
		try {
			String sql = "select rno from jchatting where caller = ? and receiver = ?";
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cmno);
			ps.setInt(2, rmno);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {System.out.println("findRno 오류 : " + e);}
		
		return null;
	}
	
	// 채팅한 텍스트들을 가져오는 메소드
	public List<MsgDto> getMsgs(String rno){
		
		List<MsgDto> list = new ArrayList<MsgDto>();
		
		try {
			String sql = "select m.mid, jcontent, jchatdate, ino from jchatting j, memberlist m  where j.caller = m.mno and rno = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MsgDto mDto = new MsgDto(
						rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
				list.add(mDto);
			}
			return list;
			
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
	// 자신의 채팅방 목록을 가져오는 메소드
	public List<MsgRoom> getMsgRoom(int mno){
		
		List<MsgRoom> list = new ArrayList<>();
		
		try {
			String sql = "select*from ("
					+ "   select*from jchatting "
					+ "	where (rno, jchatdate) in( select rno, max(jchatdate) as date_time "
					+ "		from jchatting group by rno "
					+ "		) order by jchatdate desc "
					+ "	) jchat_desc "
					+ "where caller = ? or receiver = ? "
					+ "group by rno,jno";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, mno);
			rs = ps.executeQuery();
			while(rs.next()) {
				MsgRoom mdto = new MsgRoom(
						rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6),
						rs.getInt(7), rs.getString(8));
				list.add(mdto);
			}
			return list;
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}	// m end
	
	// 채팅방 나가기 함수
	public boolean outMsgRoom(String rno) {
		
		System.out.println(rno);
		
		try {
			String sql = "delete from jchatting where rno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rno);
			System.out.println(sql);
			int count = ps.executeUpdate();
			if(count == 1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println("채팅방 나가기 오류 : " + e);}
		
		return false;
	}
	
	
	
}























