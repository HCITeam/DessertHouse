package dessert.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import dessert.configure.Configure;

@Entity(name = "emploee")
public class Employee {
	@Id
	private String name;
	private String password;
	private int type;
	@ColumnDefault(value = "0")
	private int s_id;
	@ColumnDefault(value = "0")
	private int delete_flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	
	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getTypeString(){
		switch (type) {
		case Configure.HEAD_SERVER:
			return "总店服务员";
		case Configure.SERVER:
			return "分店服务员";
		case Configure.ADMINISTRATOR:
			return "系统管理员";
		case Configure.DIRECTOR:
			return "经理";
		default:
			return "";
		}
	}
}
