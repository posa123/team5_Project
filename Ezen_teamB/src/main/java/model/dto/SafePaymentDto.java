package model.dto;

public class SafePaymentDto {

	private int vno; 			// 안전결제 진행 상태 pk
	private int vrequester;		// 구매자 안전결제방식 구매요청자 ID
	private String vrespdate;	// 안전결제 요청일시
	private String vreqsdate;	// 판매자 안전결제 수락일시
	private String vgivedate;	// 판매자 안전결제 물품전달일
	private int vstate;			// 거래상태( 1 요청상태, 2 요청수락상태(가지가지에게 포인트 보내기), 3 물품전달상태, 4 거래완료 상태(판매자에게 포인트 입금) )
	private int ino;			// 판매물품
	private String traderId;	// 판매자 ID
	private String ititle;		// 판매제목
	
	// 생성자
	public SafePaymentDto() {
		// TODO Auto-generated constructor stub
	}

	public SafePaymentDto(int vno, int vrequester, String vrespdate, String vreqsdate, String vgivedate, int vstate,
			int ino, String traderId, String ititle) {
		super();
		this.vno = vno;
		this.vrequester = vrequester;
		this.vrespdate = vrespdate;
		this.vreqsdate = vreqsdate;
		this.vgivedate = vgivedate;
		this.vstate = vstate;
		this.ino = ino;
		this.traderId = traderId;
		this.ititle = ititle;
	}


	// getter setter
	public int getVno() {
		return vno;
	}

	public void setVno(int vno) {
		this.vno = vno;
	}

	public int getVrequester() {
		return vrequester;
	}

	public void setVrequester(int vrequester) {
		this.vrequester = vrequester;
	}

	public String getVrespdate() {
		return vrespdate;
	}

	public void setVrespdate(String vrespdate) {
		this.vrespdate = vrespdate;
	}

	public String getVreqsdate() {
		return vreqsdate;
	}

	public void setVreqsdate(String vreqsdate) {
		this.vreqsdate = vreqsdate;
	}

	public String getVgivedate() {
		return vgivedate;
	}

	public void setVgivedate(String vgivedate) {
		this.vgivedate = vgivedate;
	}

	public int getVstate() {
		return vstate;
	}

	public void setVstate(int vstate) {
		this.vstate = vstate;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getItitle() {
		return ititle;
	}

	public void setItitle(String ititle) {
		this.ititle = ititle;
	}

	@Override
	public String toString() {
		return "SafePaymentDto [vno=" + vno + ", vrequester=" + vrequester + ", vrespdate=" + vrespdate + ", vreqsdate="
				+ vreqsdate + ", vgivedate=" + vgivedate + ", vstate=" + vstate + ", ino=" + ino + ", traderId=" + traderId
				+ ", ititle=" + ititle + "]\n";
	}

	
	
	

	
	
	
	
	
	
	
	
	
}
