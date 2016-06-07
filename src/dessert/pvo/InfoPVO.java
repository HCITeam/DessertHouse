package dessert.pvo;

import java.util.Date;

import dessert.configure.Configure;
import dessert.util.FormValidator;
import dessert.util.Util;

public class InfoPVO {
	private String id;
	private String compellation;
	private Date birthday;
	private int gender;
	private String address;
	private String phone;
	private int area;
	
	public void setFromValids(FormValidator validator){
		setId(validator.getS(Configure.ID));
		setCompellation(validator.getS(Configure.COMPELLATION));
		setBirthday(Util.getDateFromString(validator.getS(Configure.BIRTHDAY)));
		setGender(validator.getI(Configure.GENDER));
		setAddress(validator.getS(Configure.ADDRESS));
		setPhone(validator.getS(Configure.PHONE));
		setArea(validator.getI(Configure.AREA));
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
