package dessert.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Entity(name = "cardinfo")
public class Cardinfo {
	@Id
	private int id;
	@ColumnDefault(value = "0")
	private double balance;// 余额
	@ColumnDefault(value = "0")
	private int state;// 状态
	@ColumnDefault(value = "0")
	private double total;// 累计充值
	@ColumnDefault(value = "0")
	private int grade;// 等级
	@ColumnDefault(value = "0")
	private int integral;// 积分
	private Date last_date;// 最后激活时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
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

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public void recharge(double amount) {
		balance += amount;
		total += amount;
	}

	public void useBalance(double money) {
		balance = balance - money;
	}

	public void addIntegral(int num) {
		integral += num;
	}
}
