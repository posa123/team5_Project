package model.dto;

import java.util.HashMap;
import java.util.Map;

// 물품 판매정보 and 물품 카테고리 and 제품별 여러 개의 이미지
public class ItemsInfo {
	  	
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
	private int dno;				// 소분류번호 pk
	private int isafepayment;		// 안전결제사용여부( 0 미사용 1 사용 )
	private int keepstate;			// 중개거래소 물품 보관 여부( 0 미보관 1 보관 )
	private int uno;				// 대분류번호 pk
	private String uname;			// 대분류 카테고리명
	private String dname;			// 소분류 카테고리명
    // 제품별 여러개 이미지 
	Map<Integer, String> imgList = new HashMap<>();
    
    // 생성자
    public ItemsInfo() {
		// TODO Auto-generated constructor stub
	}

	public ItemsInfo(int ino, int iprice, int mno, String ititle, String icontent, int itrade, String itradeplace,
			String idate, int eno, int iestate, int dno, int isafepayment, int keepstate, int uno, String uname,
			String dname, Map<Integer, String> imgList) {
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
		this.isafepayment = isafepayment;
		this.keepstate = keepstate;
		this.uno = uno;
		this.uname = uname;
		this.dno = dno;
		this.dname = dname;
		this.imgList = imgList;
	}
	
	// 물품 정보 출력 생성자
	public ItemsInfo(int ino, int mno, String ititle, int itrade, String idate, String uname, String dname,
			Map<Integer, String> imgList) {
		super();
		this.ino = ino;
		this.mno = mno;
		this.ititle = ititle;
		this.itrade = itrade;
		this.idate = idate;
		this.uname = uname;
		this.dname = dname;
		this.imgList = imgList;
	}
	
	
	// 물품 등록 생성자
	public ItemsInfo(int ino, int iprice, int mno, String ititle, String icontent, int itrade, String itradeplace, int eno, int dno,
			int isafepayment, Map<Integer, String> imgList) {
		super();
		this.ino = ino;
		this.iprice = iprice;
		this.mno = mno;
		this.ititle = ititle;
		this.icontent = icontent;
		this.itrade = itrade;
		this.itradeplace = itradeplace;
		this.eno = eno;
		this.dno = dno;
		this.isafepayment = isafepayment;
		this.imgList = imgList;
	}

	// DB에 물품 정보 요청 생성자 
	public ItemsInfo(int ino, int iprice, int mno, String ititle, String icontent, int itrade, String itradeplace, String idate,
			int eno, int iestate, int dno, int isafepayment, int keepstate, Map<Integer, String> imgList) {
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
		this.dno = dno;
		this.isafepayment = isafepayment;
		this.keepstate = keepstate;
		this.imgList = imgList;
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

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Map<Integer, String> getImgList() {
		return imgList;
	}

	public void setImgList(Map<Integer, String> imgList) {
		this.imgList = imgList;
	}

	
	
	@Override
	public String toString() {
		return "ItemsInfo [ino=" + ino + ", iprice=" + iprice + ", mno=" + mno + ", ititle=" + ititle + ", icontent="
				+ icontent + ", itrade=" + itrade + ", itradeplace=" + itradeplace + ", idate=" + idate + ", eno=" + eno
				+ ", iestate=" + iestate + ", isafepayment=" + isafepayment + ", keepstate=" + keepstate + ", uno="
				+ uno + ", uname=" + uname + ", dno=" + dno + ", dname=" + dname + ", imgList=" + imgList + "]";
	}


	
	
	
	
	

	

	
	
	
    
    
}
