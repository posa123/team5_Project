package model.dto;

import java.util.ArrayList;

public class PageDto {

	private int page;		// 현재 페이지 번호
	private int listsize;	// 페이지당 최대 게시물수
	private int starrow;	// 현재 페이지에서 시작되는 게시물순서
	private int totalsize;	// 총 게시물 수 or 카테고리별 게시물수
	private int totalpage;	// 총 페이지수/마지막페이지
	private int starbtn;	// 페이지버튼의 시작번호
	private int endbtn;		// 페이지버튼의 끝번호
	ArrayList<Board> boardList;	// 게시물 리스트

	public PageDto() {}

	public PageDto(int page, int listsize, int starrow, int totalsize, int totalpage, int starbtn, int endbtn,
			ArrayList<Board> boardList) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.starrow = starrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.starbtn = starbtn;
		this.endbtn = endbtn;
		this.boardList = boardList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public int getStarrow() {
		return starrow;
	}

	public void setStarrow(int starrow) {
		this.starrow = starrow;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getStarbtn() {
		return starbtn;
	}

	public void setStarbtn(int starbtn) {
		this.starbtn = starbtn;
	}

	public int getEndbtn() {
		return endbtn;
	}

	public void setEndbtn(int endbtn) {
		this.endbtn = endbtn;
	}

	public ArrayList<Board> getBoardList() {
		return boardList;
	}

	public void setBoardList(ArrayList<Board> boardList) {
		this.boardList = boardList;
	}

	@Override
	public String toString() {
		return "PageDto [page=" + page + ", listsize=" + listsize + ", starrow=" + starrow + ", totalsize=" + totalsize
				+ ", totalpage=" + totalpage + ", starbtn=" + starbtn + ", endbtn=" + endbtn + ", boardList="
				+ boardList + "]";
	}

}