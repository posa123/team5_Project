package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.dto.AgeStatistic;
import model.dto.AreaStatistic;
import model.dto.CateStatistics;
import model.dto.Emediation;
import model.dto.MemberList;
import model.dto.MemberManageDto;
import model.dto.TradeLog;
import model.dto.TradeStatistic;

// 관리자 클래스
public class ManagerDao extends Dao{

	private static ManagerDao manager = new ManagerDao();
	public static ManagerDao getInstance() {
		return manager;
	}
	private ManagerDao() {}
	
	
	// 1. 회원관리
		// 1-1 회원 수 출력 [ 검색기능 포함 ]
	public int getTotalPageCount( String key, String keyword ) {
		
		try {
			
			String sql = "select count(*) from memberlist";
			
			if( !key.isEmpty() && !keyword.isEmpty() ) {
				sql += " where "+key+" like '%"+keyword+"%'";
			}
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if( rs.next() ) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("총회원수 출력 오류 : " + e);
		}
		
		return 0;
	}
		// 1-2 페이지에 해당되는 멤버리스트 출력
	public ArrayList<MemberList> getList( int maxSize, int startRow, String key, String keyword ){
		
		try {
			
			ArrayList<MemberList> list = new ArrayList<>();
			
			String sql = "SELECT mno, mname, msno1, mphone, memail, mid, mpoint FROM memberlist";
			
			if( !key.isEmpty() && !keyword.isEmpty() ) {
				sql += " where "+key+" like '%"+keyword+"%'";
			}
			
			sql += " ORDER BY mno DESC limit ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, maxSize);
			
			rs = ps.executeQuery();
			while( rs.next() ) {
				MemberList memberList = new MemberList(
						rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getInt(7)
				);
				list.add(memberList);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("회원통계출력오류 : " + e);
		}
		
		return null;
	}
		// 1-3 회원강제탈퇴
	public boolean exileMemeber( int mno ) {
		
		try {
			
			String sql = "delete from memberlist where mno = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mno);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println();
		}
		
		return false;
	}
	
	
	
	
	// 거래내역
	public List<TradeLog> getTradeLog(String key, String keyword, String pDate, String nDate){
		List<TradeLog> list = new ArrayList<TradeLog>();
		
		try {
			String sql = "select t.tno as 거래번호, m.mid, m.mname, i.itradeplace, i.ititle, i.itrade, t.tradedate\r\n"
					+ "from tradelog t, itemsinfo i, memberlist m\r\n"
					+ "where t.ino = i.ino and i.mno = m.mno";
			
			if( !key.isEmpty() && !keyword.isEmpty() ) {
				sql += " and "+key+" like '%"+keyword+"%'";
			}
			
			sql += " and t.tradedate between ? and ? order by t.tradedate desc";
			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				TradeLog tDto = new TradeLog(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));
				list.add(tDto);
			}
			return list;
			
		} catch (Exception e) {System.out.println("거래내역오류 : " + e);}
		
		
		return null;
	}
	
	
	// 통계(카테고리별)
	public List<CateStatistics> getCateStatistics(String pDate, String nDate){
		List<CateStatistics> list =  new ArrayList<>();
		
		try {
			String sql = "select u.uname as 대분류, d.dname as 소분류, count(case when i.iestate = 1 then 1 end) as 거래상태, count(case when i.isafepayment = 0 then 1 end) as 안전결제사용여부, count(*) as total_count\r\n"
					+ "from umaincategory u, dsubcategory d, itemsinfo i where u.uno = d.uno and d.dno = i.dno and i.idate between ? and ? \r\n"
					+ "group by u.uno, d.dname";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				CateStatistics cDto = new CateStatistics(
						rs.getString(1), rs.getString(2), 
						rs.getInt(3), rs.getInt(4), rs.getInt(5));
				list.add(cDto);
			}
			return list;
			
			
		} catch (Exception e) {System.out.println("카테고리별 통계오류 : " + e);}
		
		return null;
	}
	
	// 통계(거래방식별)
	public List<TradeStatistic> getTradeStatistics(String pDate, String nDate){
		List<TradeStatistic> list = new ArrayList<>();
		
		try {
			String sql = "select i.itrade as 거래방식, count(i.itrade) "
					+ "from itemsinfo i, tradelog t "
					+ "where i.idate between ? and ? "
					+ "group by i.itrade";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				TradeStatistic tDto = new TradeStatistic(rs.getInt(1), rs.getInt(2));
				list.add(tDto);
			}
			return list;
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
	// 통계(연령대별)
	public List<AgeStatistic> getAgeStatistics(String pDate, String nDate){
		List<AgeStatistic> list = new ArrayList<AgeStatistic>();
		
		try {
			String sql = "select "
					+ "(substring(substring(now(),1,4)-concat(ceiling(substring(m.msno2,1,1)/2)+18,substring(m.msno1,1,2)),1,1)*10) as age, "
					+ "count((substring(substring(now(),1,4)-concat(ceiling(substring(m.msno2,1,1)/2)+18,substring(m.msno1,1,2)),1,1)*10)) as 거래량, "
					+ "count(case when i.isafepayment = 1 then 1 end) as 안전결제사용 from memberlist m, itemsinfo i where m.mno = i.mno and i.idate between ? and ? "
					+ "group by age";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				AgeStatistic aDto = new AgeStatistic(
						rs.getInt(1), rs.getInt(2), rs.getInt(3));
				list.add(aDto);
			}
			return list;
			
		} catch (Exception e) {System.out.println(e);}
		
		return null;
	}
	
	// 통계(지역별)
	public List<AreaStatistic> getAreaStatistics(String pDate, String nDate){
		List<AreaStatistic> list = new ArrayList<AreaStatistic>();
		
		try {
			String sql = "select LEFT(itradeplace,2) as 지역,  \r\n"
					+ "count(d.dno), count(case when i.itrade = 1 then 1 end) as 배송여부, count(case when i.iestate = 1 then 1 end) as 거래상태\r\n"
					+ "from itemsinfo i, dpoint d, memberlist m\r\n"
					+ "where i.ino = d.ino and i.mno = m.mno and i.idate between ? and ?\r\n"
					+ "group by 지역";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			while(rs.next()) {
				AreaStatistic aDto = new AreaStatistic(
						rs.getString(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4));
				list.add(aDto);
			}
			return list;
			
		} catch (Exception e) {System.out.println("지역별 통계 오류 : " + e);}
		
		
		return null;
	}
	
	// 전체 중개거래소 
	public List<Emediation> printEmediation(){
		List<Emediation> list = new ArrayList<Emediation>();
		
		try {
			String sql = "select*from emediation";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Emediation eDto = new Emediation(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				list.add(eDto);
			}
			return list;
			
		} catch (Exception e) {System.out.println("중개거래소 출력오류 : " + e);}
		
		
		return null;
	}
	
	
	// 개별 중개거래소 등록
	public boolean insertEmediation(String eType, String itradeplace, String dlat, String dlng) {
		
		try {
			String sql = "insert into emediation(ename, eadress, elat, elng)\r\n"
					+ "values(?, ?, ?, ?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1, eType);
			ps.setString(2, itradeplace);
			ps.setString(3, dlat);
			ps.setString(4, dlng);
			int count = ps.executeUpdate();
			if(count == 1) {
				return true;
			}

		} catch (Exception e) {System.out.println("중개거래소 등록오류 : " + e);}
		
		return false;
	}
	
	// 개별 중개거래소 수정
	public boolean updateEmediation(int eno, String eType, String itradeplace, String dlat, String dlng) {
		
		try {
			String sql = "update emediation set ename = ?, eadress = ?, elat = ?, elng = ?\r\n"
					+ "where eno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, eType);
			ps.setString(2, itradeplace);
			ps.setString(3, dlat);
			ps.setString(4, dlng);
			ps.setInt(5, eno);
			int count = ps.executeUpdate();
			if(count == 1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println("중개거래소 수정오류 : " + e);}
		
		return false;
	}
	
	// 개별 중개거래소 삭제
	public boolean deleteEmediation(int eno) {
		
		try {
			String sql = "delete from emediation where eno = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eno);
			int count = ps.executeUpdate();
			if(count == 1) {
				return true;
			}
			
		} catch (Exception e) {System.out.println("중개거래소 삭제오류 : " + e);}
		
		return false;
	}
	
	
	// 페이징 처리(카테고리)
	public int getTotalSize1(String category, String pDate, String nDate) {
		
		try {
			String sql = "select count(*) from umaincategory u, dsubcategory d, itemsinfo i "
					+ "where u.uno = d.uno and d.dno = i.dno and i.idate between ? and ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, pDate);
			ps.setString(2, nDate);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {System.out.println(e);}
		
		return 0;
	}
	
	
	
}