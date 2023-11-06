package model.dto;

// 마이메뉴 회원정보
public class Mymenu {
	
	private String mid;		// 아이디
	private int mlevel;		// 칭호
	private int tradelog;	// 거래활동수
	private int saleProduct;	// 판매물품수
	private int mpoint;		// 포인트
	private String mname;	// 이름
	private String madress;	// 주소
	private String memail;	// 이메일
	
	public Mymenu() {}

	public Mymenu(String mid, int mlevel, int tradelog, int saleProduct, int mpoint, String mname, String madress,
			String memail) {
		super();
		this.mid = mid;
		this.mlevel = mlevel;
		this.tradelog = tradelog;
		this.saleProduct = saleProduct;
		this.mpoint = mpoint;
		this.mname = mname;
		this.madress = madress;
		this.memail = memail;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getMlevel() {
		return mlevel;
	}

	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}

	public int getTradelog() {
		return tradelog;
	}

	public void setTradelog(int tradelog) {
		this.tradelog = tradelog;
	}

	public int getSaleProduct() {
		return saleProduct;
	}

	public void setSaleProduct(int saleProduct) {
		this.saleProduct = saleProduct;
	}

	public int getMpoint() {
		return mpoint;
	}

	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	public String getMadress() {
		return madress;
	}

	public void setMadress(String madress) {
		this.madress = madress;
	}

	@Override
	public String toString() {
		return "Mymenu [mid=" + mid + ", mlevel=" + mlevel + ", tradelog=" + tradelog + ", saleProduct=" + saleProduct
				+ ", mpoint=" + mpoint + ", mname=" + mname + ", memail=" + memail + ", madress=" + madress + "]";
	}

}
