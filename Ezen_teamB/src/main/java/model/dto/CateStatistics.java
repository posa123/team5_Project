package model.dto;

public class CateStatistics {
	
	private String mCategory;
	private String sCategory;
	private int tState;
	private int safeState;
	private int count;
	
	public CateStatistics() {}

	public CateStatistics(String mCategory, String sCategory, int tState, int safeState, int count) {
		super();
		this.mCategory = mCategory;
		this.sCategory = sCategory;
		this.tState = tState;
		this.safeState = safeState;
		this.count = count;
	}

	public String getmCategory() {
		return mCategory;
	}

	public void setmCategory(String mCategory) {
		this.mCategory = mCategory;
	}

	public String getsCategory() {
		return sCategory;
	}

	public void setsCategory(String sCategory) {
		this.sCategory = sCategory;
	}

	public int gettState() {
		return tState;
	}

	public void settState(int tState) {
		this.tState = tState;
	}

	public int getSafeState() {
		return safeState;
	}

	public void setSafeState(int safeState) {
		this.safeState = safeState;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CateStatistics [mCategory=" + mCategory + ", sCategory=" + sCategory + ", tState=" + tState
				+ ", safeState=" + safeState + ", count=" + count + "]";
	}
	

}
