package model.dto;

// 중개거래소 정보
public class Emediation {
	private int eno;		// 중개거래소 번호 pk	
	private String ename;	// 중개거래소분류 (이용자가 맵에서 보는 업체명)
	private String eadress;	// 중개거래소 주소
	private String elat;	// 중개거래소 위도
	private String elng;	// 중개거래소 경도
	
	
	// 생성자
	public Emediation() {
		// TODO Auto-generated constructor stub
	}
	public Emediation(int eno, String ename, String eadress, String elat, String elng) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.eadress = eadress;
		this.elat = elat;
		this.elng = elng;
	}
	
	// getter setter
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEadress() {
		return eadress;
	}
	public void setEadress(String eadress) {
		this.eadress = eadress;
	}
	public String getElat() {
		return elat;
	}
	public void setElat(String elat) {
		this.elat = elat;
	}
	public String getElng() {
		return elng;
	}
	public void setElng(String elng) {
		this.elng = elng;
	}
	
	
	
	@Override
	public String toString() {
		return "Emediation [eno=" + eno + ", ename=" + ename + ", eadress=" + eadress + ", elat=" + elat + ", elng="
				+ elng + "]";
	}

	
}
