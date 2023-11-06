package model.dto;

// 대면거래 인덱스(MAP 인포윈도우) 아이템 리스트 DTO
public class FaceToFaceDto {

	private int ino;				// 판매물품번호 pk 
	private int iprice;				// 판매가격
	private String ititle;			// 판매제목
	private int isafepayment;		// 안전결제사용여부( 0 미사용 1 사용 )
	private String pimg;			// 사진 1개
	private String dlat;			// 위도
	private String dlng;			// 경도
	
	
	// 생성자
	public FaceToFaceDto(int ino, int iprice, String ititle, int isafepayment, String pimg, String dlat, String dlng) {
		super();
		this.ino = ino;
		this.iprice = iprice;
		this.ititle = ititle;
		this.isafepayment = isafepayment;
		this.pimg = pimg;
		this.dlat = dlat;
		this.dlng = dlng;
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


	public String getItitle() {
		return ititle;
	}


	public void setItitle(String ititle) {
		this.ititle = ititle;
	}


	public int getIsafepayment() {
		return isafepayment;
	}


	public void setIsafepayment(int isafepayment) {
		this.isafepayment = isafepayment;
	}


	public String getPimg() {
		return pimg;
	}


	public void setPimg(String pimg) {
		this.pimg = pimg;
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

	@Override
	public String toString() {
		return "FaceToFaceDto [ino=" + ino + ", iprice=" + iprice + ", ititle=" + ititle + ", isafepayment="
				+ isafepayment + ", pimg=" + pimg + ", dlat=" + dlat + ", dlng=" + dlng + "]";
	}
	
	
	
	
	
	
	
}
