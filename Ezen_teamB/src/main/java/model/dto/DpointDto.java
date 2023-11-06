package model.dto;

// 대면거래시 거래위치( 위도, 경도 ) Dto
public class DpointDto {
	
	private int dno;				// 거래장소 pk
	private String itradeplace;		// 대면거래일 시 거래 주소
	private String dlat;			// 위도
	private String dlng;			// 경도
	private int ino;				// 판매물품 pk
	
	// 생성자
	public DpointDto() {
		// TODO Auto-generated constructor stub
	}
	
	public DpointDto(int dno, String itradeplace, String dlat, String dlng, int ino) {
		super();
		this.dno = dno;
		this.itradeplace = itradeplace;
		this.dlat = dlat;
		this.dlng = dlng;
		this.ino = ino;
	}
	
	// 물품 등록시 사용되는 생성자
	public DpointDto(String dlat, String dlng) {
		super();
		this.dlat = dlat;
		this.dlng = dlng;
	}
	
	
	
	
	
	
	// getter setter
	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getItradeplace() {
		return itradeplace;
	}

	public void setItradeplace(String itradeplace) {
		this.itradeplace = itradeplace;
	}

	public String getDlat() {
		return dlat;
	}

	public void setDlat(String dlat) {
		this.dlat = dlat;
	}

	public String getDlng() {
		return dlng;
	}

	public void setDlng(String dlng) {
		this.dlng = dlng;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	
	
	@Override
	public String toString() {
		return "DpointDto [dno=" + dno + ", itradeplace=" + itradeplace + ", dlat=" + dlat + ", dlng=" + dlng + ", ino="
				+ ino + "]";
	}
	
	
	
	
	
	
	
	
}
