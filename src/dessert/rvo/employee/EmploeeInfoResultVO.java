package dessert.rvo.employee;

import dessert.configure.Configure;
import dessert.entity.Employee;

public class EmploeeInfoResultVO {
	private String name;
	private int type;
	private int s_id;
	
	public void setFromEmployee(Employee employee){
		setName(employee.getName());
		setType(employee.getType());
		setS_id(employee.getS_id());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
