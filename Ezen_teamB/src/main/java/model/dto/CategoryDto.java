package model.dto;


// 물품 기능 관련한 대분류/소분류 DTO
public class CategoryDto {
	
	private int uno;			// 대분류번호 pk
	private String uname;		// 대분류 카테고리명
	private int dno;			// 대분류번호 pk
	private String dname;		// 대분류 카테고리명
	
	// 생성자
	public CategoryDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoryDto(int uno, String uname, int dno, String dname) {
		super();
		this.uno = uno;
		this.uname = uname;
		this.dno = dno;
		this.dname = dname;
	}

	
	// getter setter
	public int getUno() {
		return uno;
	}


	public void setUno(int uno) {
		this.uno = uno;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public int getDno() {
		return dno;
	}


	public void setDno(int dno) {
		this.dno = dno;
	}


	public String getDname() {
		return dname;
	}


	public void setDname(String dname) {
		this.dname = dname;
	}


	@Override
	public String toString() {
		return "CategoryDto [uno=" + uno + ", uname=" + uname + ", dno=" + dno + ", dname=" + dname + "]";
	}
	
	
	
	
	
	
	
}
