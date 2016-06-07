package dessert.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="statistics")
public class Statistics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	Date s_date;
	int num;
	String age;
	String gender;
	String area;
	String purcharse;
	String cardstate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getS_date() {
		return s_date;
	}
	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPurcharse() {
		return purcharse;
	}
	public void setPurcharse(String purcharse) {
		this.purcharse = purcharse;
	}
	public String getCardstate() {
		return cardstate;
	}
	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}
	
}
