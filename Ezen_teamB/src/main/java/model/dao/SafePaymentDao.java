package model.dao;

import java.awt.List;
import java.util.ArrayList;

import model.dto.SafePaymentDto;

// 안전결제 클래스
public class SafePaymentDao extends Dao{

	private static SafePaymentDao safePaymentDao = new SafePaymentDao();
	public static SafePaymentDao getInstance() {
		return safePaymentDao;
	}
	private SafePaymentDao() {}
	
	// 구매자가 안전결제 요청 [ 안전결제 필드 최초 생성 ]
	public int responseSafepay( int vrequester, int ino ) {
		
		try {
			
			SafePaymentDto dto = getSafepayLog( vrequester, ino );
			// 신규 필드, 즉 안전결제를 요청하는데 해당 물품과 구매자 간 동일한
			// 안전결제 진행 상태에 있다면( 이미 필드가 있다면 ) 
			// 현재 진행 중인 거래상태를 return
			if( dto != null ) return dto.getVstate();
			
			String sql = "insert into vsafepayment( vrequester, ino ) values ( ?, ? )";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vrequester);
			ps.setInt(2, ino);
			ps.executeUpdate();
			
			return 0;	// 정상
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return -1;
	}
	
	// 개별 결제내역 출력
	public SafePaymentDto getSafepayLog( int vrequester, int ino ) {
		
		try {
			
			String sql = "select a.*, c.mid, b.ititle from vsafepayment a join itemsinfo b "
					   + "on a.ino = b.ino join memberlist c on b.mno = c.mno where a.vrequester = ? and a.ino = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vrequester);
			ps.setInt(2, ino);
			rs = ps.executeQuery();
			
			if( rs.next() ) {
				return new SafePaymentDto(
					rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9)
				);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null; 
	}
	
	
	// 전체 결제내역 출력
	public ArrayList<SafePaymentDto> getBuyerPaymentList( int maxSize, int startRow, int vstateFilter, int vrequester ){
		
		ArrayList<SafePaymentDto> list = new ArrayList<>();
		
		try {
			
			String sql = "select a.*, c.mid, b.ititle from vsafepayment a "
					+ "join itemsinfo b on a.ino = b.ino join memberlist c on b.mno = c.mno";
			
			// 필터가 존재하지 않으면 모두 출력
			if( vstateFilter != 0 ) {
				sql += " where a.vstate = "+vstateFilter+" and a.vrequester = "+vrequester;
			} else {
				sql += " where a.vrequester = "+vrequester;
			}
			sql += " ORDER BY a.vno DESC limit ?, ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, maxSize);
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				SafePaymentDto dto = new SafePaymentDto(
						rs.getInt("vno"),
						rs.getInt("vrequester"),
						rs.getString("vrespdate"),
						rs.getString("vreqsdate"),
						rs.getString("vgivedate"),
						rs.getInt("vstate"),
						rs.getInt("ino"),
						rs.getString("mid"),
						rs.getString("ititle")
				);
				list.add( dto );
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	// 전체 결제내역 출력
	public ArrayList<SafePaymentDto> getSellerPaymentList(int maxSize, int startRow, int vstateFilter, int vrequester) {

		ArrayList<SafePaymentDto> list = new ArrayList<>();

		try {

			String sql = "select a.*, c.mid, b.ititle from vsafepayment a "
					+ "join itemsinfo b on a.ino = b.ino join memberlist c on a.vrequester = c.mno "
					+ "where b.mno = "+vrequester;

			// 필터가 존재하지 않으면 모두 출력
			if (vstateFilter != 0) {
				sql += " and a.vstate = "+vstateFilter;
			}
			sql += " ORDER BY a.vno DESC limit ?, ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, maxSize);
			rs = ps.executeQuery();

			while (rs.next()) {
				SafePaymentDto dto = new SafePaymentDto(rs.getInt("vno"), rs.getInt("vrequester"),
						rs.getString("vrespdate"), rs.getString("vreqsdate"), rs.getString("vgivedate"),
						rs.getInt("vstate"), rs.getInt("ino"), rs.getString("mid"), rs.getString("ititle"));
				list.add(dto);
			}

			return list;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	
	// 구매관리 집계 함수
	public int getBuyerTotalCount( int vstateFilter, int vrequester ) {
			
		try {
			
			String sql = "select count(*) from vsafepayment v where vrequester ="+vrequester;
			if( vstateFilter != 0 ) {
				sql = "select count(*) from vsafepayment v where vstate = "+ vstateFilter +" and vrequester ="+vrequester;
			}
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if( rs.next() ) return rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return 0;
	}
	
	// 판매관리 집계 함수
	public int getSellerTotalCount( int vstateFilter, int seller ) {
		try {

			String sql = "select count(*) from vsafepayment a join itemsinfo b on a.ino = b.ino where b.mno = " + seller;
			if (vstateFilter != 0) {
				sql = "select count(*) from vsafepayment a join itemsinfo b on a.ino = b.ino where a.vstate = "+vstateFilter+" and b.mno = "+seller;
			}

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (Exception e) {
			System.out.println(e);
		}

		return 0;
	}
	
	// 개별 안전결제 취소(삭제) 기능
	public boolean deleteSafepay( int vno, int ino, int vrequester, int vstate ) {
		
		try {
			
			// 현재 안전결제 진행상태 확인
				// 안전결제가 '수락' 단계인 경우
				// 즉, 포인트가 지급된 경우 포인트를 환급해주어야함
			if( vstate == 2 ) {
				// 제품 가격 조회
				int iprice = ItemDao.getInstance().getItemPrice( ino );
				// 구매자의 포인트 환급
				PointPaymentDao.getInstance().givePoint(vrequester, iprice);
			}
			
			String sql = "delete from vsafepayment where vno = "+vno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// 다수 안전결제 취소(삭제) 기능
		// 수락되지 않은 물품에 대한 안전결제 모두 삭제
	public void allDeleteSafepay( int vno, int ino ) {
		
		try {

			String sql = "delete from vsafepayment where ino = "+ino+" and vno != "+vno;

			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	// 판매자 요청 수락
	public boolean acceptSafepay(int vno, int ino, int vrequester) {
		
		try {
			
			//수락되지 않은 물품에 대한 안전결제 모두 삭제
			allDeleteSafepay( vno, ino );
			// 제품 가격 조회
			int iprice = ItemDao.getInstance().getItemPrice( ino );
			// 포인트 차감
			PointPaymentDao.getInstance().deductPoint( vrequester, iprice );
			
			String sql = "update vsafepayment set vreqsdate = now(), vstate = 2 where vno = "+vno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}

	// 판매자 물품 전달
	public boolean deliverySafepay(int vno) {
		
		try {
			
			String sql = "update vsafepayment set vgivedate = now(), vstate = 3 where vno = "+vno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	// 구매자 수령 확정
	public boolean checkItem(int vno, int ino, int vrequester) {
		
		try {
			
			// ino 물품 테이블에 물품 상태 변경 [거래완료]
			ItemDao.getInstance().changeItemState( ino );
			
			// 거래내역 등록
			ItemDao.getInstance().setTradeLog( ino, vrequester );
			
			// 제품 가격 조회 후 포인트 지급 
			int iprice = ItemDao.getInstance().getItemPrice( ino );
			int seller = ItemDao.getInstance().getItemSeller( ino );
			PointPaymentDao.getInstance().givePoint(seller, iprice);
			
			String sql = "update vsafepayment set vstate = 4 where vno = "+vno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
