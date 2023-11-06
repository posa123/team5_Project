package model.dto;

// 게시판 카테고리 and 게시판
public class Board {
	/* 게시판 카테고리 */
	private int cno;			// 카테고리 pk
	private String cname;		// 카테고리명
    /* 게시판 구성 */
	private int bno;			// 게시판 pk
	private String btitle;		// 제목
	private String bcontent;	// 작성내용
	private String bdate;		// 작성일
	private String bfile;		// 첨부파일
	private int mno;			// 작성자번호
	private String mid;			// 작성자 아이디
     
	private boolean ishost;		// 작성자 확인변수

    
    // 생성자
    public Board() {
		// TODO Auto-generated constructor stub
	}


	public Board(int cno, String cname, int bno, String btitle, String bcontent, String bdate, String bfile, int mno,
			boolean ishost) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bfile = bfile;
		this.mno = mno;
		this.ishost = ishost;
	}
	
	// 글쓰기 생성자
	public Board(int cno, String btitle, String bcontent, String bfile, int mno) {
		super();
		this.cno = cno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
		this.mno = mno;
	}
	
	
	
	
	// 게시물 조회 생성자
	public Board(int cno, String cname, int bno, String btitle, String bcontent, String bdate, String bfile, int mno,
			String mid) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bfile = bfile;
		this.mno = mno;
		this.mid = mid;
	}
	


	// 게시물 개별 조회
	public Board(int bno,  String btitle,String bcontent , String bfile,String bdate, int mno,int cno,String mid,   
			String cname) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bfile = bfile;
		this.mno = mno;
		this.mid = mid;
	}
	// 게시물 수정 생성자
	public Board(int cno, int bno, String btitle, String bcontent, String bfile) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bfile = bfile;
	}
	

	public int getCno() {
		return cno;
	}


	


	


	public void setCno(int cno) {
		this.cno = cno;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public int getBno() {
		return bno;
	}


	public void setBno(int bno) {
		this.bno = bno;
	}


	public String getBtitle() {
		return btitle;
	}


	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}


	public String getBcontent() {
		return bcontent;
	}


	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}


	public String getBdate() {
		return bdate;
	}


	public void setBdate(String bdate) {
		this.bdate = bdate;
	}


	public String getBfile() {
		return bfile;
	}


	public void setBfile(String bfile) {
		this.bfile = bfile;
	}


	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public boolean isIshost() {
		return ishost;
	}


	public void setIshost(boolean ishost) {
		this.ishost = ishost;
	}
	

	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	@Override
	public String toString() {
		return "Board [cno=" + cno + ", cname=" + cname + ", bno=" + bno + ", btitle=" + btitle + ", bcontent="
				+ bcontent + ", bdate=" + bdate + ", bfile=" + bfile + ", mno=" + mno + ", mid=" + mid + ", ishost="
				+ ishost + "]";
	}


	
	
}
