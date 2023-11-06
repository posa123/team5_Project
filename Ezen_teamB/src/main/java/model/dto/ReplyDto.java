package model.dto;

public class ReplyDto {
	private int rno;	// 답글 번호
	private int mno;	// 회원번호	
	private int bno;	// 게시물 번호
    private String rdate; 		// 답글 날짜	
    private String rcontent;		// 답글 내용
    private String mid;
    
    // 기본 생성자
    public ReplyDto() {
		// TODO Auto-generated constructor stub
	}
   
	
	public ReplyDto(int rno, int mno, int bno, String rdate, String rcontent, String mid) {
		super();
		this.rno = rno;
		this.mno = mno;
		this.bno = bno;
		this.rdate = rdate;
		this.rcontent = rcontent;
		this.mid = mid;
	}


	// 개별 답글 등록 생성자
	public ReplyDto( int mno, int bno, String rcontent) {
		super();
		
		this.mno = mno;
		this.bno = bno;
		this.rcontent = rcontent;
	}
	// 개별 답글 수정 생성자
	public ReplyDto(int rno, String rcontent) {
		super();
		this.rno = rno;
		this.rcontent = rcontent;
	}
	

	public int getRno() {
		return rno;
	}
	


	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", mno=" + mno + ", bno=" + bno + ", rdate=" + rdate + ", rcontent=" + rcontent
				+ ", mid=" + mid + "]";
	}
	
	
    
		
	
    
    
}
