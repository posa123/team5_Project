package model.dto;

public class TradeStatistic {
	
	private int itrade;
	private int itcount;
	
	public TradeStatistic() {}

	public TradeStatistic(int itrade, int itcount) {
		super();
		this.itrade = itrade;
		this.itcount = itcount;
	}

	public int getItrade() {
		return itrade;
	}

	public void setItrade(int itrade) {
		this.itrade = itrade;
	}

	public int getItcount() {
		return itcount;
	}

	public void setItcount(int itcount) {
		this.itcount = itcount;
	}

	@Override
	public String toString() {
		return "TradeStatistic [itrade=" + itrade + ", itcount=" + itcount + "]";
	}
	

}
