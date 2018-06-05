package abs2.beans;

import java.util.Date;

public class Myhab {

	private int id;
	private Date dating;
	private int inOut;
	private int category;
	private String memo;
	private int money;

	public Myhab(int id, Date dating, int inOut, int category, String memo, int money) {
		super();
		this.id = id;
		this.dating = dating;
		this.inOut = inOut;
		this.category = category;
		this.memo = memo;
		this.money = money;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDating() {
		return dating;
	}
	public void setDating(Date dating) {
		this.dating = dating;
	}
	public int getInOut() {
		return inOut;
	}
	public void setInOut(int inOut) {
		this.inOut = inOut;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}


}
