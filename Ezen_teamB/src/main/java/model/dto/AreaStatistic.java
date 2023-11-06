package model.dto;

public class AreaStatistic {
	
	private String area;	// 지역이름
	private int trade;		// 거래량
	private int deliver;	// 배송여부
	private int tstate;	// 거래상태
	
	public AreaStatistic() {}

	public AreaStatistic(String area, int trade, int deliver, int tstate) {
		super();
		this.area = area;
		this.trade = trade;
		this.deliver = deliver;
		this.tstate = tstate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getTrade() {
		return trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public int getDeliver() {
		return deliver;
	}

	public void setDeliver(int deliver) {
		this.deliver = deliver;
	}

	public int getTstate() {
		return tstate;
	}

	public void setTstate(int tstate) {
		this.tstate = tstate;
	}

	@Override
	public String toString() {
		return "AreaStatistic [area=" + area + ", trade=" + trade + ", deliver=" + deliver + ", tstate=" + tstate + "]";
	}
	
	

}
