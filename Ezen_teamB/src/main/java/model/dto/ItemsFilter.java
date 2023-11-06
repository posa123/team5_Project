package model.dto;

// 물품 전체조회 검색 필터 DTO 클래스
public class ItemsFilter {
	
	private String searchWord;
	private int uno;
	private int dno;
	
	// 생성자
	public ItemsFilter() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemsFilter(String searchWord, int uno, int dno) {
		super();
		this.searchWord = searchWord;
		this.uno = uno;
		this.dno = dno;
	}
	
	
	// getter setter
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	
	@Override
	public String toString() {
		return "ItemsFilter [searchWord=" + searchWord + ", uno=" + uno + ", dno=" + dno + "]";
	}
	
	
	
	
}
