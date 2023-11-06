package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dto.ItemsInfo;
import model.dto.Mymenu;

public class MyMenuDao extends Dao{
	
	private static MyMenuDao myMenuDao = new MyMenuDao();
	public static MyMenuDao getInstance() {
		return myMenuDao;
	}
	private MyMenuDao() {}	
	
	
	// 내 정보 출력
	public Mymenu printMemberInfo(int mno){
		
		Mymenu mDto = new Mymenu();
		
		try {
			String sql = "SELECT m.mid, m.mlevel, COUNT(i.ino) AS 판매물품, m.mpoint, m.mname, m.madress, m.memail\r\n"
					+ "FROM memberlist m\r\n"
					+ "INNER JOIN itemsinfo i ON m.mno = i.mno\r\n"
					+ "WHERE m.mno = "+mno+" AND (i.iestate = 0 OR i.iestate = 1)\r\n"
					+ "GROUP BY m.mno;";
			ps = conn.prepareStatement(sql);
			
			/* ps.setInt(1, mno); ps.setInt(2, mno); */
			 
			rs = ps.executeQuery();
			if(rs.next()) {
				mDto = new Mymenu(
						rs.getString(1), rs.getInt(2), 0,
						rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}
			System.out.println(mDto);
			return mDto;
			
		} catch (Exception e) {System.out.println("내 정보 출력함수 오류 " + e);}
		
		
		return null;
	}
	
	// 거래활동수 구하는 함수
	public int tradeCount(int mno) {
		
		try {
			String sql = "select count(t.tno) as 거래내역, i.mno "
					+ "from itemsinfo i, tradelog t "
					+ "where i.ino = t.ino "
					+ "group by i.ino "
					+ "having i.mno = " + mno;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {System.out.println("거래활동함수 오류" + e);}
		
		return 0;
		
	}
	
	
	// 판매물품에 해당하는 이미지 출력하는 함수
	public Map<Integer, String> getProductImg(int ino){
		try {
			Map<Integer, String> imglist = new HashMap<>();	// 제품별 여러개 이미지
			String sql = "select*from pimg where ino = " + ino;
			PreparedStatement ps = conn.prepareStatement(sql);	// 다른함수에서 rs를 사용하기 때문에 새로 만듬
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				imglist.put(rs.getInt("pno"),rs.getString("pimg"));
			}
			return imglist;
			
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 판매중인 물품 출력 / 거래내역 출력
	public List<ItemsInfo> saleList(int mno, int estate){
		
		List<ItemsInfo> list = new ArrayList<>();
		
		try {
			String sql = "select i.*, uc.uno, uc.uname, dc.dname from itemsinfo i natural join umaincategory uc natural join dsubcategory dc where i.mno = ? and ";
			if(estate == 1) {
				sql += "i.iestate = 0";
			}else if(estate == 2) {
				sql += "i.iestate = 1";
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			rs = ps.executeQuery();
			while(rs.next()) {
				ItemsInfo idto = new ItemsInfo(
						rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getString(4), rs.getString(5), rs.getInt(6),
						rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getInt(10), rs.getInt(11), rs.getInt(12),
						rs.getInt(13), rs.getInt(14), rs.getString(15),
						rs.getString(16), getProductImg(rs.getInt("ino")));
				list.add(idto);
			}
			return list;
			
		} catch (Exception e) {System.out.println("판매중인 물품 오류 : " + e);}
		
		return null;
	}
	
	// 찜 목록 출력 함수
	public List<ItemsInfo> printWishList(int mno){
		
		List<ItemsInfo> list = new ArrayList<>();
		
		try {
			String sql = "select i.ino, w.mno, i.ititle,i.itrade,i.idate, uc.uname, dc.dname "
					+ "from itemsinfo i, umaincategory uc, dsubcategory dc, watchitem w "
					+ "where i.ino = w.ino and i.dno = dc.dno and uc.uno = dc.uno and i.iestate = 0 and w.mno = " + mno;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				ItemsInfo idto = new ItemsInfo(
						rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), getProductImg(rs.getInt("ino")));
				list.add(idto);
			}
			return list;
			
		} catch (Exception e) {System.out.println("찜 목록 오류 :" + e);}
		
		return null;
	}
	
	// 제품 찜하기 등록(찜하기 상태가 아닐때=조건에 따른 레코드가 없을때)/취소(찜하기 상태가 아닐때/조건에 따른 레코드가 있을때)
	public boolean setWish(int mno, int ino) {
		
		String sql = "";
		
		try {		
			sql += getWish(mno, ino) ? 
				"delete from watchitem where mno = ? and ino = ?" : 
				"insert into watchitem(mno, ino) values(?, ?)";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, ino);
			int count = ps.executeUpdate();
			if(count == 1) {return true;}
			
		} catch (Exception e) {System.out.println(e);}	
		return false;	
	}
	
	
	// 제품 찜하기 상태 출력
	public boolean getWish(int mno , int ino) {
		try {
			String sql = "select*from watchitem where mno = ? and ino = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.setInt(2, ino);
			rs = ps.executeQuery();
			if(rs.next()) {return true;}
			
		} catch (Exception e) {System.out.println(e);}
		return false;
	}

	
	
	

}
