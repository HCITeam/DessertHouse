package dessert.rvo.member;

import dessert.rvo.ResultVO;
import dessert.util.Util;

public class LoginResultVO extends ResultVO{
	private String member_id;
	private String name;
	private int state;
	private String balance;// 余额
	private int grade;// 等级
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public void setMidFormInt(int id){
		member_id=Util.getIDString(id);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
