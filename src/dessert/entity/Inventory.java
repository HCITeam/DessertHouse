package dessert.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "inventory")
public class Inventory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int s_id;
	private String p_name;
	private int p_num;
	@DateTimeFormat(style="yyyy-MM-dd")
	private Date s_date;
	private double price;

	public void setFromPlan(Plan plan){
		setS_id(plan.getS_id());
		setP_name(plan.getP_name());
		setP_num(plan.getP_num());
		setS_date(plan.getPlandate());
		setPrice(plan.getPrice());
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

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void sale(int num){
		p_num=p_num-num;
	}

}
