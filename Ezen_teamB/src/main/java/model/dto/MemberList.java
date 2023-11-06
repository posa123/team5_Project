package model.dto;

// 회원관리 정보 클래스
public class MemberList {
	private int mno;		// 회원번호 pk
	private String mname;	// 이름
	private String msno1;	// 주민번호 앞자리
	private String msno2;	// 주민번호 뒷자리
	private String mphone;	// 전화번호
	private String memail;	// 이메일
	private String madress;	// 주소
	private String mid;		// 아이디
	private String mpwd;	// 비빌번호
	private int mlevel;		// 칭호
	private int mpoint;		// 포인트
    
    
    // 생성자
    public MemberList() {
		// TODO Auto-generated constructor stub
	}
    
    // 풀 생성자
    public MemberList(int mno, String mname, String msno1, String msno2, String mphone, String memail, String madress,
			String mid, String mpwd, int mlevel, int mpoint) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.msno1 = msno1;
		this.msno2 = msno2;
		this.mphone = mphone;
		this.memail = memail;
		this.madress = madress;
		this.mid = mid;
		this.mpwd = mpwd;
		this.mlevel = mlevel;
		this.mpoint = mpoint;
	}

    // 회원 가입 생성자
	public MemberList(String mname, String msno1, String msno2, String mphone, String memail, String madress,
			String mid, String mpwd) {
		super();
		this.mname = mname;
		this.msno1 = msno1;
		this.msno2 = msno2;
		this.mphone = mphone;
		this.memail = memail;
		this.madress = madress;
		this.mid = mid;
		this.mpwd = mpwd;
	}

	// 로그인 기능 완성 전 임시적으로 사용되는 생성자
	public MemberList(int mno, String mid) {
		super();
		this.mno = mno;
		this.mid = mid;
	}
	
	// 회원정보수정용 생성자
	public MemberList(int mno, String memail, String mpwd) {
		super();
		this.mno = mno;
		this.memail = memail;
		this.mpwd = mpwd;
	}
	
	// 로그인 mno 가져오기
	public MemberList(int mno) {
		super();
		this.mno = mno;
	}

	
	// 아이디 & 비밀번호 찾기 생성자

	public MemberList(String mname, String mphone) {
		super();
		this.mname = mname;
		this.mphone = mphone;
	}
	
	
	

	// 아이디 & 비밀번호 반환  생성자
	
	public MemberList(String mid) {
		super();
		this.mid = mid;
	}

	// 관리자 모드 회원관리 출력 생성자
	public MemberList(int mno, String mname, String msno1, String mphone, String memail, String mid, int mpoint) {
		super();
		this.mno = mno;
		this.mname = mname;
		this.msno1 = msno1;
		this.mphone = mphone;
		this.memail = memail;
		this.mid = mid;
		this.mpoint = mpoint;
	}

	// get, set
	public int getMno() {
		return mno;
	}


	public void setMno(int mno) {
		this.mno = mno;
	}


	public String getMname() {
		return mname;
	}


	public void setMname(String mname) {
		this.mname = mname;
	}


	public String getMsno1() {
		return msno1;
	}


	public void setMsno1(String msno1) {
		this.msno1 = msno1;
	}


	public String getMsno2() {
		return msno2;
	}


	public void setMsno2(String msno2) {
		this.msno2 = msno2;
	}


	public String getMphone() {
		return mphone;
	}


	public void setMphone(String mphone) {
		this.mphone = mphone;
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


	public String getMid() {
		return mid;
	}


	public void setMid(String mid) {
		this.mid = mid;
	}


	public String getMpwd() {
		return mpwd;
	}


	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}


	public int getMlevel() {
		return mlevel;
	}


	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}


	public int getMpoint() {
		return mpoint;
	}


	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}

	
    // toString
	@Override
	public String toString() {
		return "MemberList [mno=" + mno + ", mname=" + mname + ", msno1=" + msno1 + ", msno2=" + msno2 + ", mphone="
				+ mphone + ", memail=" + memail + ", madress=" + madress + ", mid=" + mid + ", mpwd=" + mpwd
				+ ", mlevel=" + mlevel + ", mpoint=" + mpoint + "]";
	}
	
	
}
