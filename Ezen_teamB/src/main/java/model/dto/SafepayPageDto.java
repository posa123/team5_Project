package model.dto;

import java.util.ArrayList;

public class SafepayPageDto {

	private int page; 				// 현재 페이지 번호
	private int maxSize; 			// 페이지당 최대 안전결제 건수 (현재 11건)
	private int startRow; 			// 현재 페이지에서 시작되는 레코드 번호
	private int totalPaymentCount; 	// 총 안전결제 건수
	private int totalPageCount; 	// 총 페이지 수
	private int startBtn; 			// 페이지 번호의 시작번호
	private int endBtn; 			// 페이지 번호의 끝 번호
	ArrayList<SafePaymentDto> paymentList;	// 안전결제 리스트

	// 생성자
	public SafepayPageDto() {}

	public SafepayPageDto(int page, int maxSize, int startRow, int totalPaymentCount, int totalPageCount, int startBtn,
			int endBtn, ArrayList<SafePaymentDto> paymentList) {
		super();
		this.page = page;
		this.maxSize = maxSize;
		this.startRow = startRow;
		this.totalPaymentCount = totalPaymentCount;
		this.totalPageCount = totalPageCount;
		this.startBtn = startBtn;
		this.endBtn = endBtn;
		this.paymentList = paymentList;
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

	public int getTotalPaymentCount() {
		return totalPaymentCount;
	}

	public void setTotalPaymentCount(int totalPaymentCount) {
		this.totalPaymentCount = totalPaymentCount;
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

	public ArrayList<SafePaymentDto> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(ArrayList<SafePaymentDto> paymentList) {
		this.paymentList = paymentList;
	}

	@Override
	public String toString() {
		return "SafepayPageDto [page=" + page + ", maxSize=" + maxSize + ", startRow=" + startRow
				+ ", totalPaymentCount=" + totalPaymentCount + ", totalPageCount=" + totalPageCount + ", startBtn="
				+ startBtn + ", endBtn=" + endBtn + ", paymentList=" + paymentList + "]";
	}
	
	
	

}
