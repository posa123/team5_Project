package model.dto;

public class AgeStatistic {
	
	private int age;	// 연령대
	private int trade;		// 거래량
	private int strade;	// 안전거래 사용 거래량
	
	public AgeStatistic() {}

	public AgeStatistic(int age, int trade, int strade) {
		super();
		this.age = age;
		this.trade = trade;
		this.strade = strade;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getTrade() {
		return trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public int getStrade() {
		return strade;
	}

	public void setStrade(int strade) {
		this.strade = strade;
	}

	@Override
	public String toString() {
		return "AgeStatistic [age=" + age + ", trade=" + trade + ", strade=" + strade + "]";
	}


}
