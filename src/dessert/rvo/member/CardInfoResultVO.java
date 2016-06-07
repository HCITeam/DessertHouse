package dessert.rvo.member;

import dessert.configure.Configure;
import dessert.entity.Cardinfo;
import dessert.rvo.ResultVO;
import dessert.util.Util;

public class CardInfoResultVO extends ResultVO {
	private String id;
	private String balance;// 余额
	private int grade;// 等级
	private int state;// 状态
	private String total;// 累计充值
	private int integral;// 积分
	private String backCard;// 银行卡
	
	public void setFromCardinfo(Cardinfo cardinfo){
		setId(Util.getIDString(cardinfo.getId()));
		setBalance(Util.DoubleToString(cardinfo.getBalance()));
		setState(cardinfo.getState());
		setTotal(Util.DoubleToString(cardinfo.getTotal()));
		setGrade(cardinfo.getGrade());
		setIntegral(cardinfo.getIntegral());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getBackCard() {
		return backCard;
	}

	public void setBackCard(String backCard) {
		this.backCard = backCard;
	}

	public String getStateString(){
		switch (state) {
		case Configure.INACTIVE:
			return "未激活";
		case Configure.ACTIVE:
			return "激活";
		case Configure.PAUSE:
			return "暂停";
		case Configure.STOP:
			return "停止";
		default:
			return "";
		}
	}
}
