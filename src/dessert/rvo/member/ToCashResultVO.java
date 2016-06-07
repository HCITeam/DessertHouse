package dessert.rvo.member;

import dessert.rvo.ResultVO;

public class ToCashResultVO extends ResultVO {
	private double balance;// 余额
	private int integral;// 积分
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

}
