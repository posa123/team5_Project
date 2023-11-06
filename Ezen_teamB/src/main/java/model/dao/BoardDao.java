package model.dao;

import java.util.ArrayList;

import model.dto.Board;
import model.dto.ReplyDto;

// 게시판 클래스
public class BoardDao extends Dao{
	
	private static BoardDao boardDao = new BoardDao();
	public static BoardDao getInstance() {
		return boardDao;
	}
	private BoardDao() {}
	
	
	// 게시물 수 출력
	public int getTotalSize( int cno , String key , String keyword ) {
		
		try {
			String sql = "select count(*) from board b natural join memberlist m";
			
			if( cno != 0 ) { sql +=" where b.cno = " +cno;}
			
			if( !key.isEmpty() && !keyword.isEmpty()) {
				if( cno != 0 ) sql += " and ";
				else sql += "where";
				
				sql +=" "+key+" like '%"+keyword+"%' "; 
			}
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() )return rs.getInt(1);
		} catch (Exception e) { System.out.println(e); }
		
		return 0;
	}
	
	
	
	// 전체 게시물 출력
	public ArrayList<Board> getList( int cno , int listsize , int starrow , String key , String keyword){
		ArrayList<Board> list = new ArrayList<>();
		try {
			String sql = "select b.* , m.mid , c.cname from board b natural join category c natural join memberlist m ";
			// 카테고리 선택
			if(cno != 0 ) { sql += " where b.cno = " +cno;}
			// 검색
			if(!key.isEmpty() && !keyword.isEmpty() ) {
				// 카테고리내 검색이면
				if( cno != 0 ) sql+=" and ";
				else sql += " where ";
				
				sql += " "+key+" like '%"+keyword+"%' ";
			}
			
			sql += " order by b.bdate desc limit ? , ?";
			
			ps=conn.prepareStatement(sql);
			ps.setInt(1, starrow);
			ps.setInt(2,listsize);
			rs = ps.executeQuery();
			while(rs.next()) {
				Board boardDto = new Board(rs.getInt("cno"), 
						rs.getString("cname"), rs.getInt("bno"), 
						rs.getString("btitle"), rs.getString("bcontent"), 
						rs.getString("bdate"), rs.getString("bfile"), 
						rs.getInt("mno"), rs.getString("mid"));
				list.add(boardDto);
			}
		} catch (Exception e) {System.out.println(e);}
		return list;
	}	
	
	// 개별 게시물 출력
	public Board getBoard( int bno ) {
		try {
			String sql = "select b.* , m.mid , c.cname from board b natural join memberlist m natural join category c where b.bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			if(rs.next()) {
				Board boardDto = new Board(
						rs.getInt("bno"),
						rs.getString("btitle") , rs.getString("bcontent") ,
						rs.getString("bfile") , rs.getString("bdate"),
						rs.getInt("mno"),rs.getInt("cno"),
						rs.getString("mid") , rs.getString("cname")
	
						);
			return boardDto;
			}
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 개별 게시물 등록
	public boolean bwrite( Board boardDto ) {
		try {
			System.out.println(boardDto);
			String sql ="insert into board( btitle , bcontent , bfile , mno , cno ) "+"values( ? , ? , ? , ? , ? )";
			ps=conn.prepareStatement(sql);
			ps.setString(1, boardDto.getBtitle());
			ps.setString(2, boardDto.getBcontent());
			ps.setString(3, boardDto.getBfile());
			ps.setInt(4, boardDto.getMno());
			ps.setInt(5, boardDto.getCno());
			
			int count = ps.executeUpdate(); if( count == 1 ) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 개별 게시물 수정
	public boolean bUpdate( Board dto ) {
		try {
		
			
			String sql = "update board set btitle = ? , bcontent = ? , cno = ? ,bfile = ? where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBtitle());
			ps.setString(2, dto.getBcontent());
			ps.setInt(3, dto.getCno());
			ps.setString(4, dto.getBfile());
			ps.setInt(5, dto.getBno());
			int count = ps.executeUpdate();
			if( count == 1) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 개별 게시물 삭제
	public boolean bDelete(int bno) {
		try {
			String sql = "delete from board where bno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			int count = ps.executeUpdate();
			if(count == 1) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 개별 게시물 답글 등록
	public boolean bReply( ReplyDto dto ) {
		try {
			String sql = "insert into reply(bno,mno,rno,rcontent) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dto.getBno());
			ps.setInt(2, dto.getMno());
			ps.setInt(3,dto.getRno());
			ps.setString(4,dto.getRcontent());
			int count = ps.executeUpdate(); if( count == 1 ) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 개별 게시물 답글 출력
	public ArrayList<ReplyDto> getReply( int bno ){
		ArrayList<ReplyDto> list = new ArrayList<>();
		try {
			String sql = "select m.mid, r.* from reply r join memberlist m on r.mno = m.mno where bno = "+bno+" order by rdate desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDto replyDto = new ReplyDto(
						rs.getInt("rno"),rs.getInt("mno"),
						rs.getInt("bno"),rs.getString("rdate"),
						rs.getString("rcontent"),rs.getString("mid"));
				list.add(replyDto);
			}
		} catch (Exception e) {System.out.println(e);}
		return list;
	}
	// 개별 게시물 답글 수정
	public boolean rUpdate( ReplyDto dto) {
		try {
			String sql = "update reply set rcontent =? where rno =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,dto.getRcontent());
			ps.setInt(2,dto.getRno());
			int count = ps.executeUpdate();
			if(count == 1 ) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 개별 게시물 답글 삭제
	public boolean rDelete( int rno ) {
		try {
			String sql = "delete from reply where rno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			int count = ps.executeUpdate();
			if(count == 1) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
		
	}
}
