package model.dto;

public class TradeLog {
	
	private int tno;				// 거래번호
	private String mid;				// 판매자아이디
	private String mname;			// 판매자이름
	private String itradeplace;		// 판매위치
	private String ititle;			// 판매게시물제목
	private int itrade;			// 거래방식
	private String tradedate;		// 거래완료날짜
	
	public TradeLog() {}

	public TradeLog(int tno, String mid, String mname, String itradeplace, String ititle, int itrade,
			String tradedate) {
		super();
		this.tno = tno;
		this.mid = mid;
		this.mname = mname;
		this.itradeplace = itradeplace;
		this.ititle = ititle;
		this.itrade = itrade;
		this.tradedate = tradedate;
	}

	public int getTno() {
		return tno;
	}

	public void setTno(int tno) {
		this.tno = tno;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getItradeplace() {
		return itradeplace;
	}

	public void setItradeplace(String itradeplace) {
		this.itradeplace = itradeplace;
	}

	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	public int getItrade() {
		return itrade;
	}

	public void setItrade(int itrade) {
		this.itrade = itrade;
	}

	public String getTradedate() {
		return tradedate;
	}

	public void setTradedate(String tradedate) {
		this.tradedate = tradedate;
	}

	@Override
	public String toString() {
		return "TradeLog [tno=" + tno + ", mid=" + mid + ", mname=" + mname + ", itradeplace=" + itradeplace
				+ ", ititle=" + ititle + ", itrade=" + itrade + ", tradedate=" + tradedate + "]";
	}
	
	

}
