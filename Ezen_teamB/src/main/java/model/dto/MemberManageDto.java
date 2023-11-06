package model.dto;

import java.util.ArrayList;

// 관리자 회원관리 페이지 DTO
public class MemberManageDto {
	
	private int page; 				// 현재 페이지 번호
	private int maxSize; 			// 페이지당 최대 회원수 (현재 14명)
	private int startRow; 			// 현재 페이지에서 시작되는 레코드 번호
	private int totalMemeberCount; 	// 총 회원수
	private int totalPageCount; 	// 총 페이지 수
	private int startBtn; 			// 페이지 번호의 시작번호
	private int endBtn; 			// 페이지 번호의 끝 번호
	
	ArrayList<MemberList> memberList;
	
	// 생성자
	public MemberManageDto() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberManageDto(int page, int maxSize, int startRow, int totalMemeberCount, int totalPageCount, int startBtn,
			int endBtn, ArrayList<MemberList> memberList) {
		super();
		this.page = page;
		this.maxSize = maxSize;
		this.startRow = startRow;
		this.totalMemeberCount = totalMemeberCount;
		this.totalPageCount = totalPageCount;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.memberList = memberList;
	}

	
	// getter setter
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getTotalMemeberCount() {
		return totalMemeberCount;
	}

	public void setTotalMemeberCount(int totalMemeberCount) {
		this.totalMemeberCount = totalMemeberCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(int startBtn) {
		this.startBtn = startBtn;
	}

	public int getEndBtn() {
		return endBtn;
	}

	public void setEndBtn(int endBtn) {
		this.endBtn = endBtn;
	}

	public ArrayList<MemberList> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<MemberList> memberList) {
		this.memberList = memberList;
	}

	@Override
	public String toString() {
		return "MemberManageDto [page=" + page + ", maxSize=" + maxSize + ", startRow=" + startRow
				+ ", totalMemeberCount=" + totalMemeberCount + ", totalPageCount=" + totalPageCount + ", startBtn="
				+ startBtn + ", endBtn=" + endBtn + ", memberList=" + memberList + "]";
	}
	
	
	
	
	
}
