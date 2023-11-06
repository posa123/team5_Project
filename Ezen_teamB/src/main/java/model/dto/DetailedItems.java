package model.dto;

import java.util.HashMap;
import java.util.Map;

// 개별물품에 대한 전체 정보 출력 Dto
public class DetailedItems {
	
	private int ino;				// 판매물품번호 pk
	private int iprice;				// 판매가격
	private int mno;				// 판매자
	private String ititle;			// 판매제목
    private String icontent;		// 판매내용
	private int itrade;				// 거래방식( 1 배송, 2 대면거래, 3 중개거래 )
	private String itradeplace;		// 대면거래일 시 거래 주소
	private String idate;			// 판매물품 등록일시
	private int eno;				// 중개소 번호( 중개소거래를 이용할 시 )
	private int iestate;			// 거래상태( 0 판매중 1 판매완료 )
	private int uno;
	private int dno;				// 소분류번호 pk
	private int isafepayment;		// 안전결제사용여부( 0 미사용 1 사용 )
	private int keepstate;			// 중개거래소 물품 보관 여부( 0 미보관 1 보관 )
	private Map<Integer, String> imgList = new HashMap<>();
	private String lat;				// 판매물품 위도
	private String lng;				// 판매물품 경도
	private String mid;				// 판매자 아이디
	
	// 생성자
	public DetailedItems() {
		// TODO Auto-generated constructor stub
	}

	public DetailedItems(int ino, int iprice, int mno, String ititle, String icontent, int itrade, String itradeplace,
			String idate, int eno, int iestate, int uno, int dno, int isafepayment, int keepstate, Map<Integer, String> imgList,
			String lat, String lng, String mid) {
		super();
		this.ino = ino;
		this.iprice = iprice;
		this.mno = mno;
		this.ititle = ititle;
		this.icontent = icontent;
		this.itrade = itrade;
		this.itradeplace = itradeplace;
		this.idate = idate;
		this.eno = eno;
		this.iestate = iestate;
		this.uno = uno;
		this.dno = dno;
		this.isafepayment = isafepayment;
		this.keepstate = keepstate;
		this.imgList = imgList;
		this.lat = lat;
		this.lng = lng;
		this.mid = mid;
	}
	
	// 배송거래 생성자
		// 위치정보( 장소, 위도, 경도 ) 제외
	public DetailedItems(int ino, int iprice, int mno, String ititle, String icontent, int itrade, String idate,
			int eno, int iestate, int uno, int dno, int isafepayment, int keepstate, Map<Integer, String> imgList, String mid) {
		super();
		this.ino = ino;
		this.iprice = iprice;
		this.mno = mno;
		this.ititle = ititle;
		this.icontent = icontent;
		this.itrade = itrade;
		this.idate = idate;
		this.eno = eno;
		this.iestate = iestate;
		this.uno = uno;
		this.dno = dno;
		this.isafepayment = isafepayment;
		this.keepstate = keepstate;
		this.imgList = imgList;
		this.mid = mid;
	}
	
	
	// getter setter
	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public int getIprice() {
		return iprice;
	}

	public void setIprice(int iprice) {
		this.iprice = iprice;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	public String getIcontent() {
		return icontent;
	}

	public void setIcontent(String icontent) {
		this.icontent = icontent;
	}

	public int getItrade() {
		return itrade;
	}

	public void setItrade(int itrade) {
		this.itrade = itrade;
	}

	public String getItradeplace() {
		return itradeplace;
	}

	public void setItradeplace(String itradeplace) {
		this.itradeplace = itradeplace;
	}

	public String getIdate() {
		return idate;
	}

	public void setIdate(String idate) {
		this.idate = idate;
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public int getIestate() {
		return iestate;
	}

	public void setIestate(int iestate) {
		this.iestate = iestate;
	}
	
	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}
	
	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public int getIsafepayment() {
		return isafepayment;
	}

	public void setIsafepayment(int isafepayment) {
		this.isafepayment = isafepayment;
	}

	public int getKeepstate() {
		return keepstate;
	}

	public void setKeepstate(int keepstate) {
		this.keepstate = keepstate;
	}

	public Map<Integer, String> getImgList() {
		return imgList;
	}

	public void setImgList(Map<Integer, String> imgList) {
		this.imgList = imgList;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	@Override
	public String toString() {
		return "DetailedItems [ino=" + ino + ", iprice=" + iprice + ", mno=" + mno + ", ititle=" + ititle
				+ ", icontent=" + icontent + ", itrade=" + itrade + ", itradeplace=" + itradeplace + ", idate=" + idate
				+ ", eno=" + eno + ", iestate=" + iestate + ", uno=" + uno + ", dno=" + dno + ", isafepayment="
				+ isafepayment + ", keepstate=" + keepstate + ", imgList=" + imgList + ", lat=" + lat + ", lng=" + lng
				+ ", mid=" + mid + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
