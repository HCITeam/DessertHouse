package dessert.rvo.employee;

import dessert.configure.Configure;
import dessert.entity.Employee;

public class EmpInfoResultVO {
	private String name;
	private int type;
	private String s_name;
	
//	public void setFromEmployee(Employee employee){
//		setName(employee.getName());
//		setType(employee.getType());
//		setS_id(employee.getS_id());
//	}
	
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
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
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
