package dessert.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import dessert.pvo.CartItemPVO;
import dessert.util.Util;

@Entity(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int s_id;
	private int m_id;
	private String p_name;
	private int p_num;
	private double price;
	private double amount;
	@DateTimeFormat(style="yyyy-MM-dd HH:mm:ss")
	private Timestamp c_date;//预定时间
	@DateTimeFormat(style="yyyy-MM-dd")
	private Date s_date;//送达时间
	
	public Reservation(String m_id,CartItemPVO pvo,Timestamp timestamp,int s_id){
		this.s_id=s_id;
		this.m_id=Integer.parseInt(m_id);
		p_name=pvo.getP_name();
		p_num=pvo.getP_num();
		price=pvo.getPrice();
		this.amount=pvo.getPrice()*pvo.getP_num();
		c_date=timestamp;
		s_date=Util.getDateFromString(pvo.getS_date());
	}
	
	public int getId() {
		return id;
	}
	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_num() {
		return p_num;
	}

	public void setP_num(int p_num) {
		this.p_num = p_num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getC_date() {
		return c_date;
	}

	public void setC_date(Timestamp c_date) {
		this.c_date = c_date;
	}

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

}
