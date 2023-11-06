package model.dto;

import model.dao.Dao;

public class TempPwd extends Dao{
	private String mid;
	private String memail;
	private String tempwd;
	
	public TempPwd() {
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getTempwd() {
		return tempwd;
	}
	public void setTempwd(String tempwd) {
		this.tempwd = tempwd;
	}
	public TempPwd(String mid, String memail, String tempwd) {
		super();
		this.mid = mid;
		this.memail = memail;
		this.tempwd = tempwd;
	}
	public TempPwd(String tempwd) {
		super();
		this.tempwd = tempwd;
	}
	public TempPwd(String mid, String memail) {
		super();
		this.mid = mid;
		this.memail = memail;
	}

	
	
}
