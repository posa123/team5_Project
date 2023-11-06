package model.dao;

// 포인트 Dao
public class PointPaymentDao extends Dao {
	
	private static PointPaymentDao pointPaymentDao = new PointPaymentDao();
	public static PointPaymentDao getInstance() {
		return pointPaymentDao;
	}
	private PointPaymentDao() {}
	
	
	// 구매자 포인트 조회
	public int getMemeberPoint ( int mno ) {
		
		try {
			
			String sql = "select mpoint from memberlist where mno ="+mno;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if( rs.next() ) return rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return -1;
	}
	
	// 구매자 포인트 차감 [ 가지가지 서버로 포인트 이동 ]
	public boolean deductPoint( int mno, int iprice ) {
		
		try {
			
			String sql = "update memberlist set mpoint = mpoint-"+iprice+" where mno = "+mno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return false;
	}
	
	// 판매자 포인트 지급 [ 가지가지 서버에서 판매자에게 포인트 전달 ]
		// 또는 거래를 취소한 구매자에게 포인트를 환급
	public boolean givePoint( int mno, int iprice ) {
		
		try {
			
			String sql = "update memberlist set mpoint = mpoint+"+iprice+" where mno = "+mno;
			
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	
}




















