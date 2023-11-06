package model.dto;

public class MsgRoom {
	  
    private int caller;                 // 메시지 보낸 사람
	private int receiver;            // 메시지 받은 사람   
	private String jcontent;       	// 마지막 메시지 내용
    private String jchatdate;		// 마지막 메시지 시간
    private boolean jcheck;		// 채팅 확인 식별 변수
    private int ino;		// 채팅과 연결된 상품 번호
    private String rno;	// 채팅방식별변수
    
    public MsgRoom() {}

	public MsgRoom(int caller, int receiver, String jcontent, String jchatdate, boolean jcheck, int ino, String rno) {
		super();
		this.caller = caller;
		this.receiver = receiver;
		this.jcontent = jcontent;
		this.jchatdate = jchatdate;
		this.jcheck = jcheck;
		this.ino = ino;
		this.rno = rno;
	}

	public int getCaller() {
		return caller;
	}

	public void setCaller(int caller) {
		this.caller = caller;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public String getJcontent() {
		return jcontent;
	}

	public void setJcontent(String jcontent) {
		this.jcontent = jcontent;
	}

	public String getJchatdate() {
		return jchatdate;
	}

	public void setJchatdate(String jchatdate) {
		this.jchatdate = jchatdate;
	}

	public boolean isJcheck() {
		return jcheck;
	}

	public void setJcheck(boolean jcheck) {
		this.jcheck = jcheck;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public String getRno() {
		return rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	@Override
	public String toString() {
		return "MsgRoom [caller=" + caller + ", receiver=" + receiver + ", jcontent=" + jcontent + ", jchatdate="
				+ jchatdate + ", jcheck=" + jcheck + ", ino=" + ino + ", rno=" + rno + "]";
	}
    
    

}
