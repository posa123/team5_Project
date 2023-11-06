package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgDto {
	
	private String caller;	// 보낸 사람
	private String jcontent;	// 보낸 내용
	private String jchatdate;		// 보낸시간
	private int ino;			// 채팅 거래상품
	
	public MsgDto() {}

	public MsgDto(String caller, String jcontent) {
		super();
		this.caller = caller;
		this.jcontent = jcontent;
		Date date = new Date();
		// 2-2 날짜 포멧(형식) : SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm");
			// y연도 M월 d일 h시m분s초 aa오전오후
		this.jchatdate = sdf.format(date);	// 현재시간을 정의한형식으로 변환시킴	
	}

	public MsgDto(String caller, String jcontent, String jchatdate, int ino) {
		super();
		this.caller = caller;
		this.jcontent = jcontent;
		this.jchatdate = jchatdate;
		this.ino = ino;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
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

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	@Override
	public String toString() {
		return "MsgDto [caller=" + caller + "," + " jcontent=" + jcontent + ", jchatdate="
				+ jchatdate + ", ino=" + ino + "]";
	}
	

}
