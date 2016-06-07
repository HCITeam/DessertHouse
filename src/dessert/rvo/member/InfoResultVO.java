package dessert.rvo.member;

import java.util.Date;

import dessert.entity.Memberinfo;
import dessert.rvo.ResultVO;

public class InfoResultVO extends ResultVO {
	private String id;
	private String compellation;
	private Date birthday;
	private int gender;
	private String address;
	private String phone;
	private int area;

	public void setInfo(Memberinfo memberinfo){
		setId(memberinfo.getId()+"");
		setCompellation(memberinfo.getCompellation());
		setBirthday(memberinfo.getBirthday());
		setGender(memberinfo.getGender());
		setAddress(memberinfo.getAddress());
		setPhone(memberinfo.getPhone());
		setArea(memberinfo.getArea());
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompellation() {
		return compellation;
	}

	public void setCompellation(String compellation) {
		this.compellation = compellation;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

}
